import java.io.IOException;
import java.util.List;

public class Day1 {

    private final static String filePath = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\input_day_1";

    public static void run() throws IOException {
        // 1a. Sonar Swap
        System.out.println("Number of increased depths: " + getNumberOfDepthIncreases());

        // 1b. With sliding window
        System.out.println("Number of increased depths: " + getNumberOfDepthIncreasesSlidingWindow());
    }

    private static int getNumberOfDepthIncreasesSlidingWindow() throws IOException {

        List<String> depths = utilities.getInputFromFile(filePath);

        int numberOfIncreases = 0;
        int previousSlidingSum = Integer.parseInt(depths.get(0)) +Integer.parseInt(depths.get(1)) + Integer.parseInt(depths.get(2));

        for(int i = 2; i < depths.size() - 1; i++) {

            int currentSlidingSum =  Integer.parseInt(depths.get(i - 1)) +Integer.parseInt(depths.get(i)) + Integer.parseInt(depths.get(i + 1));;
            if(currentSlidingSum > previousSlidingSum) {
                numberOfIncreases++;
            }
            previousSlidingSum = currentSlidingSum;
        }

        return numberOfIncreases;
    }


    private static int getNumberOfDepthIncreases() throws IOException {

        List<String> depths = utilities.getInputFromFile(filePath);

        int numberOfIncreases = 0;

        for(int i = 0; i < depths.size() - 1; i++) {

            if(Integer.parseInt(depths.get(i)) < Integer.parseInt(depths.get(i  + 1))) {
                numberOfIncreases++;
            }
        }

        return numberOfIncreases;
    }
}
