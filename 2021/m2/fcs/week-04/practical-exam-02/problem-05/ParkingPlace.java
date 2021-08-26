//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-05
//===================================
public class ParkingPlace {
    
    private String owner;
    private String address;
    private String type;
    private String vehicle;
    private int price;

    public ParkingPlace(String owner, String address, String type, String vehicle) {
        this.owner = owner;
        this.address = address;
        this.vehicle = vehicle;
        if(!"on-street".equals(type) && !"off-street".equals(type))  {
            throw new RuntimeException("the type is not match, please input on-street or off-street");
        }
    }
    
    // sets the owner's name of a parking place
    void setOwner(String tmpOwner) {
        this.owner = tmpOwner;
    }
    
    // returns the owner's name of a parking place 
    String getOwner() {
        return this.owner;
    }
    
    // set the address
    void setAddress(String tmpAddress) {
        this.address = tmpAddress;
    }
    
    // returns the address of a parking place
    String getAddress() {
        return this.address;
    }
    
    // returns the type of a parking place
    String getType() {
        return this.type;
    }
    
    // returns the vehicle's type that a parking place may accommodate
    String getVehicleType() {
        return this.vehicle;
    }
    
    // sets the price of a packing place;
    // price is to be non-negative integer value
    void setPrice(int tmpPrice) {
        this.price = tmpPrice;
    }
    
    // returns the price of a packing place
    int getPrice() {
        return this.price;
    }                                 
    
}
