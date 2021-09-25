//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: 
//===================================
public class Main {

    public static void main(String[] args) {
        MyGraph graph = new MyGraph();

        int [][] array = {{0, 1, 0, 1}, {1, 0, 0, 0}, {0, 0, 0, 1}, {0, 1, 1 ,0}};
        graph.matrixToList(array); 
        System.out.println("Output for displayAdjListArray: "); 
        graph.displayAdjListArray();
    }

}
