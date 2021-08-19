
import java.util.Random;


public class Computer extends Player {
    
    public Computer() {
        this.setName("computer");
    }

    @Override
    public String performMove() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random ran = new Random();
        int i = ran.nextInt(3); 
        return choices[i];
    }
    
}
