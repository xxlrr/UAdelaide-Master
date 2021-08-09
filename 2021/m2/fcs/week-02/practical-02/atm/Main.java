//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem05
//===================================

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ATM.run();
    }
    
}

class ATM {
    
    public static void run() {
        int cash;    // save the cash user entered
        int remain;  // save the remain cash
        
        System.out.print("Please enter an amount of cash: ");
        
        // get cash
        Scanner scanner = new Scanner(System.in);
        cash = scanner.nextInt();
        scanner.close();
        
        // calculate how many $50 and how many $20
        int count50 = cash / 50;
        remain = cash % 50;
        int count20 = remain / 20;
        remain = remain % 20;
        
        // tell the user
        if(remain == 0) {
            System.out.println("Here is " + count20 + " $20 notes and " + count50 + " $50 notes.");
        }
        else {
            System.out.println("Sorry, the value your input cannot be withdrow.");
        }
    }
    
}