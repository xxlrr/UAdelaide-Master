//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem03
//===================================

import java.util.InputMismatchException;
import java.util.Scanner;

public class Problem03 {

    public static void main(String[] args) {
        
        try{
            // create a scanner
            Scanner scanner = new Scanner(System.in);
            // prompt and receive a number
            System.out.print("Please, insert a number: ");
            float number1 = scanner.nextFloat();

            // prompt and receive a number again
            System.out.print("Please, insert a number again: ");
            float number2 = scanner.nextFloat();

            // get the bigger number and print
            float maxNumber = number1 > number2 ? number1 : number2;
            System.out.println("The bigger number is " + maxNumber);

            // close the scanner
            scanner.close();
        }
        // handle wrong inputs
        catch(InputMismatchException e){
            System.out.println("[ERROR]: your input does not match,"
                    + " please enter an integer!");
        }
        // handle other exceptions
        catch(Exception e){
            System.out.println("Sorry, the program encountered an unknown error."
                    + " Please try again");
        }
    }
    
}
