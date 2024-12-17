package day_03;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Day3 {
    public static boolean isProcessing = true; // Global state for processing

    public static void main(String[] args) {
        String filePath = "day_03/memory.txt";
        int totalSum = readFile(filePath);
        System.out.println("Total sum of all products: " + totalSum);
    }

    public static int readFile(String filePath) {
        int totalSum = 0;

        try {
            File inputFile = new File(filePath);
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Processing line: " + line); // Debug: Log the line being processed
                totalSum += processLine(line);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

        return totalSum;
    }

    public static int processLine(String line) {
        int sum = 0;
    
        // Combined pattern to match both directives and calculations
        Pattern combinedPattern = Pattern.compile("(do\\(\\))|(don't\\(\\))|mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = combinedPattern.matcher(line);
    
        while (matcher.find()) {
            if (matcher.group(1) != null) { // Found do()
                isProcessing = true;
                System.out.println("Processing enabled (do()).");
            } else if (matcher.group(2) != null) { // Found don't()
                isProcessing = false;
                System.out.println("Processing disabled (don't()).");
            } else if (matcher.group(3) != null && isProcessing) { // Found mul(int, int)
                int num1 = Integer.parseInt(matcher.group(3));
                int num2 = Integer.parseInt(matcher.group(4));
                int result = num1 * num2;
                sum += result;
                System.out.println("Processed mul(" + num1 + ", " + num2 + ") = " + result);
            }
        }
    
        return sum;
    }
}    