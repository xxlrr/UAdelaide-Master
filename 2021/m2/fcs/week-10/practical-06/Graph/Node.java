
public class Node {

    //attribute
    private int index;

    //constructor
    Node(){
        //basic constructor
    }

    //parametize constructor
    Node(int index){
        this.index = index;
    }

    //accessors
    public int getIndex(){
        return this.index;
    }

    //mutators
    public void setIndex(int index){
        this.index = index;
    }

    //method to print node
    public void printNode(){
        System.out.print("Node " + this.index);
    }
    
}
