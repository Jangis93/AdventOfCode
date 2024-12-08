package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 implements Day{

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day2\\test_input_2.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day2\\input.txt";

    @Override
    public void run() {
        try {
            //readInput(INPUT_FILE, false);
            readInput(INPUT_FILE, true);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }
    }

    @Override
    public void test() {
        try {
            //readInput(TEST_INPUT_FILE, false);
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
            List<Integer> reports = Arrays.stream(currentLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            sumSafeReports += validateReport(reports, newConstraint) ? 1 : 0;

        }
        reader.close();

        System.out.println("Day2 - Part 1 Sum of safe reports: " + sumSafeReports);
    }

    private boolean validateReport(List<Integer> reports, boolean newConstraint) {
        int direction =  reports.get(0) - reports.get(1);
        for(int i = 0; i < reports.size()-1; i++) {
            boolean valid = validatePair(reports.get(i), reports.get(i + 1), direction);
            if(!valid) {
                if(newConstraint) {
                    newConstraint = false;
                    // now we know that we have an un-valid case so we need to look at:
                    // previous + next
                    // current + (next + 1)
                    if(i == 0) {// we can't check first item
                        boolean next = validatePair(reports.get(i+1), reports.get(i + 2), direction);
                        boolean nextOtherDirection = validatePair(reports.get(i+1), reports.get(i + 2), -direction);
                        if(!next && !nextOtherDirection) {
                            System.out.println("Report: " + reports + " is unsafe");
                            return false;
                        } else if(nextOtherDirection) {
                            int newDirection = reports.get(i+1) - reports.get(i + 2);
                            direction = newDirection;
                            i = i + 2;
                            //System.out.println("Switching direction from: " + direction + " to " + newDirection);
                        } else {
                            i = i + 2;
                        }
                    } else if(i - 1 >= 0 && i + 2 < reports.size()) { // not first item
                        boolean prevNextValid = validatePair(reports.get(i - 1), reports.get(i + 1), direction);
                        boolean next = validatePair(reports.get(i), reports.get(i + 2), direction);

                        boolean nextNewDirection = validatePair(reports.get(i), reports.get(i + 2), -direction);
                        boolean prevNextValidNewDirection = validatePair(reports.get(i - 1), reports.get(i + 1), -direction);

                        if(!prevNextValid && !next && !nextNewDirection && !prevNextValidNewDirection) {
                            // not a single valid option
                            //System.out.println("Report: " + reports + " is unsafe");
                            return false;
                        } else if(prevNextValidNewDirection) {
                            int newDirection = reports.get(i - 1) - reports.get(i + 1);
                            direction = newDirection;
                            //System.out.println("Switching direction from: " + direction + " to " + newDirection);
                            i = i + 2;
                        } else if(nextNewDirection){
                            int newDirection = reports.get(i) - reports.get(i + 2);
                            direction = newDirection;
                            //System.out.println("Switching direction from: " + direction + " to " + newDirection);
                            i = i + 2;
                        } else if(next){
                            i = i + 2;
                        } else {
                            i = i + 1;
                        }
                    } //else {
                        //System.out.println("end of reports and we can't check anymore");
                    //}
                } else {
                    System.out.println("Report: " + reports + " is unsafe");
                    return false;
                }
            }
        }
        System.out.println("Report: " + reports + " is safe");
        return true;
    }

    private boolean validatePair(int current, int next, int direction) {
        int diff = current - next;
        int absDiff = Math.abs(diff);
        if(absDiff < 0 || absDiff > 3 || absDiff == 0 || (direction * diff < 0)) {
            return false;
        } else {
            return true;
        }
    }
}

/*
            if(!valid) {
                if (newConstraint) {
                    newConstraint = false;
                    if(i - 1 >= 0 && i < reports.size() - 2) {
                        int newDirection =  reports.get(i-1) - reports.get(i+2);
                        boolean skipFirst = validatePair(reports.get(i-1), reports.get(i + 2), newDirection);
                        if (!skipFirst) {
                            System.out.println("Report: " + reports + " is unsafe");
                            return false;
                        } else if(skipFirst) {
                            direction = newDirection;
                        }
                    } else if (i == 0) {
                        int newDirection =  reports.get(i+1) - reports.get(i+2);
                        boolean nextValid = validatePair(reports.get(i+1), reports.get(i + 2), newDirection);
                        if (!nextValid) {
                            System.out.println("Report: " + reports + " is unsafe");
                            return false;
                        } else {
                            direction = newDirection;
                        }
                    }
                    /*
                } else {
                    System.out.println("Report: " + reports + " is unsafe");
                    return false;
                }
            }*/