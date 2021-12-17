import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {

        // 1. Sonar Swap
        System.out.println("Number of increased depths: " + getNumberOfDepthIncreases());

        // 2. 

    }


    private static int getNumberOfDepthIncreases() throws IOException {

        List<Integer> depths = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\input"));
        String line;
        while((line = reader.readLine()) != null) {
            depths.add(Integer.parseInt(line));
        }

        int numberOfIncreases = 0;

        for(int i = 0; i < depths.size() - 1; i++) {

            if(depths.get(i) < depths.get(i + 1)) {
                numberOfIncreases++;
            }
        }

        return numberOfIncreases;
    }
}
