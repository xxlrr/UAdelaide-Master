//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem05
//===================================
package problem05;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Problem05 {

    public static void main(String[] args) {
        Determinant determinant;

        try{
            // ask the user weather to perform a determiant calculation.
            Scanner scanner = new Scanner(System.in);        
            System.out.print("Welcome to Matrix Determinant Calculator!\n"
                    + "Would you like to calculate the determinant of a new matrix?\n"
                    + "1. Yes, 2. No\n\n"
                    + "Option:");
            int option = scanner.nextInt();
            if(option == 2) {
                return;
            }
            else if(option != 1){
                System.out.println("Input error, please try again!");
                return;
            }

            // get the matrix type and initialize the determinant variable.
            System.out.print("\nWhat is the dimension of the matrix?\n" 
                    + "Input:");
            String type = scanner.next();
            if(type.equals("2x2")) {
                determinant = new Determinant2();
            }
            else if(type.equals("3x3")) {
                determinant = new Determinant3();
            }
            else {
                System.out.println("Input error, only support 2x2 or 3x3");
                return;
            }

            // prompt and receive matrices of user input.
            System.out.println("\nGreat! Let's calculate this determinant then.\n"
                    + "Please insert all the values for your matrix.\n");
            determinant.inputMatrices();        

            // print the matrices in a square style.
            System.out.println("\nThe determinant of your matrix is: \n");
            determinant.printMatrices();
            
            // calculate determinant and output result.
            double result = determinant.calcDeterminant();
            System.out.println("\nDeterminant: " + String.format("%4.0f", result));
        }
        // handle wrong inputs
        catch(InputMismatchException e){
            System.out.println("[ERROR]: your input does not match, please try again!");
        }
        // handle other exceptions
        catch(Exception e){
            System.out.println("Sorry, the program encountered an unknown error."
                    + " Please try again");
        }
    }
    
}

/**
 * This is a base class that provides methods for input/output matrices
 * and an abstract function  calculate the determinant。
 */
abstract class Determinant {
    double[][] matrices;
    
    // prompts and receives the user to input matrices.
    public void inputMatrices() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<matrices.length; i++) {
            for(int j=0; j<matrices[i].length; j++) {
                System.out.print("New matrix [" + (i+1) + "][" + (j+1) + "]: ");
                matrices[i][j] = scanner.nextDouble();
            }
        }
    }
    
    // print the matrices
    public void printMatrices() {
        for(int i=0; i<matrices.length; i++) {
            System.out.print("| ");
            for(int j=0; j<matrices[i].length; j++) {
                System.out.print(String.format("%4.0f", matrices[i][j]));
                if(j < matrices[i].length - 1) {
                    System.out.print(","); 
                }
            }
            System.out.println(" |");
        }
    }
    
    // calculates determinant
    abstract public double calcDeterminant();
}

/**
 * The class a 2x2 matrix that provides input and output matrices
 * and a method for calculating the determinant。
 */
class Determinant2 extends Determinant {
    
    Determinant2(){
        matrices = new double[2][2];
    }

    // calculates determinant
    @Override
    public double calcDeterminant() {
        double[][] matrices = this.matrices;
        return matrices[0][0] * matrices[1][1] - matrices[0][1] * matrices[1][0];
    }

}

/**
 * The class a 3x3 matrix that provides input and output matrices
 * and a method for calculating the determinant。
 */
class Determinant3 extends Determinant {
    
    Determinant3(){
        matrices = new double[3][3];
    }

    // calculates determinant
    @Override
    public double calcDeterminant() {
        double[][] matrices = this.matrices;
        return matrices[0][0] * matrices[1][1] * matrices[2][2]
             + matrices[0][1] * matrices[1][2] * matrices[2][0]
             + matrices[0][2] * matrices[1][0] * matrices[2][1]
             - matrices[2][0] * matrices[1][1] * matrices[0][2]
             - matrices[2][1] * matrices[1][2] * matrices[0][0]
             - matrices[2][2] * matrices[1][0] * matrices[0][1];
    }

}