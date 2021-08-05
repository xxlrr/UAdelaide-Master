//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem04
//===================================
package problem04;

import java.util.Scanner;


public class Problem {

    // convert the input miles to kilometres
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // asks the user for a distance in miles. 
        System.out.print("Enter a ditinct in miles: ");
        double miles = scanner.nextDouble();
        
        // if the distance is in the range [0,1000] it will be converted
        if(0 <= miles && miles <= 1000){
            double kilometres = 1.6093 * miles;
            System.out.printf("The distance in kilometres is %.2f\n", kilometres);
        }
        // tell the user if the distance is not in the range
        else {
            System.out.println("The distance " + miles + " is out of range");
        }
    }
    
}
