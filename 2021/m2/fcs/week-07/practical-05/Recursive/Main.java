//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Recursive
//===================================
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        // tests next happy number
        System.out.println("tests happy number. ");
        System.out.print("Input: ");
        try {
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            int happyNum = nextHappyNum(num);
            System.out.println("Output: " + happyNum);
        }
        catch(Exception e) {
            System.out.println("Sorry, please try again!");
        }
        System.out.println("+++++++++++++++++++");
        
        // tests decode string
        System.out.println("tests decoding string.");
        String ciphertext = "3[b2[ca]1[d]]";
        System.out.println("Input: " + ciphertext);
        System.out.println("Output: " + decodeString(ciphertext));
    }
    
    // sum of squares of its digits using a recursive algorithm.
    private static int calcSquareSum(int num) {
        int singleDigit = num % 10;
        int otherDigit = num / 10;
        if (otherDigit == 0) {
            return singleDigit * singleDigit;
        }
        return singleDigit * singleDigit + calcSquareSum(otherDigit);
    }
    
    //  determine whether num is a happy number using a recursive algorithm.
    private static boolean isHappyNum(List tempList, int num) {
        if (num == 1) {
            return true;
        }
        else if (tempList.contains(num)) {
            return false;
        }
        else {
            tempList.add(num);
            int squareSum = calcSquareSum(num);
            return isHappyNum(tempList, squareSum);
        }        
    }
    
    //  find the next smallest Happy Number
    public static int nextHappyNum(int num) {
        ArrayList<Integer> tmpNumList = new ArrayList<>();
        while(!isHappyNum(tmpNumList, ++num)){}
        return num;
    }

    // problem 16, decode string
    public static String decodeString (String s) {
        
        String tmpStr = "";  // save letter string
        String tmpNum = "";  // save digit string
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if ('0'<=c && c <= '9') {
                tmpNum += c;
            }
            else if (c =='[') {
                int startPos = i+1;
                int bracketCount = 1;
                
                i++;
                while (bracketCount != 0) {
                    c = s.charAt(i);
                    if (c == '[') {
                        bracketCount++;
                    }
                    else if (c == ']') {
                        bracketCount--;
                    }
                    i++;
                }
                i--;
                
                String childStr = s.substring(startPos, i);
                // parse child code using a recursive algorithm.
                tmpStr += decodeString(childStr).repeat(Integer.valueOf(tmpNum));
                tmpNum = "";
            }
            else {
                tmpStr += c;
            }
        }
        return tmpStr;
    }   
    
}
