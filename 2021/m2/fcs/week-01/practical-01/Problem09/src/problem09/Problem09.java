package problem09;

import java.util.Scanner;

/**
 * @author haohongxing
 * 
 * Problem09: ask a dog some information
 */
public class Problem09 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // prompt the dog
        System.out.println("Hi dog, please enter the following information!");
        
        // a String value
        System.out.println("Enter your name: ");
        String name = scanner.next();
        
        // an int value
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        
        // a String value
        System.out.println("Enter your favorite food: ");
        String food = scanner.next();
        
        // a String value
        System.out.println("Enter your favorite toy: ");
        String mathsGrade = scanner.next();
        
        // a String value
        System.out.println("Enter your owner's name: ");
        String englishGrade = scanner.next();

    }
    
}
