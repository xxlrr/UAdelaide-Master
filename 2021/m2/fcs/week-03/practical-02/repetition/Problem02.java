//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem02
//===================================
package problem02;

import java.util.Random;

public class Problem02 {

    public static void main(String[] args) {
        // create a random generator
        Random ran = new Random();
        
        int sum = 0;
        // generate 50 random integers and sum
        for(int i=0; i<50; i++) {
            int number = ran.nextInt(100);
            // check odd or even
            if(number % 2 == 0) {
                System.out.println("Get an even number: " + number);
            }
            else {
                System.out.println("Get an odd number: " + number);
            }
            sum += number;
        }
        // calculate average
        float mean = sum / 50.0f;
        
        // echo sum and mean
        System.out.println("The sum is "+ sum + ", The mean is " + mean);
    }
    
}
