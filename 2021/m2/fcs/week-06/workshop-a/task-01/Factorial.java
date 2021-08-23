
public class Factorial {
    public int factorial(int value){
        if(value == 0){
                return 1;
        }
        else{
                return value * factorial(value - 1);
        }
    }
}