/**
 * Children Classes
 * 
 * + Electric Bike
 * ++ attributes: battery;
 * ++ methods: chargeBike();
 */

public class ElectricBike {
    private int battery;

    public int getBattery() {
        return battery;
    }
    
    public void chargeBike() {
        this.battery = 100;
        System.out.println("charge completed!");
    }
    
}
