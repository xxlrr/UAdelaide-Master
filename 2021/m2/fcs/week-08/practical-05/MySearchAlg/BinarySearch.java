//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Practical-05
//===================================

// binary serach class
public class BinarySearch extends MySearchAlg {

    // find the num and return the index of that number
    @Override
    int search(int[] array, int num) {
        int l=0;
        int r=array.length;
        return this.search(array, l, r, num);
    }
    
    private int search(int arr[], int l, int r, int x){
        
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the 
            // middle itself
            if (arr[mid] == x)
               return mid;
 
            // If element is smaller than mid, then 
            // it can only be present in left subarray
            if (arr[mid] > x)
               return search(arr, l, mid-1, x);
 
            // Else the element can only be present
            // in right subarray
            return search(arr, mid+1, r, x);
        }
 
        // We reach here when element is not present
        //  in array
        return -1;
    }
    
}
