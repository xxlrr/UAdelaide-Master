
import java.util.Scanner;

public class RectangleArea extends Rectangle {
    
    public boolean read_input() {
        Scanner scanner = new Scanner(System.in);
            try{
                double height = scanner.nextDouble();
                double width = scanner.nextDouble();

                if(!((1 <= height && height <=1000) && (1 <= width && width <=1000))){
                    System.out.println("Your input out of range. Please try again.");
                    return false;
                }
                
                this.setHeight(height);
                this.setWidth(width);
            }
            catch(Exception e){
                System.out.println("Your input do not match. Please try again.");
                return false;
            }
            return true;
    }
    
    @Override
    public void display(){
        System.out.println(this.getHeight() + " " + this.getWidth());
        System.out.println(this.area());
    }
    
}
