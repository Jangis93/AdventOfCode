package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 implements Day {

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day4\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day4\\input.txt";

    List<String> grid;

    @Override
    public void run() {
        try {
            parseInput(INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }

        int result = countXMAS_v1();
        System.out.println("Day4 - Part 1 Count of XMAS: " + result);
        int result_v2 = countXMAS_v2(this.grid);
        System.out.println("Day4 - Part 2 Count of XMAS: " + result_v2);
    }

    @Override
    public void test() {
        try {
            parseInput(TEST_INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }

        int result_v1 = countXMAS_v1();
        System.out.println("Day4 - Part 1 Count of XMAS: " + result_v1);
        int result_v2 = countXMAS_v2(this.grid);
        System.out.println("Day4 - Part 2 Count of XMAS: " + result_v2);
    }

    private void parseInput(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine;
        int j = 0;
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

    public int countXMAS_v1() {
        int rows = this.grid.size();
        int cols = this.grid.get(0).length();
        String target = "XMAS";
        int targetLen = target.length();

        // Directions (row change, col change)
        int[][] directions = {
                {0, 1},   // Horizontal right
                {0, -1},  // Horizontal left
                {1, 0},   // Vertical down
                {-1, 0},  // Vertical up
                {1, 1},   // Diagonal down-right
                {1, -1},  // Diagonal down-left
                {-1, 1},  // Diagonal up-right
                {-1, -1}  // Diagonal up-left
        };

        int count = 0;

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Check for "XMAS" in all directions from this cell
                for (int[] direction : directions) {
                    int dr = direction[0];
                    int dc = direction[1];

                    // Check if the word can fit in this direction
                    if (canFit(r, c, dr, dc, rows, cols, targetLen)) {
                        boolean match = true;
                        for (int i = 0; i < targetLen; i++) {
                            int nr = r + i * dr;
                            int nc = c + i * dc;
                            if (this.grid.get(nr).charAt(nc) != target.charAt(i)) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    // Check if "XMAS" can fit in a specific direction from a given starting point
    private static boolean canFit(int r, int c, int dr, int dc, int rows, int cols, int targetLen) {
        int endRow = r + (targetLen - 1) * dr;
        int endCol = c + (targetLen - 1) * dc;
        return endRow >= 0 && endRow < rows && endCol >= 0 && endCol < cols;
    }


    public static int countXMAS_v2(List<String> grid) {
        int count = 0;
        int rows = grid.size();
        int cols = grid.get(0).length();  // Length of the first string (assuming all strings have the same length)

        // Traverse the grid to find the "X-MAS" patterns
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (isXMASPattern(grid, i, j)) {
                    /*
                    System.out.println("Pattern found at center (" + i + ", " + j + ")");
                    System.out.println(grid.get(i-1).substring(j-1, j+2));
                    System.out.println(grid.get(i).substring(j-1, j+2));
                    System.out.println(grid.get(i+1).substring(j-1, j+2));
                    */
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean isXMASPattern(List<String> grid, int i, int j) {
        // Check if the current cell forms an X-MAS pattern
        /*
        M.S
        .A.
        M.S
         */
        boolean firstPattern = (grid.get(i - 1).charAt(j - 1) == 'M' &&
                grid.get(i).charAt(j) == 'A' &&
                grid.get(i + 1).charAt(j + 1) == 'S' &&
                grid.get(i - 1).charAt(j + 1) == 'S' &&
                grid.get(i + 1).charAt(j - 1) == 'M');

        /*
        M.M
        .A.
        S.S
         */
        boolean secondPattern = (grid.get(i - 1).charAt(j - 1) == 'M' &&
                grid.get(i).charAt(j) == 'A' &&
                grid.get(i + 1).charAt(j + 1) == 'S' &&
                grid.get(i - 1).charAt(j + 1) == 'M' &&
                grid.get(i + 1).charAt(j - 1) == 'S');

        /*
        S.S
        .A.
        M.M
         */
        boolean thirdPattern = (grid.get(i - 1).charAt(j - 1) == 'S' &&
                grid.get(i).charAt(j) == 'A' &&
                grid.get(i + 1).charAt(j + 1) == 'M' &&
                grid.get(i - 1).charAt(j + 1) == 'S' &&
                grid.get(i + 1).charAt(j - 1) == 'M');
        /*
        S.M
        .A.
        S.M
         */
        boolean fourthPattern = (grid.get(i - 1).charAt(j - 1) == 'S' &&
                grid.get(i).charAt(j) == 'A' &&
                grid.get(i + 1).charAt(j + 1) == 'M' &&
                grid.get(i - 1).charAt(j + 1) == 'M' &&
                grid.get(i + 1).charAt(j - 1) == 'S');

        /*
        System.out.println("First Pattern: " + firstPattern);
        System.out.println("Second Pattern: " + secondPattern);
        System.out.println("Third Pattern: " + thirdPattern);
        System.out.println("Fourth Pattern: " + fourthPattern);
        */
        return firstPattern || secondPattern || thirdPattern || fourthPattern;
    }

}
