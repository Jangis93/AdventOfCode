package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 implements Day {

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day6\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day6\\input.txt";

    List<String> grid;

    @Override
    public void run() {
        try {
            parseInput(INPUT_FILE);
        } catch (IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }

        int resultV1 = countGuardPositions();
        System.out.println("Day6 - Part 1 Count of unique guard positions: " + resultV1);
    }

    @Override
    public void test() {
        try {
            parseInput(TEST_INPUT_FILE);
        } catch (IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }

        int resultV1 = countGuardPositions();
        System.out.println("Day6 - Part 1 Count of unique guard positions: " + resultV1);
    }

    private void parseInput(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        this.grid = new ArrayList<>();
        while ((currentLine = reader.readLine()) != null) {
            grid.add(currentLine.trim());
        }
        reader.close();

        // Debug: Print the grid
        /*
        System.out.println("Grid:");
        for (String line : grid) {
            System.out.println(line);
        }
         */
    }

    private int countGuardPositions() {
        int steps = 1;
        int[] startPosition = getGuardPosition();
        updateMarkerForGuardPosition(startPosition);
        int[] direction = {-1, 0}; // move upwards
        int[] nextGuardPosition = new int[]{startPosition[0] + direction[0], startPosition[1] + direction[1]};

        while (validGuardPosition(nextGuardPosition)) {
            char nextPositionSymbol = this.grid.get(nextGuardPosition[0]).charAt(nextGuardPosition[1]);
            switch (nextPositionSymbol) {
                case '#':
                    // since we are looking already on the next step, we need to back this step and update the direction
                    nextGuardPosition = new int[]{nextGuardPosition[0] - direction[0], nextGuardPosition[1] - direction[1]};
                    direction = new int[]{direction[1], -direction[0]};
                    break;
                case '.':
                    // move with same direction but mark X and update steps
                    updateMarkerForGuardPosition(nextGuardPosition);
                    steps += 1;
                    break;
            }
            nextGuardPosition = new int[]{nextGuardPosition[0] + direction[0], nextGuardPosition[1] + direction[1]};
        }
        return steps;
    }

    private int[] getGuardPosition() {
        String current;
        int j;
        for (int i = 0; i < this.grid.size(); i++) {
            current = this.grid.get(i);
            j = current.indexOf('^');
            if (j > -1) {
                return new int[]{i, j};
            }
        }
        return null;
    }

    private boolean validGuardPosition(int[] currentGuardPosition) {
        return currentGuardPosition != null &&
                currentGuardPosition[0] >= 0 && currentGuardPosition[0] < this.grid.size() &&
                currentGuardPosition[1] >= 0 && currentGuardPosition[1] < this.grid.size();
    }

    private void updateMarkerForGuardPosition(int[] guardPosition) {
        if (guardPosition != null) {
            char[] rowChars = grid.get(guardPosition[0]).toCharArray();
            rowChars[guardPosition[1]] = 'X';
            this.grid.set(guardPosition[0], new String(rowChars));
            //printGrid();
        }
    }

    // debug function
    private void printGrid() {
        for (String line : this.grid) {
            System.out.println(line);
        }
    }
}
