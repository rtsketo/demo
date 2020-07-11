package org.algorithm.execution.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.algorithm.execution.pojo.Stats;
import org.apache.commons.lang3.EnumUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AlgorithmService {

	private final ActiveMqService activeMqService;
	private final ExecutorService executorService;

	/**
	 * Service method that runs the algorithm, calculates the statistics and sends them to active mq
	 * @param algorithmName if the algorithmName does not exist then a message is returned to inform the client
	 * @param input Dending on the chosen algorithm this might differ
	 * @param recursion Whether the client requests for the recursion version of this algorithm
	 * @return
	 */
	public List<String> runAlgorithmAndSendStats(String algorithmName, String input, boolean recursion) {

		if (Objects.isNull(EnumUtils.getEnum(AlgorithmType.class,algorithmName.toUpperCase()))) {
			return List.of("Algorithm could not be found. Avalable Algorithms are: "+getAlgorithms());
		}

		long start = System.currentTimeMillis();
		List<String> results = runAlgorithm(algorithmName, input, recursion);

		long end = System.currentTimeMillis();

		Stats stats = calculateStats(start, end, algorithmName, recursion ? "withRecursion" : "withLoop",input,results);

		log.debug(stats.toString());

		executorService.execute(() -> {
					try {
						activeMqService.sendMessage(stats);
						log.info("Stats with id: "+stats.getId()+" sent to ActiveMq.");
					} catch (Exception e) {
						log.error("Could not send message to active mq. Error message is: "+ e.getMessage());
					}
				}
		);


		return results;

	}

	/**
	 * Returns all available algorithms to the client
	 * @return A list with all available algorithm names
	 */
	public List<String> getAlgorithms(){
		return Stream.of(AlgorithmType.values()).map(Enum::toString).collect(Collectors.toList());
	}

	private List<String> runAlgorithm(String algorithmName, String input, boolean recursion) {
		return AlgorithmType.valueOf(algorithmName.toUpperCase()).runAlgorithm(input, recursion);
	}

	private static Stats calculateStats(long start, long end, String algorithmName, String type,String input,List<String> results) {
		UUID uuid = UUID.randomUUID();
		return new Stats(uuid.toString(), algorithmName, type, end - start,input,results);
	}



}
