package org.algorithm.execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.algorithm.execution.service.AlgorithmStrategies;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DemoApplicationTests {

	//	@Test
	//	void contextLoads() {
	//	}

	@Test
	public void testFibonacciStrategy() {
		List<String> result = AlgorithmStrategies.fibonacci("10", false);
		assertTrue(result.size() == 11);

	}

	@Test
	public void testComputeNumOfPaths() {
		List<String> result = AlgorithmStrategies.computeNumOfPaths("2");
		assertEquals("126", result.get(0));

	}

	@Test
	public void testPermutaions() {
		List<String> result = AlgorithmStrategies.permutations("abc", false);
		String num = AlgorithmStrategies.calculateFactorial("3").get(0);
		assertEquals(result.size(), Integer.parseInt(num));

		result = AlgorithmStrategies.permutations("abcikmg", false);
		num = AlgorithmStrategies.calculateFactorial("7").get(0);
		assertEquals(result.size(), Integer.parseInt(num));

	}

	@Test
	public void testComputeNumOfDDPaths() {
		List<String> result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("1", "1");
		assertEquals("1", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("1", "2");
		assertEquals("1", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("2", "1");
		assertEquals("1", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("2", "2");
		assertEquals("2", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("2", "3");
		assertEquals("3", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("3", "2");
		assertEquals("3", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("3", "3");
		assertEquals("6", result.get(0));

		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("2", "4");
		assertEquals("4", result.get(0));

		//2,4+3,3=10
		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("3", "4");
		assertEquals("10", result.get(0));

		//3,4+4,3=20
		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("4", "4");
		assertEquals("20", result.get(0));

		//4,2+5,1=5
		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("5", "2");
		assertEquals("5", result.get(0));

		//4,3+5,2=15
		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("5", "3");
		assertEquals("15", result.get(0));

		//4,4+5,3=35
		result = AlgorithmStrategies.computeNumOfPathsDiffDimentions("5", "4");
		assertEquals("35", result.get(0));
	}

	@Test
	public void testFactorial() {
		List<String> result = AlgorithmStrategies.calculateFactorial("5");
		assertTrue(result.get(0).equals("120"));

	}
}
