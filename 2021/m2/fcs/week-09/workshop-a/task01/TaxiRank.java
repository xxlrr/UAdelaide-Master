
class Taxi {

    int taxiID;
    Taxi next;

    Taxi() {
        taxiID = 0;
        next = null;
    }

    Taxi(int newID) {
        taxiID = newID;
        next = null;
    }

}


class TaxiRank {

    Taxi head, tail;

    TaxiRank() {
        head = null;
    }

    void add(Taxi newNode) {
        if (head == null) {
            head = newNode;
        } else {
            if (newNode != null) {
                Taxi current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }
    
    Taxi get() {
        if (head==null) {
            return null;
        } else {
            Taxi returnVal = head;
            head = head.next;
            return returnVal;
        }
    }
    
    void list() {
        System.out.print("TaxiRank:");
        
        Taxi current = head;
        while (current != null) {
            System.out.print(" Taxi" + current.taxiID);
            current=current.next;
        }
        System.out.println();
    }
    
}
