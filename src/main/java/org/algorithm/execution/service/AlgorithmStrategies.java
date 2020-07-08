package org.algorithm.execution.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlgorithmStrategies {

	/**
	 * Find all fibonacci numbers
	 *
	 * @param number
	 * @param recursion
	 * @return
	 */
	public static List<String> fibonacci(String number, Boolean recursion) {
		int num;
		List<String> results;

		try {
			num = Integer.parseInt(number);
		} catch (NumberFormatException nfe) {
			return Lists.newArrayList("The input was not a integer");
		}

		if (recursion) {
			int[] fibs = new int[num + 1];
			fibRecursion(num, fibs);

			results = Arrays.stream(fibs).boxed().collect(Collectors.toList()).stream().map(Objects::toString).collect(Collectors.toList());
		} else {
			results = fibLoop(num).stream().map(Object::toString).collect(Collectors.toList());
		}

		return results;
	}

	private static int fibRecursion(int n, int[] fibs) {

		if (n == 0 || n == 1) {
			fibs[n] = n;
			return n;
		}

		if (fibs[n] == 0) {
			fibs[n] = fibRecursion(n - 1, fibs) + fibRecursion(n - 2, fibs);
		}

		return fibs[n];
	}

	private static List<Integer> fibLoop(int n) {
		List<Integer> fibs = new ArrayList<>();
		if (n == 0) {
			fibs.add(0);
			return fibs;
		}
		if (n == 1) {
			fibs.add(1);
			return fibs;
		}
		fibs.add(0);
		fibs.add(1);

		for (int i = 2; i <= n; i++) {
			fibs.add(fibs.get(i - 1) + fibs.get(i - 2));
		}
		return fibs;
	}

	public static List<String> permutations(String word, boolean recursion) {
		List<String> perms = new ArrayList<>();
		permutations("", word, perms);
		return perms;
	}

	private static void permutations(String prefix, String suffix, List<String> results) {
		if (suffix.length() == 0) {
			results.add(suffix);
		} else {
			for (int i = 0; i < suffix.length(); i++) {
				permutations(prefix + suffix.charAt(i),
						suffix.substring(0, i) + suffix.substring(i + 1), results);
			}
		}
	}
}
