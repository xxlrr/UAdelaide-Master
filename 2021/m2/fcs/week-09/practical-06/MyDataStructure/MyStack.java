
public class MyStack {

    private Node top;

    MyStack() {
        this.top = null;
    }

    // Push new element on top of the stack
    public void push(Node node) {
        node.setNext(this.top);
        this.top = node;
    }

    // Pop an element from the top of the stack
    public Node pop() {
        // Check if it's empty
        if (this.isEmpty()) {
            return null;
        }

        Node tmpTop = this.top;
        this.top = this.top.getNext();
        return tmpTop;
    }

    // Get the top node in this stack. 
    // Return Null and print "Stack is empty" when the stack is empty.
    public Node peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.top;
    }

    // Return TRUE when the stack is empty, otherwise, return FALSE.
    public boolean isEmpty() {
        return this.top == null;
    }

}
