import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;


public class Main{

    public static void main(String[] args){
        try {  
            File input = new File("day_01/input.txt");
            Scanner scanner = new Scanner(input);

        
            int totalLines = 0;
            while(scanner.hasNextLine()){
                scanner.nextLine();
                totalLines++;
            }

            int[] leftValues = new int[totalLines];
            int[] rightValues = new int[totalLines];
            scanner.close();

            //populate arrays with input
            scanner = new Scanner(input);
            int index = 0;

            while(scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                if(!line.isEmpty()){
                    String[] numbers = line.split(" +");
                    if(numbers.length == 2){
                        leftValues[index] = Integer.parseInt(numbers[0]);
                        rightValues[index] = Integer.parseInt(numbers[1]);
                        index++;
                    }else{
                        System.err.println("Unsupported line format: " + line);
                    }
                } 
            } 
            scanner.close();

            calculateDifferenceOfElements(leftValues, rightValues);
            calculateSimilarityScore(leftValues, rightValues);
        } catch (FileNotFoundException e){
            System.err.println("There was an error while reading the file: " + e.getMessage());
        }
    }

    public static void calculateDifferenceOfElements(int[] arr1, int[] arr2){

        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int difference = 0;

        for(int i = 0; i < arr1.length; i++){
            difference += Math.abs(arr1[i] - arr2[i]);
        }

        System.out.println("The total difference is: " + difference);
       
    }

    public static void calculateSimilarityScore(int[] left, int[] right){
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int similarityScore = 0;
        //populate map from right array
        for(int num : right){
            if(freqMap.containsKey(num)){
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }else{
                freqMap.put(num, 1);
            } 
        }
        for(int num : left){
            similarityScore += freqMap.getOrDefault(num, 0) * num;
        }

        System.out.println("The total similarity score is: " + similarityScore );
    }

}