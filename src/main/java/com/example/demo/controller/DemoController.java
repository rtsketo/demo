package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Stats;
import com.example.demo.service.ActiveMqService;
import com.example.demo.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	private ActiveMqService activeMqService;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello " + name + "!";
	}

	@GetMapping("/permutations")
	public String permutations(@RequestParam(value = "word", defaultValue = "abc") String word) {
		List<String> perms = DemoService.calculatePermutations(word);
		return "Permutations for word are " + String.join(",", perms);
	}

	@GetMapping("/fibonacci")
	public String fibonacci(@RequestParam(value = "number", defaultValue = "10") String number,
			@RequestParam(value = "recursion", defaultValue = "false") boolean recursion) {
		List<String> fibs = DemoService.fibonacci(number, recursion, activeMqService);
		return "The fibonacci of " + number + " is " + fibs;
	}

	@GetMapping("/activeMQ")
	public String activeMQ(@RequestParam(value = "message", defaultValue = "hello") String message) {
		activeMqService.sendMessage(new Stats(1,"demo", "1", 0));
		return "Message Sent";
	}
}
