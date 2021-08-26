//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-03
//===================================
public class HandlingArrays {
    
    public static void printArray(double[] testArray) {
        System.out.print("printing Array: [");
        for(int i=0; i<testArray.length; i++) {
            System.out.print(testArray[i]);
            if(i != testArray.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
     }

    // sum each element of two arrays and return result
    public static double[] sumElements(double[] firstArray, double[] secondArray) {
        if(firstArray.length != secondArray.length) {
            throw new RuntimeException("Error - Arrays different shape");
        }
        
        double[] sumArray = new double[firstArray.length];
        for(int i=0; i<sumArray.length; i++) {
            sumArray[i] = firstArray[i] + secondArray[i];
        }
        
        return sumArray;
    }

    // return a reversed array
    public static double[] reverseArray(double [] testArray) {
        double[] newArray = new double[testArray.length];
        for(int i=0; i<newArray.length; i++) {
            newArray[i] = testArray[newArray.length-1-i];
        }
        return newArray;
    }
    
}
