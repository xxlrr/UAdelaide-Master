class Shape {

    private double width;
    private double height;
    private double radius;
    private double length;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // Basic Constructor
    public Shape() {
    }
    
    // Paramatic Constructor
    public Shape(double width, double height, double radius, double length) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.length = length;
    }
    
}
