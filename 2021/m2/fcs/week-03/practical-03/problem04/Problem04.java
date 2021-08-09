//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem04
//===================================

public class Problem04 {

    public static void main(String[] args) {
        // create two complex numbers
        Complex num1 = new Complex(1, 1);
        Complex num2 = new Complex(1, -2);
        
        // addition of the two complex numbers
        Complex sum = ComplexCalculator.add(num1, num2);
        System.out.println("(" + num1 + ") + (" + num2 + ") = (" + sum + ")");
        
        // subtraction of the two complex numbers
        Complex remainder = ComplexCalculator.sub(num1, num2);
        System.out.println("(" + num1 + ") - (" + num2 + ") = (" + remainder + ")");
        
        // multiplication of the two complex numbers
        Complex product = ComplexCalculator.multiplication(num1, num2);
        System.out.println("(" + num1 + ") * (" + num2 + ") = (" + product + ")");
        
        // division of the two complex numbers
        Complex quotient = ComplexCalculator.division(num1, num2);
        System.out.println("(" + num1 + ") / (" + num2 + ") = (" + quotient + ")");
    }
    
}

/**
 * Class Complex is used to store a complex number, and a Complex object
 * has both real part and imaginary part.
 */
class Complex{
    double real;    // read part
    double image;   // imaginary part
    
    Complex(){
        this.real = 0;
        this.image = 0;
    }
    
    Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }
    
    @Override
    public String toString() {
        return this.real + " + " + this.image + "i"; 
    }
}

/**
 * Class ComplexCalculator is used to complex number operations.
 */
class ComplexCalculator{

    // addition of the two complex numbers (add)
    public static Complex add(Complex num1, Complex num2){
        Complex sum = new Complex();
        
        sum.real = num1.real + num2.real;
        sum.image = num1.image + num2.image;
        
        return sum;
    }
    
    // subtraction of the two complex numbers
    public static Complex sub(Complex minuend, Complex subtrahend){
        Complex remainder = new Complex();
        
        remainder.real = minuend.real - subtrahend.real;
        remainder.image = minuend.image - subtrahend.image;
        
        return remainder;
    }
    
    // multiplication of the two complex numbers
    public static Complex multiplication(Complex num1, Complex num2){
        Complex product = new Complex();
        
        product.real = num1.real * num2.real - num1.image * num2.image;
        product.image = num1.image * num2.real +  num1.real * num2.image;
        
        return product;
    }
    
    // division of the two complex numbers
    public static Complex division(Complex dividend, Complex divisor){
        Complex quotient = new Complex();
        
        quotient.real = 
                (dividend.real * divisor.real + dividend.image * divisor.image) 
                / (divisor.real * divisor.real + divisor.image * divisor.image);
        quotient.image =
                (dividend.image * divisor.real - dividend.real *  divisor.image)
                / (divisor.real * divisor.real + divisor.image * divisor.image);
        
        return quotient;
    }
    
}