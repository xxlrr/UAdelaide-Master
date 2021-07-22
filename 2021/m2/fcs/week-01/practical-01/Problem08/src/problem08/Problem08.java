package problem08;

/**
 * @author haohongxing
 * 
 * Problem08: ask a student some information
 */
import java.util.Scanner;
public class Problem08 {

    public static void main(String[] args) {       
        Scanner scanner = new Scanner(System.in);

        // prompt the studnet
        System.out.println("Hi student, please enter the following information!");
        
        // a String value
        System.out.println("Enter your name: ");
        String name = scanner.next();
        
        // an int value
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        
        // a String value
        System.out.println("Enter your favorite food: ");
        String food = scanner.next();
        
        // a float value
        System.out.println("Enter your maths grade: ");
        float mathsGrade = scanner.nextFloat();
        
        // a float value
        System.out.println("Enter your english grade: ");
        float englishGrade = scanner.nextFloat();
        
        // a float value
        System.out.println("Enter your height: ");
        float htight = scanner.nextFloat();
        
        // a float value
        System.out.println("Enter your wight: ");
        float wight = scanner.nextFloat();
        
        // a int value
        System.out.println("Enter your number of subject: ");
        int number = scanner.nextInt();
        
    }
    
}
