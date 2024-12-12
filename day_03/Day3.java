package day_03;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Day3{

    public static void main(String[] args){
        String filePath = "day_03/memory.txt";
        int totalSum = readFile(filePath);
        System.out.println("Total sum of all products: " + totalSum);
    }

    public static int readFile(String filePath){
        int totalSum = 0;
        try{
            File inputFile = new File(filePath);
            Scanner fileScanner = new Scanner(inputFile);

            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                totalSum += processLine(line);  
            }

            fileScanner.close();

        } catch(FileNotFoundException e){
            System.err.println("Error processing file: " + e.getMessage());
        }

        return totalSum;    
    }

    public static int processLine(String line){
        int sum = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)"); //creates a pattern of mul(intA,intB)
        Matcher matcher = pattern.matcher(line);

        while(matcher.find()){
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            sum += num1 * num2;
        }
        return sum;
    }



}