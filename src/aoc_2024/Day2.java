package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 implements Day{

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day2\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day2\\input.txt";

    @Override
    public void run() {
        try {
            readInput(INPUT_FILE, false);
            readInput(INPUT_FILE, true);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }
    }

    @Override
    public void test() {
        try {
            readInput(TEST_INPUT_FILE, false);
            readInput(TEST_INPUT_FILE, true);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }
    }

    private void readInput(String file, boolean newConstraint) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int sumSafeReports = 0;

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> report = Arrays.stream(currentLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            sumSafeReports += analyzeReports(report, newConstraint) ? 1 : 0;

        }
        reader.close();

        System.out.println("Day2 - Part 1 Sum of safe reports: " + sumSafeReports);
    }

    private boolean analyzeReports(List<Integer> report, boolean newConstraint) {
        if (isSafe(report)) {
            //System.out.println("Report: " + report + " is safe");
            return true;
        }

        if(!newConstraint) {
            //System.out.println("Report: " + report + " is unsafe");
            return false;
        }

        for(int i = 0; i < report.size(); i++) {
            List<Integer> modifiedLevels = new ArrayList<>(report);
            modifiedLevels.remove(i);

            // Check if the modified list is safe
            if (isSafe(modifiedLevels)) {
                //System.out.println("Report: " + modifiedLevels + " is safe");
                return true;
            }
        }
        //System.out.println("Report: " + report + " is unsafe");
        return false;
    }

    private boolean isSafe(List<Integer> levels) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);

            // Check if the difference is within the allowed range
            int absDiff = Math.abs(diff);
            if(absDiff > 3 || absDiff == 0) {
                return false;
            }

            // Update flags for increasing or decreasing sequence
            if (diff <= 0) increasing = false;
            if (diff >= 0) decreasing = false;
        }
        return increasing || decreasing;
    }
}