/*
    import LinkedList from java.util
    Reference: https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
*/
import java.util.LinkedList;
 
public class Graph{

    // Number of vertices in the graph
    private int V; 
    // Array of linked list;
    LinkedList<Integer> adjListArray[];
     
    // Basic constructor required number of vertices
    Graph(int V){
        // set number of vertices
        this.V = V;
         
        // define the size of array as number of vertices
        adjListArray = new LinkedList[V];
         
        // Create a new list for each vertex such that adjacent nodes can be stored
        for(int i = 0; i < V ; i++){
            adjListArray[i] = new LinkedList<>();
        }
    }
    /*  Adds an edge to an undirected graph,
        this method perform a adjacent addition    
        it uses the method "addFirst" which includes
        the element in the beggining of the linked list */
    public void addEdge(int src, int dest){
        // Add an edge from src to dest. 
        // the src represents a index in the array of linkedlists
        this.adjListArray[src].addFirst(dest);
         
        // Since graph is undirected, add an edge from dest to src also
        this.adjListArray[dest].addFirst(src);
    }

    // A utility function to print the adjacency list 
    // representation of graph
    public void printGraph(){       
        for(int v = 0; v < this.V; v++){
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer pCrawl: this.adjListArray[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }

}       
