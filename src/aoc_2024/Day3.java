package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Day {

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day3\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day3\\input.txt";



    @Override
    public void run() {
        try {
            parseInput(INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }
    }

    @Override
    public void test() {
        try {
            parseInput(TEST_INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }
    }

    private void parseInput(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String p = "mul\\((\\d*),(\\d*)\\)";
        Pattern pattern = Pattern.compile(p);

        int sum = 0;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(currentLine);

            while (matcher.find()) {
                String firstNumber = matcher.group(1); // First capture group
                String secondNumber = matcher.group(2); // Second capture group

                //System.out.println("First number: " + firstNumber);
                //System.out.println("Second number: " + secondNumber);
                sum += Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
            }

        }
        reader.close();

        System.out.println("Day3 - Part 1 Sum of corrupted calculations: " + sum);
    }

}
