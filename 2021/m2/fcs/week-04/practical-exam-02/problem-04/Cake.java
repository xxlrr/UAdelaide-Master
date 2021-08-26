//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-04
//===================================
public class Cake {
    private String name;
    private float qtSugar;
    private float qtFlour;
    private float qtYeast;
    private double timePrepare;

    // default constructor
    public Cake() {
    }

    // a Constructor containing all parameters
    public Cake(String name, float qtSugar, float qtFlour, float qtYeast, double timePrepare) {
        this.name = name;
        this.qtSugar = qtSugar;
        this.qtFlour = qtFlour;
        this.qtYeast = qtYeast;
        this.timePrepare = timePrepare;
    }

    // accessors
    public String getName() {
        return name;
    }

    public float getQtSugar() {
        return qtSugar;
    }

    public float getQtFlour() {
        return qtFlour;
    }

    public float getQtYeast() {
        return qtYeast;
    }

    public double getTimePrepare() {
        return timePrepare;
    }

    // mutator
    public void setName(String name) {
        this.name = name;
    }

    public void setQtSugar(float qtSugar) {
        this.qtSugar = qtSugar;
    }

    public void setQtFlour(float qtFlour) {
        this.qtFlour = qtFlour;
    }

    public void setQtYeast(float qtYeast) {
        this.qtYeast = qtYeast;
    }

    public void setTimePrepare(double timePrepare) {
        this.timePrepare = timePrepare;
    }
    
}
