/*
* The class Node consists of the basic element for linked lists;
* The linked list is a set of linked nodes;
 */
public class Node {

    // information
    private String data;
    // pointing
    private Node next;

    // Basic Constructor for class Node, set value = 0 and next = null
    Node() {
        //this.setData(0);
        this.data = "";
        this.next = null;
    }

    // Constructor that takes value and set next = null
    Node(String data) {
        this.data = data;
        this.next = null;
    }

    // Constructor takes value and next element to point
    Node(String data, Node next) {
        this.data = data;
        this.next = null;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    // Print the data saved in the node. 
    public void printNode() {
        System.out.println("Node data: " + this.getData());
    }

}
