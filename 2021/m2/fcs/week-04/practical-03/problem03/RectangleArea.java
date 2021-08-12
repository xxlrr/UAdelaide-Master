
import java.util.Scanner;

public class RectangleArea extends Rectangle {
    
    public void read_input() {
        Scanner scanner = new Scanner(System.in);
        try{
            double lenght = scanner.nextDouble();
            double width = scanner.nextDouble();
            
            this.setHeight(lenght);
            this.setWidth(width);
        }
        catch(Exception e){
            System.out.println("Your input do not match. Please try again.");
        }
    }
    
    @Override
    public void display(){
        System.out.println(this.getHeight() + " " + this.getWidth());
        System.out.println(this.area());
    }
    
}
