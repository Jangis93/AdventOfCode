import java.io.IOException;
import java.util.List;

public class Day2 {

    private final static String filePath = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\input_day_2";

    public static void run() throws IOException {
        // 2a. Dive!
        System.out.println("Combined position " + getMultipliedPosition());
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

}
