package org.algorithm.execution.pojo;

import java.util.List;

import lombok.Data;

@Data
public class Stats {

	private final String id;
	private final String algorithmName;
	private final String algorithmType;
	private final long executionTime;
	private final String input;
	private final List<String> results;

}
