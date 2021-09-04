//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: MySortAlg
//===================================
public class Main {
    
    public static void printArray(int [] arr){
        String out = "[";
        for(int i = 0; i < arr.length; i++){
            out += arr[i];
            if (i+1 != arr.length){
                out += ", ";
           }
        }
        out += "]";
        System.out.println(out);
    }
    
    // check if these two arrays are exactly the same,
    // return true when they are the same and false when they not
    public static boolean test(int[] result, int[] ans) {
        // if the length is different, directly return false.
        if (result.length != ans.length) {
            return false;
        }
        int count = result.length;
        
        // check if the elements are equal one by one
        for (int i=0; i<count; i++) {
            // if find out any one is different, directly return false.
            if (result[i] != ans[i]) {
                return false;
            }
        }
        // it's all the same to be able to run here.
        return true;
    }
    
    // ignore the order, check whether the elements of the two arrays are the same.
    public static boolean compare(int[] arr1, int[] arr2) {
        // if the length is different, directly return false.
        if (arr1.length != arr2.length) {
            return false;
        }
        // compare, after sorting.
        MergeSort ms = new MergeSort();
        ms.sort(arr1);
        ms.sort(arr2);
        return test(arr1, arr2);
    }
    
    // return the smallest sum of two elements in this array.
    public static int findSmallestSum(int[] array) {
        MergeSort ms = new MergeSort();
        int n = array.length;
        ms.sort(array);
        return array[n-1] + array[n-2];
    }

    public static void main(String[] args) {
        MySortAlg s;
        
        // 1st test cases
        s = new InsertionSort();
        int[] ans1 = {7,6,5,4,3,2,1};
        int[] unSort1 = {4,7,6,5,3,2,1};
        int[] result1 = s.sort(unSort1);
        System.out.println(
            test(result1, ans1)
        );
        
        // 2ed test cases
        s = new MergeSort();
        int[] ans2 = {7,6,5,4,3,2,1};
        int[] unSort2 = {4,7,6,5,3,2,1};
        int[] result2 = s.sort(unSort1);
        System.out.println(
            test(result2, ans2)
        );
        
        // 2ed test cases
        s = new QuickSort();
        int[] ans3 = {7,6,5,4,3,2,1};
        int[] unSort3 = {4,7,6,5,3,2,1};
        int[] result3 = s.sort(unSort1);
        System.out.println(
                test(result3, ans3)
        );

        // tests findSmallestSum
        int[] a = {1,2,3,4,5};
        System.out.println(
            findSmallestSum(a)
        );        
    }
    
}
