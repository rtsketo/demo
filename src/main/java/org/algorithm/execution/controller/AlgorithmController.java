package org.algorithm.execution.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.algorithm.execution.pojo.Stats;
import org.algorithm.execution.service.ActiveMqService;
import org.algorithm.execution.service.AlgorithmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AlgorithmController {

	@Autowired
	private ActiveMqService activeMqService;

	@Autowired
	private AlgorithmService algorithmService;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello " + name + "!";
	}

	@GetMapping("/permutations")
	public String permutations(@RequestParam(value = "word", defaultValue = "abc") String word) {
		List<String> perms = algorithmService.runAlgorithmAndSendStats("permutations", word, false);
		return "Permutations for word are " + String.join(",", perms);
	}

	@GetMapping("/fibonacci")
	public String fibonacci(@RequestParam(value = "number", defaultValue = "10") String number,
			@RequestParam(value = "recursion", defaultValue = "false") boolean recursion) {
		List<String> fibs = algorithmService.runAlgorithmAndSendStats("fibonacci", number, recursion);
		//DemoService.fibonacci(number, recursion, activeMqService);
		return "The fibonacci of " + number + " is " + fibs;
	}

	@GetMapping("/algorithm")
	public String algorithm(@RequestParam(value = "algorithmName", defaultValue = "") String algorithmName,
			@RequestParam(value = "input", defaultValue = "") String input,
			@RequestParam(value = "recursion", defaultValue = "false") boolean recursion) {
		List<String> fibs = algorithmService.runAlgorithmAndSendStats(algorithmName, input, recursion);
		//DemoService.fibonacci(number, recursion, activeMqService);
		return "The " + algorithmName + " of " + input + " is " + fibs;
	}

	@GetMapping("/activeMQ")
	public String activeMQ(@RequestParam(value = "message", defaultValue = "hello") String message) {
		log.info("Sending to ActiveMQ message: " + message);
		UUID uuid = UUID.randomUUID();

		activeMqService.sendMessage(new Stats(uuid.toString(), "demo", "1", 0));
		return "Message Sent";
	}
}
