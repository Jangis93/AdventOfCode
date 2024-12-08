package aoc_2021.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class utilities {

    public static List<String> getInputFromFile(String path) throws IOException {
        List<String> depths = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine()) != null) {
            depths.add(line);
        }

        return depths;
    }
}
