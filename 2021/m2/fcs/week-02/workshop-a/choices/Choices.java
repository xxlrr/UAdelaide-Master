package choices;

import java.util.Scanner;

public class Choices {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input an integer value: ");
        int value = scanner.nextInt();
        if(value > 0 ){
            System.out.println("Your value is positive value!");
        }else{
            System.out.println("Your value is negative value!");
        }
        scanner.close();
    }
    
}
