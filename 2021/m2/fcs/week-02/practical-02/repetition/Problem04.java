//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem04
//===================================

import java.util.Random;


public class Problem04 {

    public static void main(String[] args) {
        // create a random generator
        Random ran = new Random();
        
        // get a random integer
        int number = ran.nextInt(3);
        // print different content for different values
        switch(number) {
            case 0:
                System.out.println("Rock");
                break;
            case 1:
                System.out.println("Paper");
                break;
            case 2:
                System.out.println("Scissors");
                break;
        }
    }
    
}
