package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 implements Day {

	private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day1\\input.txt";
	private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day1\\test_input.txt";

	private List<Long> list1;
	private List<Long> list2;

	private Map<Long, Long> counts;

	Day1() {
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		counts = new HashMap<>();
	}

	@Override
	public void test() {
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		counts = new HashMap<>();
		try {
			readInput(TEST_INPUT_FILE);
		} catch (IOException e) {
			System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
		}
		calculate();
	}

	@Override
	public void run() {
		try {
			readInput(INPUT_FILE);
		} catch (IOException e) {
			System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
		}
		calculate();
	}

	private void calculate() {
		// Day1 - Part 1
		int sum = 0;
		for(int i = 0; i < list1.size(); i++) {
			sum += (int) Math.abs(list1.get(i) - list2.get(i));
		}
		System.out.println("Day1 - Part 1 Total distans: " + sum);

		// Day1 - Part 2
		long similarityScoreSum = 0;
		for (Long value : list1) {
			long count = counts.getOrDefault(value, 0L);
			similarityScoreSum += value * count;
		}
		System.out.println("Day1 - Part 2 Similarity score is: " + similarityScoreSum);
	}

	private void readInput(String file) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String currentLine;
		while ((currentLine = reader.readLine()) != null) {
			String[] pair = currentLine.split(" ");
			Long value1 = Long.parseLong(pair[0]);
			Long value2 = Long.parseLong(pair[1]);
			addSorted(value1, list1);
			addSorted(value2, list2);

			counts.put(value2, counts.getOrDefault(value2, 0L) + 1);
		}
		reader.close();
	}

	// todo: use quick sort
	private void addSorted(long value, List<Long> sorted) {
		int size = sorted.size();
		for(int i = 0; i < sorted.size(); i++) {
			if(value < sorted.get(i)) {
				sorted.add(i, value);
				break;
			}
		}
		// we haven't added the value yet
		if(size == sorted.size()) {
			sorted.add(value);
		}
	}
}
