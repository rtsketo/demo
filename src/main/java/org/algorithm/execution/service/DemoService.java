package org.algorithm.execution.service;

import java.util.*;
import java.util.stream.Collectors;

import org.algorithm.execution.pojo.Stats;

import com.google.common.collect.Lists;

public class DemoService {

	public static List<String> calculatePermutations(String str) {
		if (str == null) {
			return Collections.emptyList();
		}
		ArrayList<String> permutations = new ArrayList<>();
		//base case
		if (str.length() == 0) {
			permutations.add("");
			return permutations;
		}
		char first = str.charAt(0);
		String remainder = str.substring(1);
		List<String> words = calculatePermutations(remainder);
		for (String word : words) {
			for (int j = 0; j < words.size(); j++) {
				String s = DemoService.insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}

	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}

	public static List<String> permutations(String word) {
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

	public static List<String> fibonacci(String number, boolean recursion, ActiveMqService activeMqService) {
		int num;
		List<String> results;

		long start = System.currentTimeMillis();
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
		long end = System.currentTimeMillis();

		Stats stats = calculateStats(start, end, "fibonacci", recursion ? "withRecursion" : "withLoop");

		activeMqService.sendMessage(stats);

		return results;
	}

	private static Stats calculateStats(long start, long end, String algorithmName, String type) {
		UUID uuid = UUID.randomUUID();
		return new Stats(uuid.toString(), algorithmName, type, end - start);
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
}
