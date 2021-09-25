
public class MyBST {
    
    private Node root;  // The reference to the root node in this tree

    // initialise an empty tree in the constructor.
    public MyBST() {
        this.root = new Node(0);
    }
    
    // Insert a new value into the tree. This method calls insertRec()
    public void insert(int value) {
        this.root = insertRec(this.root, value);
    }

    // This is a recursive function to insert a new value in the tree.
    private Node insertRec(Node current, int value) {
        if(current == null) {
            return new Node(value);
        }
        else if (value < current.getData()) {
            current.setLeft(insertRec(current.getLeft(), value));
        }
        else if (value > current.getData()) {
            current.setRight(insertRec(current.getRight(), value));
        }
        else {
            System.out.println("Node is in the tree");
        }
        return current;
    }

    // Search a node in the tree. This method calls searchRec()
    public boolean search(int value) {
        return searchRec(this.root, value);
    }

    // This is a recursive function to search a node in the tree.
    private boolean searchRec(Node current, int value) {
        if (current == null) {
            return false;
        } 
        if (value == current.getData()) {
            return true;
        } 
        return value < current.getData()
          ? searchRec(current.getLeft(), value)
          : searchRec(current.getRight(), value);
    }
    
}
