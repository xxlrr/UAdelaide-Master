//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: practical-04
//===================================
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // create two players and a referee.
        Human human = new Human();        
        Computer comp = new Computer();        
        Referee referee = new Referee();
        
        // register players' name. 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Firstly, please enter your name: ");
        String humanName = scanner.next();
        human.setName(humanName);
        
        // start game
        System.out.println("All right, let's start the game!");
        while(true){
            // start a round
            System.out.println();
            referee.runARound(human, comp);
            
            // ask the player if they want to continue
            System.out.print("Start again? y/n: ");
            String coutinue = scanner.next();
            if(!"y".equalsIgnoreCase(coutinue)){
                break;
            }
        }
    }

}
