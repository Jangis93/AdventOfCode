import java.io.IOException;
import java.util.List;

public class Day3 {

    private final static String filePath = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\input_day_3";


    public static void run() throws IOException {
        // 2a. Dive!
        System.out.println("Power consumption " + getPowerConsumption());
    }

    private static int getPowerConsumption() throws IOException {
        List<String> commands = utilities.getInputFromFile(filePath);
        int numberOflines = commands.size();

        int[] bitsums = sumbits(commands);
        int gammaRate = 0;
        int epsilonRate = 0;

        int j = 0;
        for(int i = bitsums.length - 1; i >= 0; i--) {
            int gammaBit, epsilonBit;
            if (bitsums[i] > numberOflines / 2) {
                gammaBit = 1;
                epsilonBit = 0;
            } else {
                gammaBit = 0;
                epsilonBit = 1;
            }
            gammaRate += gammaBit * Math.pow(2, j);
            epsilonRate += epsilonBit * Math.pow(2, j);
            j++;
        }

        System.out.format("Calculated gama rate: %d and epsilon rate %d", gammaRate, epsilonRate);
        System.out.println();
        return epsilonRate * gammaRate;
    }

    private static int[] sumbits(List<String> commands) throws IOException {

        int[] bitSums = new int[commands.get(0).length()];

        for (String s: commands) {
            for(int i = 0; i < s.length(); i++) {
                bitSums[i] = bitSums[i] + Integer.parseInt(String.valueOf(s.charAt(i)));
            }
        }

        return bitSums;
    }
}
