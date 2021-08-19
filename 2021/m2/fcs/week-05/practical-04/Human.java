
import java.util.InputMismatchException;
import java.util.Scanner;

public class Human extends Player {

    public Human() {
        this.setName("human");
    }

    @Override
    public String performMove() {
        System.out.println("Please enter your choice from Rock, Paper or Scissors!");

        Scanner scanner = new Scanner(System.in);
        String choice;
        while (true) {
            try {
                System.out.print("Your choice: ");
                choice = scanner.next();
                if ("Rock".equals(choice) || "Paper".equals(choice) || "Scissors".equals(choice)) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Sorry, Please enter Rock, Paper or Scissors!");
            }
        }

        return choice;
    }

}
