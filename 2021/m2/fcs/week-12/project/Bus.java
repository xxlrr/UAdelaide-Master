
public class Bus {
    private int busId;
    private int capacity;
    private int numberOfSeatsAvaliable;
    private Seat[] seats;
    private boolean busIsAvailable;

    public Bus(int busId, int capacity) {
        this.busId = busId;
        this.capacity = capacity;
        this.numberOfSeatsAvaliable = capacity;
        this.busIsAvailable = true;
        this.seats = new Seat[capacity];
        for(int i=0; i<capacity; i++) {
            this.seats[i] = new Seat(i+1);
        }
    } 

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfSeatsAvaliable() {
        return numberOfSeatsAvaliable;
    }

    public void setNumberOfSeatsAvaliable(int numberOfSeatsAvaliable) {
        this.numberOfSeatsAvaliable = numberOfSeatsAvaliable;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public boolean isBusIsAvailable() {
        return busIsAvailable;
    }

    public void setBusIsAvailable(boolean busIsAvailable) {
        this.busIsAvailable = busIsAvailable;
    }
    
    public boolean makeReservation(int seatId, String passengerName) {
        int i = seatId - 1;
        if(seats[i].isReserved()) {
            System.out.println("Error! This seat is already reseved.");
            return false;
        }
        seats[i].makeReservation(passengerName);
        numberOfSeatsAvaliable--;
        return true;
    }
    
    public void cancelReservation(int seatNo) {
        seats[seatNo].cancelReservation();
    }
    
    public void printBusInfo() {
        System.out.print("Bus id: " + this.busId);
        for(int i=0; i<capacity; i++) {
            if(i%4 == 0) {
                System.out.println();
            }
            if(seats[i].isReserved()) {
                System.out.print(seats[i].getPassengerName() + " ");
            }
            else {
                System.out.print("Empty ");
            }
        }
        System.out.println();
    }
    
}
