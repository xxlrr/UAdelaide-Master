//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Task-01
//===================================
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Please enter a positive integer: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        Factorial f = new Factorial();
        int result = f.factorial(number);
        System.out.println("!" + number + " = " + result);
        
    }
}
