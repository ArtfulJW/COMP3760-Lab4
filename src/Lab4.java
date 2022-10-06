import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Name: Jay Wang
// ID: A01291640

public class Lab4 {
    
    public int missingNumber = 0;
    private int counterBruteForce = 0;
    private int counterBinarySearch = 0;
    
    // Brute Force Search Implementation
    private int getPasscodeBruteForce(Integer[] numList) {
        // Ascertain that the counter will be an expected value before starting
        setCounterBruteForce(0);
        int numToFind = 0;
        
        for (Integer x : numList) {
            // Check for each number in ArrayList
            if (x == numToFind) {
                // Number exists in list
                numToFind++;
                setCounterBruteForce(bruteForceComparisons()+1);
            } else {
                // Found missing number.
                // System.out.println(numToFind);
                return numToFind;
            }
        }
        return -1;
        
    }
    
    // Implementation of BinarySearch
    private int getPasscodeBinarySearch(Integer[] numList) {  
        // Ascertain that the counter will be an expected value before starting
        
        
        // Check middle value
        int indexMiddle = (int)Math.ceil(numList.length/2);
        int middleValue = numList[indexMiddle]; 
        
        // Base case checks and assignment operator to class variable missingNumber
        if (((numList[0]+1 == numList[1]) && (numList[numList.length-2]+1 == numList[numList.length-1])) && numList.length == 2) {
            // Array of Size two where there is no missing number.
            return -1;
        } else if (((numList[0]+1 != numList[1]) && (numList[numList.length-2]+1 != numList[numList.length-1])) && numList.length == 2){
            if (missingNumber > (numList[0] + numList[1]) / 2) {
                missingNumber =  (numList[0] + numList[1]) / 2;
            }
            return (numList[0] + numList[1]) / 2;
        } else {
        
            if (middleValue-1 != numList[indexMiddle-1]) {
                if (missingNumber > (middleValue-1)) {
                    missingNumber =  middleValue-1;
                }
                setCounterBinarySearch(binarySearchComparisons() + 1);
            }
            
            if (middleValue+1 != numList[indexMiddle+1]){
                if (missingNumber > (middleValue+1)) {
                    missingNumber =  middleValue+1;
                }
                setCounterBinarySearch(binarySearchComparisons() + 1);
            } 
        }
        
        // If Else to ascertain even and odd cases
        if (numList.length % 2 == 0) {
            // Check left side
            Integer[] leftArray = new Integer[(int)Math.ceil(numList.length/2)];
            copyArray(numList, leftArray, 0 , indexMiddle);
            
            // No missing number found, do nothing
            if (getPasscodeBinarySearch(leftArray) < 0) {

            }
            
            // Check right side
            Integer[] rightArray = new Integer[(int)Math.ceil(numList.length/2)];
            copyArray(numList, rightArray, indexMiddle , numList.length);
            
            // No missing number found, do nothing
            if (getPasscodeBinarySearch(rightArray) < 0) {} 

        } else {
            // Check left side
            Integer[] leftArray = new Integer[(int)Math.ceil(numList.length/2)];
            copyArray(numList, leftArray, 0 , indexMiddle);
            
            // No missing number found, do nothing
            if (getPasscodeBinarySearch(leftArray) < 0) {}
            
            // Check right side
            Integer[] rightArray = new Integer[(int)Math.ceil(numList.length/2)];
            copyArray(numList, rightArray, indexMiddle+1 , numList.length);
            
            // No missing number found, do nothing
            if (getPasscodeBinarySearch(rightArray) < 0) {}
        }
        
        // Somethings gone wrong...
        return -1;
        
    }
    
    // Helper Function: FormatString
    // Removes Leading 0's from String
    private void formatString(ArrayList<Integer> numList) {
        for (Integer x : numList) {
            String inputStringNum = x.toString();
            int len = 0;
           
            // Parse and find the end index of the 'substring' of '0's
            while (len < inputStringNum.length() && inputStringNum.equals("0") && inputStringNum.length()>1) {
                len++;
            }
            
            // Create StringBuffer to modify target String
            StringBuffer stringBuffer = new StringBuffer(inputStringNum);
            
            // Replace from index 0 to len, which is the end of the substring of 0's with nothing.
            stringBuffer.replace(0, len, "");
            
            // Convert new modified String back to integer.
            x = Integer.parseInt(stringBuffer.toString());
            
        }
    }
    
    // Range based copy.
    private void copyArrayList(ArrayList<Integer> numList, Integer[] outputArray, int rangeLow, int rangeHigh) {
        int index = 0;
        
        for (int x = rangeLow; x < rangeHigh; x++) {
            outputArray[x] = numList.get(x);
        }
        
    }
    
    // Range based copy.
    private void copyArray(Integer[] inputArray, Integer[] outputArray, int rangeLow, int rangeHigh) {
        int index = 0;
        
        if (outputArray.length == 2 && rangeLow == rangeHigh-1) {
            for (int x = rangeLow; x <= rangeHigh; x++) {
                outputArray[x-rangeLow] = inputArray[x];
            }
        } else {
            for (int x = rangeLow; x < rangeHigh; x++) {
                outputArray[x-rangeLow] = inputArray[x];
            } 
        }   
    }
    
    // Getter 
    public int bruteForceComparisons() {
        return counterBruteForce;
    }
    public int binarySearchComparisons() {
        return counterBinarySearch;
    }
    
    // Setter
    public void setCounterBruteForce(int num) {
        this.counterBruteForce = num;
    }
    public void setCounterBinarySearch(int num) {
        this.counterBinarySearch = num;
    }
    
    // Helper Function: parseFile
    // Allows for file reading.
    private void parseFile(String fileName) throws FileNotFoundException{
        System.out.println("Parsing...");
        
        ArrayList<Integer> numList = new ArrayList<Integer>();
        
        Scanner fileScan = new Scanner(new File(fileName));
        Scanner lineScan;
        
        // Parse File.
        while (fileScan.hasNextInt()) {
            lineScan = new Scanner(fileScan.nextLine());
            while (lineScan.hasNext()) {
                int token = lineScan.nextInt();
                numList.add(token);
            }
        }
        
        // Remove leading 0's
        formatString(numList);
        
        // Sort Ascending
        Collections.sort(numList);
        
        // Create Array with the same size as the ArrayList
        Integer[] outputArray = new Integer[numList.size()];
        // Copy every content of ArrayList into Array.
        copyArrayList(numList, outputArray, 0, numList.size());
        
        // Calling Brute Force
        int missingNumberBrute = getPasscodeBruteForce(outputArray);
        System.out.println("Number of Comparisons [BruteForce]: " + bruteForceComparisons());
        System.out.println("Brute Force Missing Number: " + missingNumberBrute);   
        
        // Calling Binary Search
        missingNumber = outputArray[outputArray.length-1];
        getPasscodeBinarySearch(outputArray);
        int missingNumberBinary = missingNumber;
        System.out.println("Number of Comparisons [BinarySearch]: " + binarySearchComparisons());
        System.out.println("Binary Search Missing Number: " + missingNumberBinary);
        
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Lab4 BOT = new Lab4();
        try {
            BOT.parseFile("src\\length_2_N_44.txt");
//            BOT.parseFile("src\\length_3_N_021.txt");
            BOT.parseFile("src\\length_3_N_429.txt");
            BOT.parseFile("src\\length_4_N_0930.txt");
            BOT.parseFile("src\\length_4_N_8589.txt");
//            BOT.parseFile("src\\length_5_N_27100.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
