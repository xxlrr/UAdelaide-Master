//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-04
//===================================
public class Car {
    private String model;
    private int numGears;
    private float literTank;
    private int yearManufacture;

    // default constructor
    public Car() {
    }

    // a Constructor containing all parameters
    public Car(String model, int numGears, float literTank, int yearManufacture) {
        this.model = model;
        this.numGears = numGears;
        this.literTank = literTank;
        this.yearManufacture = yearManufacture;
    }

    // accessors
    public String getModel() {
        return model;
    }

    public int getNumGears() {
        return numGears;
    }

    public float getLiterTank() {
        return literTank;
    }

    public int getYearManufacture() {
        return yearManufacture;
    }
    
    
    //mutator
    public void setModel(String model) {
        this.model = model;
    }

    public void setNumGears(int numGears) {
        this.numGears = numGears;
    }

    public void setLiterTank(float literTank) {
        this.literTank = literTank;
    }

    public void setYearManufacture(int yearManufacture) {
        this.yearManufacture = yearManufacture;
    }
    
    
}
