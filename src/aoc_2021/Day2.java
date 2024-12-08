package aoc_2021;

import aoc_2021.utilities.utilities;

import java.io.IOException;
import java.util.List;

public class Day2 {

    private final static String filePath = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\resources\\input_day_2";

    public static void run() throws IOException {
        // 2a. Dive!
        System.out.println("Combined position " + getMultipliedPosition());

        // 2b. Dive with aim!
        System.out.println("Combined position with aim " + getMultipliedPositionWithAim());

    }

    private static int getMultipliedPosition() throws IOException {
        int horizontal = 0;
        int depth = 0;

        List<String> commands = utilities.getInputFromFile(filePath);

        for (String s : commands) {
            String[] command = s.split(" ");

            switch (command[0]) {
                case "forward":
                    horizontal += Integer.parseInt(command[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(command[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(command[1]);
                    break;
            }
        }
        System.out.format("Position horizontal: %d and depth %d", horizontal, depth);
        System.out.println();

        return horizontal * depth;
    }

    private static int getMultipliedPositionWithAim() throws IOException {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        List<String> commands = utilities.getInputFromFile(filePath);

        for (String s : commands) {
            String[] command = s.split(" ");

            switch (command[0]) {
                case "forward":
                    horizontal += Integer.parseInt(command[1]);
                    depth += aim * Integer.parseInt(command[1]);
                    break;
                case "down":
                    aim+= Integer.parseInt(command[1]);
                    break;
                case "up":
                    aim-= Integer.parseInt(command[1]);
                    break;
            }
        }
        System.out.format("Position horizontal: %d, depth %d and aim %d ", horizontal, depth, aim);
        System.out.println();

        return horizontal * depth;
    }

}
