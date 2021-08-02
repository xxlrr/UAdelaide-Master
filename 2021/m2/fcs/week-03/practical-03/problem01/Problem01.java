//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem01
//===================================
package problem01;


public class Problem01 {
    public static void main(String[] args) {
        // create a new BaseOperations object
        BaseOperations baseOperations = new BaseOperations();  
        
        // print a integer array
        int[] intArray = {1, 2, 3, 4, 5};
        baseOperations.printArray(intArray);
        
        // print a string array
        String[] strArray = {"ABC", "DEF", "GHI", "JKL", "MNO"};
        baseOperations.printArray(strArray);
        
        // print a float array
        float[] floatArray = {1.1f, 2.2f, 3.3f, 4.4f, 5.5f};
        baseOperations.printArray(floatArray);
    }
}

class BaseOperations {
    
    // print a integer array
    void printArray(int[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.println("the array[" + i + "]: " + array[i]);
        }
    }
    
    // print a string array
    void printArray(String[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.println("the array[" + i + "]: " + array[i]);
        }
    }
    
    // print a float array
    void printArray(float[] array) {
        for(int i=0; i<array.length; i++) {
            System.out.println("the array[" + i + "]: " + array[i]);
        }
    }
}
