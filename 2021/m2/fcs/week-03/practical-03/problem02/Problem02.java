//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem02
//===================================

import java.util.InputMismatchException;
import java.util.Scanner;


public class Problem02 {

    public static void main(String[] args) {
        try{
            // prompt and accept an integer from the user
            System.out.print("Please input a integer: ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            
            // check whether the number is prime or not
            boolean isPrime = true;
            for(int i=2; i<number; i++){
                if(number%i == 0){
                    isPrime = false;
                    break;
                }
            }
            
            // print the result
            if(isPrime){
                 System.out.println("Your number is prime");
            }
            else{
                 System.out.println("Your number is not prime");
            }
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
