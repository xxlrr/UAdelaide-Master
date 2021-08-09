//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem03
//===================================

import java.util.Random;

public class Problem03 {

    public static void main(String[] args) {
        // create a rando generator
        Random ran = new Random();
        
        // generate 100 random integer
        for(int i=0; i<100; i++) {
            int number = ran.nextInt(100);
            // check whether it is greater than 10 or less than 10 and print
            if(number > 10) {
                System.out.println("the random number is " + number + ", it is more than 10.");
            }
            else if(number < 10) {
                System.out.println("the random number is " + number + ", it is less than 10.");
            }
            else {
                System.out.println("the random number is " + number + ", it is equal to 10.");
            }
        }
        
    }
    
}
