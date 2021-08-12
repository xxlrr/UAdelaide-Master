public class Rectangle extends Shape {
    
    public void display() {
        System.out.println(this.getWidth() + " " + this.getHeight());
    }
    
    public double area() {
        return this.getWidth() * this.getHeight();
    }
    
}
