package org.algorithm.execution.service;

import java.util.List;
import java.util.function.BiFunction;

public enum AlgorithmType {

	FIBONACCI(AlgorithmStrategies::fibonacci),
	PERMUTATIONS(AlgorithmStrategies::permutations),
	SUM(AlgorithmStrategies::sum);

	AlgorithmType(BiFunction<String, Boolean, List<String>> function) {
		this.function = function;
	}

	BiFunction<String, Boolean, List<String>> function;

	public List<String> runAlgorithm(String number, boolean recursion) {
		return function.apply(number, recursion);
	}
}
