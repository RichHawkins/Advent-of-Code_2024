package day_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DayTwo{
    public static void main(String[] args){
        try{
            File input = new File("day_02/reports.txt");
            int totalSafeReports = 0;
            ArrayList<ArrayList<Integer>> reports = parseInputToMakeArray(input);
            
            for(ArrayList<Integer> report : reports){
                if(isIncreasingAndSafe(report) || isDecreasingAndSafe(report)){
                    totalSafeReports++;
                }
            }
            System.out.println("Total number of safe reports: " + totalSafeReports);
        }catch(FileNotFoundException e){
            System.err.println("Error loading file: " + e.getMessage());
        }
       
      
        
    }


    public static Boolean isIncreasingAndSafe(ArrayList<Integer> list){
        
        for(int i = 0; i < list.size() - 1; i++){
            int diff = list.get(i + 1)- list.get((i));
            if(diff < 1 || diff > 3){
                return false;
            } 
        }
        return true;
    }


    public static Boolean isDecreasingAndSafe(ArrayList<Integer> list){

        for(int i = 0; i < list.size() - 1; i++){
            int diff = list.get(i) - list.get((i + 1));
            if(diff < 1 || diff > 3){
                return false;
            } 
        }
        return true;
    }
    public static ArrayList<ArrayList<Integer>> parseInputToMakeArray(File input) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> reports = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] nums = line.split(" +");
                    ArrayList<Integer> report = new ArrayList<>();
                    for (String num : nums) {
                        report.add(Integer.parseInt(num));
                    }
                    reports.add(report);
                }
            }
        }
        return reports;
}
}



