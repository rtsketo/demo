package org.algorithm.execution.pojo;

import lombok.Data;

@Data
public class Stats {

	private final String id;
	private final String algorithmName;
	private final String algorithmType;
	private final long executionTime;

}
