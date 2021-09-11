//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Practical-05
//===================================

public class Main {
    
    // test method should compare if these two integers are the same,
    // return true when they are the same and false when they not.
    private static boolean test(int result, int ans) {
        return result == ans;
    }
    
    // return the index value for the maximum number in this array
    private static int findMaxVal(int array[]) {
        int index = 0;
        for (int i=0; i<array.length; i++) {
            if(array[i] > array[index]) {
                index =i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
       
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10};
        int[] arr2 = {10,9,8,7,6,5,4,3,2,1};
        int[] arr3 = {-5,-4,-3,-2,-1,0,1,2,3,4,5};
        
        MySearchAlg s;
        // test linear search
        s = new LinearSearch();
        System.out.println(test(s.search(arr1, 5), 4));
        System.out.println(test(s.search(arr2, 5), 5));
        System.out.println(test(s.search(arr3, 5), 10));
        
        // test binary search
        s = new BinarySearch();
        System.out.println(test(s.search(arr1, 5), 4));
        System.out.println(test(s.search(arr2, 5), 5));
        System.out.println(test(s.search(arr3, 5), 10));

    }

}
