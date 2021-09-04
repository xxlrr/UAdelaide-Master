//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: MySortAlg
//===================================

public class QuickSort extends MySortAlg {

    @Override
    public int[] sort(int[] array) {
        return this.sortInt(array, 0, array.length-1);
    }
    
    private int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; 
        // Index of smaller element and indicates the right position of pivot found so far
        int i = (low - 1); 
        for(int j = low; j <= high - 1; j++)
        {
            if (arr[j] > pivot)
            {
                // Increment index of smaller element
                i++; 
                // swap(arr, i, j);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
            }
        }
        // swap(arr, i + 1, high);
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }
    
    private int[] sortInt(int[] arr, int low, int high)
    {
        if (low < high) 
        {
            // pi is partitioning index, arr[p] is now at right place 
            int pi = partition(arr, low, high);
            // Separately sort elements before partition and after partition
            sortInt(arr, low, pi - 1);
            sortInt(arr, pi + 1, high);
        }
        return arr;
    }
    
}
