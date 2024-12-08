package aoc_2024;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Day> days = new ArrayList<>();
		days.add(new Day1());
		days.add(new Day2());
		//days.add(new Day3());
		for(int i = 1; i < days.size(); i++) {
			System.out.println(String.format("---------- Day %s ----------%n", i));
			Day day = days.get(i);
			System.out.println(String.format("--- Day %s - Test run ---", i));
			day.test();
			System.out.println(String.format("--- Day %s - Run ---", i));
			day.run();
		}
	}
}
