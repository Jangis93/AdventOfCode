package aoc_2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 implements Day {

    private final String TEST_INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day5\\test_input.txt";
    private final String INPUT_FILE = "C:\\Users\\micha\\IdeaProjects\\AdventOfCode\\src\\aoc_2024\\resources\\day5\\input.txt";


    Map<Integer, List<Integer>> rules;
    List<List<Integer>> updates;

    @Override
    public void run() {
        try {
            parseInput(INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + INPUT_FILE + " with error: " + e);
        }

        int resultV1 = sumUpdates(true);
        System.out.println("Day5 - Part 1 Count of middle pages: " + resultV1);
        int resultV2 = sumUpdates(false);
        System.out.println("Day5 - Part 2 Count of middle pages: " + resultV2);
    }

    @Override
    public void test() {
        try {
            parseInput(TEST_INPUT_FILE);
        } catch(IOException e) {
            System.out.println("Failed to process input file: " + TEST_INPUT_FILE + " with error: " + e);
        }

        int resultV1 = sumUpdates(true);
        System.out.println("Day5 - Part 1 Count of middle pages: " + resultV1);
        int resultV2 = sumUpdates(false);
        System.out.println("Day5 - Part 2 Count of middle pages: " + resultV2);
    }

    private void parseInput(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        this.rules = new HashMap<>();
        this.updates = new ArrayList<>();

        String currentLine;
        boolean rulesParsing = true;
        while ((currentLine = reader.readLine()) != null) {
            if(rulesParsing) {
                if(currentLine.isEmpty()) {
                    rulesParsing = false;
                } else {
                    String[] rule = currentLine.split("\\|");
                    rules.computeIfAbsent(Integer.parseInt(rule[0]), k -> new ArrayList<>()).add(Integer.parseInt(rule[1]));
                }
            } else {
                updates.add(Arrays.stream(currentLine.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        }
        reader.close();
        // Debug
        /*
        System.out.println("Rules:");
        this.rules.entrySet().forEach(System.out::println);

        System.out.println("Updates:");
        this.updates.forEach(System.out::println);
        */
    }

    private int sumUpdates(boolean partOne) {
        int sum = 0;
        for(List<Integer> update: this.updates) {
            if(partOne) {
                if(isValidUpdate(update)) {
                    sum += update.get(update.size() / 2);
                }
            } else {
                update = validateAndCorrect(update);
                if(!update.isEmpty()) {
                    sum += update.get(update.size() / 2);
                }
            }
        }
        return sum;
    }

    private boolean isValidUpdate(List<Integer> updateRow) {
        List<Integer> processed = new ArrayList<>();
        List<Integer> rulesList;
        processed.add(updateRow.get(0));
        for(int i = 1; i < updateRow.size(); i++) {
            for(Integer alreadyProcessed: processed) {
                rulesList = this.rules.get(updateRow.get(i)) ;
                if(rulesList != null && rulesList.contains(alreadyProcessed)) {
                    return false;
                }
            }
            processed.add(updateRow.get(i));
        }
        return true;
    }

    private List<Integer> validateAndCorrect(List<Integer> updateRow) {
        List<Integer> processed = new ArrayList<>();
        processed.add(updateRow.get(0));
        boolean nonValidFound = false;
        for(int i = 1; i < updateRow.size(); i++) {

            int current = updateRow.get(i);
            List<Integer> rulesList = this.rules.get(current);  // Get dependency rules for current element

            if (rulesList == null || rulesList.isEmpty()) {
                // No dependencies, just add at the end
                processed.add(current);
            }else {
                // Find the correct position in 'processed'
                int insertIndex = processed.size();

                for (int j = 0; j < processed.size(); j++) {
                    if (rulesList.contains(processed.get(j))) {
                        nonValidFound = true;
                        break;
                    }
                }
                processed.add(insertIndex, current);
            }
        }
        if(nonValidFound) {
            return processed;
        }
        return Collections.emptyList();
    }
}
