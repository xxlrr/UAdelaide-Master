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
        
        // handle a integer array
        int[] intArray = {1, 2, 3, 4, 5};
        baseOperations.printArray(intArray);
        baseOperations.reverseArray(intArray);
        int[] newIntArray = baseOperations.reverseArray(intArray);
        baseOperations.printArray(newIntArray);
        
        // handle a string array
        String[] strArray = {"ABC", "DEF", "GHI", "JKL", "MNO"};
        baseOperations.printArray(strArray);
        String[] newStrArray = baseOperations.reverseArray(strArray);
        baseOperations.printArray(newStrArray);
        
        // handle a float array
        float[] floatArray = {1.1f, 2.2f, 3.3f, 4.4f, 5.5f};
        baseOperations.printArray(floatArray);
        float[] newFloatArray = baseOperations.reverseArray(floatArray);
        baseOperations.printArray(newFloatArray);
    }
}

class BaseOperations {
    
    // print a integer array
    void printArray(int[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if(i != array.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
    
    // print a string array
    void printArray(String[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if(i != array.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // print a float array
    void printArray(float[] array) {
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if(i != array.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // reverse a int array
    int[] reverseArray(int[] array) {
        int[] newArray = new int[array.length];
        for(int i=0; i<newArray.length; i++) {
            newArray[i] = array[array.length-1-i];
        }
        return newArray;
    }
    
    // reverse a string array
    String[] reverseArray(String[] array) {
        String[] newArray = new String[array.length];
        for(int i=0; i<newArray.length; i++) {
            newArray[i] = array[array.length-1-i];
        }
        return newArray;
    }
    
    // reverse a float array
    float[] reverseArray(float[] array) {
        float[] newArray = new float[array.length];
        for(int i=0; i<newArray.length; i++) {
            newArray[i] = array[array.length-1-i];
        }
        return newArray;
    }
}
