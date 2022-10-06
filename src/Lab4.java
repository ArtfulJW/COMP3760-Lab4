import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Lab4 {
    
    public Integer[] savedArray;
    
    public int missingNumber = 0;
    private int counterBruteForce = 0;
    private int counterBinarySearch = 0;
    
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
    
    private int BinarySearch(Integer[] numList) {
        
        int low = 0;
        int high = numList.length-1;
      
        while (low <= high) {
            
            int median = (low + high) / 2;
            System.out.println("Median: " + median + " numList[median] = " + numList[median]
                    + "\nLeft of median " +numList[(median)-1] + " Right of median " +numList[(median)+1]);
            
            // Check middle element
            if (numList[median] != median + 1 && numList[median - 1] == median) {
                return median + 1;
            }
            if (numList[median] == median + 1) {
                low = median + 1;
            } else {
                high = median -1;
            }
            
        }
        
        // Nothing missing
        return -1;
    }
    
    private int getPasscodeBinarySearch(Integer[] numList) {  
        // Ascertain that the counter will be an expected value before starting
        
        
        // Check middle value
        int indexMiddle = (int)Math.ceil(numList.length/2);
        int middleValue = numList[indexMiddle];
        
//        if (numList.length > 2) {
//            System.out.println("LENGTH OF NUMLIST: " + numList.length 
//                    + "\nLEFT OF MIDDLE: " + numList[indexMiddle-1]
//                    + "\nMIDDLE VALUE: " + middleValue
//                    + "\nRIGHT OF MIDDLE: " + numList[indexMiddle+1] + "\n");
//        } else {
//            System.out.println("LENGTH OF NUMLIST: " + numList.length 
//                    + "\nINDEX 0: " + numList[0] 
//                    + "\nINDEX 1: " + numList[1]);
//        }
        
        
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
                System.out.println("Found Missing Value: " + (middleValue-1) + " " + numList[indexMiddle-1]);
                if (missingNumber > (middleValue-1)) {
                    missingNumber =  middleValue-1;
                }
//                missingNumber = middleValue-1;
                setCounterBinarySearch(binarySearchComparisons() + 1);
            }
            
            if (middleValue+1 != numList[indexMiddle+1]){
                System.out.println("Found Missing Value: " +(middleValue+1)+ " " +numList[indexMiddle+1]);
                if (missingNumber > (middleValue+1)) {
                    missingNumber =  middleValue+1;
                }
                setCounterBinarySearch(binarySearchComparisons() + 1);
            } 
        }

        if (numList.length % 2 == 0) {
            // Check left side
            Integer[] leftArray = new Integer[(int)Math.ceil(numList.length/2)];
            System.out.println("LHS");
            copyArray(numList, leftArray, 0 , indexMiddle);
            for (Integer x : leftArray) {
                System.out.println(x);
            }
            if (getPasscodeBinarySearch(leftArray) < 0) {
                System.out.println("Missing Number not on left side.\n");
            }
            
            // Check right side
            Integer[] rightArray = new Integer[(int)Math.ceil(numList.length/2)];
            System.out.println("RHS");
            copyArray(numList, rightArray, indexMiddle , numList.length);
            for (Integer x : rightArray) {
                System.out.println(x);
            }
            
            if (getPasscodeBinarySearch(rightArray) < 0) {
                System.out.println("Missing Number not on right side.\n");

            } 

        } else {
            // Check left side
            Integer[] leftArray = new Integer[(int)Math.ceil(numList.length/2)];
            System.out.println("\nLHS");
            copyArray(numList, leftArray, 0 , indexMiddle);
            for (Integer x : leftArray) {
                System.out.println(x);
            }
            if (getPasscodeBinarySearch(leftArray) < 0) {
                System.out.println("Missing Number not on left side.\n");
            }
            

            
            // Check right side
            Integer[] rightArray = new Integer[(int)Math.ceil(numList.length/2)];
            System.out.println("\nRHS");
            copyArray(numList, rightArray, indexMiddle+1 , numList.length);
            for (Integer x : rightArray) {
                System.out.println(x);
            }
            if (getPasscodeBinarySearch(rightArray) < 0) {
                System.out.println("Missing Number not on right side.\n");
            }
        }
        
        
//        System.out.println("LHS: " + numList[0] + " to " + numList.length 
//                + " LENGTH OF LHS ARRAY: " 
//                + numList.length 
//                + " VALUE OF MIDDLE VALUE: " 
//                + middleValue
//                + " VALUE OF START OF ARRAY: "
//                + numList[0]);
//        
//        System.out.println("RHS: " + numList[0] + " to " + numList.length 
//                + " LENGTH OF RHS ARRAY: " 
//                + numList.length 
//                + " VALUE OF MIDDLE VALUE: " 
//                + middleValue
//                + " VALUE OF END OF ARRAY: "
//                + numList[numList.length-1]);
      

        
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
        
//        for (Integer x : numList) {
//            outputArray[index] = x;
//            index++;
//        }
    }
    
    // Range based copy.
    private void copyArray(Integer[] inputArray, Integer[] outputArray, int rangeLow, int rangeHigh) {
        int index = 0;
//        System.out.println(rangeLow);
//        System.out.println(rangeHigh);
        
        if (outputArray.length == 2 && rangeLow == rangeHigh-1) {
            for (int x = rangeLow; x <= rangeHigh; x++) {
                outputArray[x-rangeLow] = inputArray[x];
            }
        } else {
            for (int x = rangeLow; x < rangeHigh; x++) {
                outputArray[x-rangeLow] = inputArray[x];
            } 
        }
        
        
//        for (Integer x : numList) {
//            outputArray[index] = x;
//            index++;
//        }
    }
    
    // Getter 
    public int bruteForceComparisons() {
        return counterBruteForce;
    }
    public void setCounterBruteForce(int num) {
        this.counterBruteForce = num;
    }
    
    public int binarySearchComparisons() {
        return counterBinarySearch;
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
        
        int missingNumberBrute = getPasscodeBruteForce(outputArray);
        System.out.println("Brute Force Missing Number: " + missingNumberBrute);   
        
        //int missingNumberBinary = getPasscodeBinarySearch(BinarySearch(outputArray,0,outputArray.length-1,outputArray.length/2,0+(outputArray[outputArray.length-1])/2));
        missingNumber = outputArray[outputArray.length-1];
        getPasscodeBinarySearch(outputArray);
        int missingNumberBinary = missingNumber;
        System.out.println("Number of Comparisons: " + binarySearchComparisons());
        System.out.println("Binary Search Missing Number: " + missingNumberBinary);
        
        //BinarySearch(outputArray);
        
        
        
        // Output for Debug
//        for (Integer x : outputArray) {
//            System.out.println(x);
//        }
        
        
        
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Lab4 BOT = new Lab4();
        try {
            BOT.parseFile("src\\length_3_N_429.txt");
//            System.out.println("Brute Force Counter: " + BOT.bruteForceComparisons());
//            BOT.parseFile("src\\length_4_N_0930.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
