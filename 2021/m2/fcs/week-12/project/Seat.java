
public class Seat {
    private int seatId;
    private boolean isReserved;
    private String passengerName;

    public Seat(int seatId) {
        this.seatId = seatId;
        this.isReserved = false;
        this.passengerName = null;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isReserved() {
        return this.isReserved;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    
    public boolean makeReservation(String passengerName) {
        if(!this.isReserved) {
            this.passengerName = passengerName;
            this.isReserved = true;
            return true;
        }
        else {
            System.out.println("Error! This seat is already reserved.");
            return false;
        }
    }
    
    public void cancelReservation() {
        this.passengerName = null;
        this.isReserved = false;
    }
    
}
