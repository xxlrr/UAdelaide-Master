
import java.util.LinkedList;


public class MyGraph {
    LinkedList<Node> adjListArray[]; 
    
    // You should initialise an empty graph in the constructor.
    public MyGraph() {
        adjListArray = null;
    }

    // transform an adjacency matrix to an adjacency list
    public void matrixToList(int[][] matrix) {
        // get the number of vertices (nodes)
        int v = matrix.length;
        
        // create a new list for each vertex
        adjListArray = new LinkedList[v];
        for(int i=0; i<v; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                if(matrix[i][j] == 1) {
                    adjListArray[i].add(new Node(j));
                }
            }
        }
    }

    // Print out the adjacency list array
    public void displayAdjListArray() {
        for(int i = 0; i<adjListArray.length; i++){
            System.out.print(i+": ");
            for(int j=0; j<adjListArray[i].size(); j++){
                adjListArray[i].get(j).printNode();
                if(j != adjListArray[i].size()-1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

}
