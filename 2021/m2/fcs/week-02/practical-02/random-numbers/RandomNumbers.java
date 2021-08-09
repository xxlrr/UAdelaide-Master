//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Random Numbers
//===================================

import java.util.Random;

public class RandomNumbers {

    public static void main(String[] args) {
        // create a random generator
        Random ran = new Random();
        
        // sum over 10 random numbers
        int sum = 0;
        for(int i=0; i<10;i++) {
            sum += ran.nextInt(100);
        }
        // calculate the average number
        float mean = sum / 10.0f;
        
        // echo result
        System.out.println("The sum is " + sum + ", The mean is " + mean + ".");
    }
    
}
