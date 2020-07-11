package org.algorithm.execution.service;

import java.util.*;
import java.util.stream.Collectors;

import org.algorithm.execution.pojo.ListNode;

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
	public static List<String> fibonacci(String number, boolean recursion) {
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

	/**
	 * Recursive function that will return the fibonacci number of n
	 * @param n the input
	 * @param fibs array for memoization
	 * @return
	 */
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

	/**
	 * Calculates the fibonacci of n by using a for loop
	 *
	 * @param n the input
	 * @return the fibonacci of n
	 */
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
			results.add(prefix);
			System.out.println(prefix);
		} else {
			for (int i = 0; i < suffix.length(); i++) {

//				prefix = prefix + suffix.charAt(i);
//				suffix = suffix.substring(0, i) + suffix.substring(i + 1);
//
//				permutations(prefix, suffix, results);


				System.out.println("prefix is : \""+prefix+"\" and suffix is \""+suffix+"\" i is "+i);
				String newPrefix = prefix + suffix.charAt(i);//i=0,a,i=2,b,i=3
				String newSuffix = suffix.substring(0, i) +	suffix.substring(i + 1);

				System.out.println("Calling persutations with newPrefix: \""+newPrefix+"\" and newSuffix \""+newSuffix+"\"");
				permutations(newPrefix, newSuffix, results);

			}
		}
	}

	public static List<String> sum(String number, boolean recursion) {
		int num = Integer.parseInt(number);//positive and >0
		if (recursion) {

			return Collections.emptyList();
		} else {
			int res = 0;
			for (int i = 0; i <= num; i++) {
				res += i;
			}
			return List.of(String.valueOf(res));
		}
	}

	private int sum(int num) {
		if (num == 0) {
			return 0;
		} else {
			return sum(num - 1) + num;
		}
	}

	public static List<String> computeNumOfPathsDiffDimentions(String c, String r) {
		int columns = Integer.parseInt(c);
		int rows = Integer.parseInt(r);
		return List.of(String.valueOf(computeNumOfPathsDD(columns, rows)));
	}

	private static int computeNumOfPathsDD(int cols, int rows) {
		if (cols == 1 || rows == 1) {
			return 1;
		}
		return computeNumOfPathsDD(cols, rows - 1) + computeNumOfPathsDD(cols - 1, rows);
	}

	public static List<String> computeNumOfPaths(String number) {
		int num = Integer.parseInt(number);
		return List.of(String.valueOf(computePaths(num, 1, 1)));
	}

	private static int computePaths(int n, int i, int j) {
		if (i == n || j == n) {
			//reach either border, only one path
			return 1;
		}
		return computePaths(n, i + 1, j) + computePaths(n, i, j + 1);
	}

	public static List<String> calculateFactorial(String number) {
		int num = Integer.parseInt(number);
		return List.of(String.valueOf(factorial(num)));

	}

	private static int factorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return number * factorial(number - 1);
		}
	}

	public static int numberOfPaths(int m, int n) {
		// If either given row number is first or
		// given column number is first
		if (m == 1 || n == 1)
			return 1;

		// If diagonal movements are allowed then
		// the last addition is required.
		return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
		// + numberOfPaths(m-1, n-1);
	}

	public static int[] twoSumsBrute(int[] nums, int target) {
		int[] sums = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					sums[0] = i;
					sums[1] = i + 1;
					break;
				}
				if (nums[j] > target) {
					break;
				}
			}
			if (nums[i] > target) {
				break;
			}
		}

		return sums;
	}

	public static int[] twoSums(int[] nums, int target) {
		int[] sums = new int[2];

		Map<Integer, Integer> numsMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			numsMap.put(nums[i], i);
		}

		for (int i = 0; i < nums.length; i++) {
			int sub = target - nums[i];
			if (numsMap.containsKey(sub) && numsMap.get(sub) != i) {
				sums[0] = i;
				sums[1] = numsMap.get(sub);
			}

		}
		return sums;
	}

//	public static void main(String[] args) {
//		System.out.println(numberOfPaths(3, 3));
//
//		System.out.println(computeNumOfPaths("3"));
//		int[] nums = { 3, 3 };
//		//Stream.of(twoSum(nums, 9)).forEach(x-> System.out.println(x));
//		Arrays.stream(twoSumsBrute(nums, 6)).forEach(x -> System.out.println(x));
//		Arrays.stream(twoSums(nums, 6)).forEach(x -> System.out.println(x));
//
//		ListNode add1 = new ListNode(2);
//		add1.next = new ListNode(4);
//		add1.next.next = new ListNode(3);
//
//		ListNode add2 = new ListNode(5);
//		add2.next = new ListNode(6);
//		add2.next.next = new ListNode(4);
//
//		ListNode listNode = addTwoLists(add1, add2);
//		System.out.println(listNode);
//	}

	public static ListNode addTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		int carry = 0;
		ListNode add1 = l1;
		ListNode add2 = l2;
		ListNode listNode = dummy;
		while (add1 != null || add2 != null) {
			int x = (add1 != null) ? add1.val : 0;
			int y = (add2 != null) ? add2.val : 0;
			int sum = x + y + carry;
			carry = sum >= 10 ? 1 : 0;

			//listNode.val = sum % 10;
			if (Objects.nonNull(add1)) {
				add1 = add1.next;
			}
			if (Objects.nonNull(add2)) {
				add2 = add2.next;
			}

			listNode.next = new ListNode(sum % 10);
			listNode = listNode.next;
		}
		if (carry > 0) {
			listNode.next = new ListNode(carry);
		}
		return dummy.next;

	}

}
