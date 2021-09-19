
public class MyQueue {

    private Node front, rear; // The reference to the first and last node in this queue

    // You should initialise the an empty queue in the constructor.
    public MyQueue() {
        this.front = null;
        this.rear = null;
    }

    // Insert one node at the end of the queue.
    public void enqueue(Node node) {
        if (this.isEmpty()) {
            this.front = node;
        } else {
            this.rear.setNext(node);
        }
        this.rear = node;
    }

    // Get and remove the front node from the queue. 
    // Return Null and print "Queue is empty" when the queue is empty
    public String dequeue() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        String data = this.front.getData();
        this.front = this.front.getNext();

        return data;
    }

    // Return TRUE when the queue is empty, otherwise, return FALSE.
    public boolean isEmpty() {
        return this.front == null || this.rear == null;
    }

    // Print out the data saved in nodes from the first to the last. 
    // Return and print "Queue is empty" when the queue is empty
    // You should call printNode() to print each node in this method.
    public void displayQueue() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node tmpNode = this.front;
        while (tmpNode != null) {
            tmpNode.printNode();
            tmpNode = tmpNode.getNext();
        }
    }

}
