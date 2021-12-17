import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void run() throws IOException {
        // 1. Sonar Swap
        System.out.println("Number of increased depths: " + getNumberOfDepthIncreases());

        // 2. With sliding window
        System.out.println("Number of increased depths: " + getNumberOfDepthIncreasesSlidingWindow());
    }

    private static int getNumberOfDepthIncreasesSlidingWindow() throws IOException {

        List<Integer> depths = getInputFromFile();

        int numberOfIncreases = 0;
        int previousSlidingSum = depths.get(0) + depths.get(1) + depths.get(2);

        for(int i = 2; i < depths.size() - 1; i++) {

            int currentSlidingSum = depths.get(i - 1) + depths.get(i) + depths.get(i + 1);
            if(currentSlidingSum > previousSlidingSum) {
                numberOfIncreases++;
            }
            previousSlidingSum = currentSlidingSum;
        }

        return numberOfIncreases;
    }


    private static int getNumberOfDepthIncreases() throws IOException {

        List<Integer> depths = getInputFromFile();

        int numberOfIncreases = 0;

        for(int i = 0; i < depths.size() - 1; i++) {

            if(depths.get(i) < depths.get(i + 1)) {
                numberOfIncreases++;
            }
        }

        return numberOfIncreases;
    }

    private static List<Integer> getInputFromFile() throws IOException {
        List<Integer> depths = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\input"));
        String line;
        while((line = reader.readLine()) != null) {
            depths.add(Integer.parseInt(line));
        }

        return depths;
    }
}
