package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Day {

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day3\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day3\\input.txt";



    @Override
    public void run() {
        try {
            parseInput(INPUT_FILE, true);
            parseInput(INPUT_FILE, false);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }
    }

    @Override
    public void test() {
        try {
            parseInput(TEST_INPUT_FILE, true);
            parseInput(TEST_INPUT_FILE, false);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }
    }

    private void parseInput(String file, boolean firstPart) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String regex;
        if(firstPart) {
            regex = "mul\\((\\d*),(\\d*)\\)";
        } else {
            regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)|do\\(\\)";
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;// = pattern.matcher(input);

        int sum = 0;
        int multiplier = 1;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {

            matcher = pattern.matcher(currentLine);
            while (matcher.find()) {

                if(firstPart) {
                    String firstNumber = matcher.group(1); // First capture group
                    String secondNumber = matcher.group(2); // Second capture group

                    //System.out.println("First number: " + firstNumber);
                    //System.out.println("Second number: " + secondNumber);
                    sum += Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
                } else {
                    String fullMatch = matcher.group(0); // Entire match

                    if (fullMatch.startsWith("mul")) {
                        // Extract x and y values for "mul(x, y)"
                        String firstNumber = matcher.group(1); // First capture group
                        String secondNumber = matcher.group(2); // Second capture group
                        //System.out.println("Multiplication operation: x = " + firstNumber + ", y = " + secondNumber + " and multiplier: " + multiplier + " sumOfMult: " + multiplier*Integer.parseInt(firstNumber)*Integer.parseInt(secondNumber));
                        sum += multiplier * (Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
                    } else if (fullMatch.equals("don't()")) {
                        multiplier = 0;
                        // Handle "don't()"
                        //System.out.println("Encountered a 'don't()' operation.");
                    } else if (fullMatch.equals("do()")) {
                        multiplier = 1;
                        // Handle "do()"
                        //System.out.println("Encountered a 'do()' operation.");
                    }
                }
            }
        }
        reader.close();

        if(firstPart) {
            System.out.println("Day3 - Part 1 Sum of corrupted calculations: " + sum);
        } else {
            System.out.println("Day3 - Part 2 Sum of corrupted calculations: " + sum);
        }

    }

// 93729253
}
