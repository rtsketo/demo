package com.example.demo.pojo;

import lombok.Data;

@Data
public class Stats {

	private final int id;
	private final String algorithmName;
	private final String algorithmType;
	private final long executionTime;

}
