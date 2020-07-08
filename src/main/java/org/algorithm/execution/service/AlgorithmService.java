package org.algorithm.execution.service;

import java.util.List;
import java.util.UUID;

import org.algorithm.execution.pojo.Stats;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlgorithmService {

	private final ActiveMqService activeMqService;

	public List<String> runAlgorithm(String algorithmName, String input, boolean recursion) {
		return AlgorithmType.valueOf(algorithmName.toUpperCase()).runAlgorithm(input, recursion);
	}

	public List<String> runAlgorithmAndSendStats(String algorithmName, String input, boolean recursion) {

		long start = System.currentTimeMillis();
		List<String> results = runAlgorithm(algorithmName, input, recursion);

		long end = System.currentTimeMillis();

		Stats stats = calculateStats(start, end, "fibonacci", recursion ? "withRecursion" : "withLoop");

		//TODO do this on a new thread
		activeMqService.sendMessage(stats);

		return results;

	}

	private static Stats calculateStats(long start, long end, String algorithmName, String type) {
		UUID uuid = UUID.randomUUID();
		return new Stats(uuid.toString(), algorithmName, type, end - start);
	}

}
