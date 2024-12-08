package aoc_2021;

import javafx.util.Pair;
import aoc_2021.utilities.utilities;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    private final static String filePath = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\resources\\input_day_3";


    public static void run() throws IOException {
        // 2a. Dive!
        System.out.println("Power consumption " + getPowerConsumption());

        System.out.println("Live support rating: " + getLifesupportRating());
    }

    private static int getPowerConsumption() throws IOException {
        List<String> commands = utilities.getInputFromFile(filePath);
        int numberOflines = commands.size();

        int[] bitsums = sumBits(commands);
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

    private static int[] sumBits(List<String> commands) {

        int[] bitSums = new int[commands.get(0).length()];

        for (String s: commands) {
            for(int i = 0; i < s.length(); i++) {
                bitSums[i] = bitSums[i] + Integer.parseInt(String.valueOf(s.charAt(i)));
            }
        }

        return bitSums;
    }


    private static int getLifesupportRating() throws IOException {
        List<String> commands = utilities.getInputFromFile(filePath);

        List<String> o = commands;
        for(int i = 0; i < commands.get(0).length(); i++) {
            Pair<Integer, Integer> counts = countBitsInColumn(i, o);
            int mostCommon = counts.getKey() > counts.getValue() ? 0 : 1;

            int finalI = i;
            if(o.size() != 1) {
                o = o.stream().filter(line -> Character.getNumericValue(line.charAt(finalI)) == mostCommon).collect(Collectors.toList());
            }
        }

        List<String> c = commands;
        for(int i = 0; i < commands.get(0).length(); i++) {
            Pair<Integer, Integer> counts = countBitsInColumn(i, c);
            int mostCommon = counts.getKey() > counts.getValue() ? 0 : 1;
            int finalI = i;
            if(c.size() != 1) {
                c = c.stream().filter(line -> Character.getNumericValue(line.charAt(finalI)) != mostCommon).collect(Collectors.toList());
            }
        }

        int oxygen = binaryToDec(o.get(0));
        int co2 = binaryToDec(c.get(0));

        System.out.format("Calculated gama oxygen: %d and carbon rate %d", oxygen, co2);
        System.out.println();
        return co2 * oxygen;
    }

    private static int binaryToDec(String bits) {
        int j = 0;
        int decimal = 0;
        for(int i = bits.length() - 1; i >= 0; i--) {
            decimal += Character.getNumericValue(bits.charAt(i)) * Math.pow(2, j);
            j++;
        }
        return decimal;
    }

    private static Pair<Integer, Integer> countBitsInColumn(int column, List<String> input) {
        int zeros = 0;
        int ones = 0;
        for(String line: input) {
            if (line.charAt(column) == '0') {
                zeros ++;
            } else {
                ones ++;
            }
        }

        return new Pair(zeros, ones);
    }

}

/*
10110

00100
11110   11110
10110   10110   10110   10110   10110   10110
10111   10111   10111   10111   10111
10101   10101   10101   10101
01111
00111
11100   11100
10000   10000   10000
11001   11001
00010
01010



 */