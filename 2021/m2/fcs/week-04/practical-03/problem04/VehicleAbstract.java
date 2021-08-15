/**
 * Abstract Class
 *
 * + VehicleAbstract
 * ++ attributes: color, year, numberGears
 */

public abstract class VehicleAbstract implements Vehicle  {
     private String color;
     private String year;
     private int numberGears;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getNumberGears() {
        return numberGears;
    }

    public void setNumberGears(int numberGears) {
        this.numberGears = numberGears;
    }

    public VehicleAbstract(String color, String year, int numberGears) {
        this.color = color;
        this.year = year;
        this.numberGears = numberGears;
    }
     
}
