package org.algorithm.execution;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.algorithm.execution.service.AlgorithmStrategies;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testFibonacciStrategy() {
		List<String> result = AlgorithmStrategies.fibonacci("10", false);
		assertTrue(result.size() == 11);

	}
}
