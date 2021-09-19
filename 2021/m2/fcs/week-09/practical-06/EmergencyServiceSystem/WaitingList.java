
public class WaitingList {

    protected Node head;
    protected int numNodes;

    public WaitingList() {
        this.head = null;
        this.numNodes = 0;
    }

    /**
     * This method will pop the first patient from the waiting list
     *
     * @return the patient popped from the waiting list
     */
    public Patient popPatient() {
        if (this.head == null) {
            return null;
        }

        Node node = this.head;
        this.head = node.getNext();
        this.numNodes--;

        return node.getPatient();
    }
    
    /**
     * This method will Pop out one patient by his/her id from the waiting list
     *
     * @return the patient popped from the waiting list
     */
    public Patient popPatient(int id) {
        Node currNode = this.head;
        Node prevNode = null;
               
        while (currNode != null) {
            Patient currPatient = currNode.getPatient();
            if (currPatient.getId() == id) {
                break;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        if(currNode == null) {
            return null;
        }
        else if(prevNode == null) {
            this.head = currNode.getNext();
        }
        else {
            prevNode.setNext(currNode.getNext());
        }
        this.numNodes--;
        
        return currNode.getPatient();
    }
    
    /**
     * This method will search one patient by his/her id from the wait list
     * 
     * @param patientID patient' id
     * @return the patient's index, starting at zero.
     */
    public int indexOf(int patientID) {
        int position = -1;
        
        Node currNode = this.head;
        for(int i=0; i<this.numNodes; i++) {
            Patient currPatient = currNode.getPatient();
            if (currPatient.getId() == patientID) {
                position = i;
                break;
            }
            currNode = currNode.getNext();
        }

        return position;
    }

    /**
     * This method will add patient into the waiting list according to the
     * triage level
     *
     * @param patient patient's data
     */
    public void addToList(Patient patient) {
        Node currNode = this.head;
        Node prevNode = null;

        while (currNode != null) {
            Patient currPatient = currNode.getPatient();
            if (patient.getTriageLevel() > currPatient.getTriageLevel()) {
                break;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        Node newNode = new Node(patient);
        if (prevNode == null) {
            this.head = newNode;
        }
        else {
            prevNode.setNext(newNode);
        }
        newNode.setNext(currNode);

        this.numNodes++;
    }

    /**
     * print out the information for each patient in waiting list
     */
    public void printList() {
        Node current = this.head;
        while (current != null) {
            current.printNode();
            current = current.getNext();
        }
    }

    /**
     * Check whether the patient is in this list or not
     *
     * @return
     */
    public boolean isInList(Patient patient) {
        if (this.head == null) {
            return false;
        }
        Node temp = this.head;
        while (temp != null) {
            if (temp.getPatient().getName().equals(patient.getName())
                    && temp.getPatient().getPhoneNumber().equals(patient.getPhoneNumber())) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

}
