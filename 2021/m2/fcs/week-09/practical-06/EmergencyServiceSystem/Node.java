public class Node {
    protected Node next;
    protected Patient patient;

    public Node(Patient patient) {
        this.patient = patient;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void printNode() {
        System.out.printf("%-8d %-16s %-8d\n",
            patient.getId(),
            patient.getName(),
            patient.getTriageLevel()
        );
    }
}
