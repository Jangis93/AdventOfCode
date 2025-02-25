package aoc_2024;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Day> days = new ArrayList<>();
		days.add(new Day1());
		days.add(new Day2());
		days.add(new Day3());
		days.add(new Day4());
		for(int i = 0; i < days.size(); i++) {
			System.out.printf("---------- Day %s ----------%n%n", i + 1);
			Day day = days.get(i);
			System.out.printf("--- Day %s - Test run ---%n", i + 1);
			day.test();
			System.out.printf("--- Day %s - Run ---%n", i + 1);
			day.run();
		}
	}
}
