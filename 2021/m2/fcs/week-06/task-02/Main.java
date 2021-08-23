//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Task-02
//===================================

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.print("Please enter a positive integer: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        Fibonacci f = new Fibonacci();
        System.out.printf("%d fibonacci numbers: ", number);
        for (int counter = 1; counter <= number; counter++){
            System.out.printf("%d, ", f.fibonacci(counter));
        }
        System.out.println();
    }

}
