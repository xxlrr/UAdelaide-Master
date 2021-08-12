public class PhysicalBook {
    private int shelf;
    private int aisle;
    private int floor;

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    public void displayBookLocation() {
        System.out.printf("Location: %d floor, %d aisle, %d shelf\n",
                this.getFloor(), this.getAisle(), this.getShelf());
    }
}
