
public class QuickSort {
    
    // controls weather to sort in descending order.
    private boolean descending = false;

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }
    
    private int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; 
        // Index of smaller element and indicates the right position of pivot found so far
        int i = (low - 1); 
        for(int j = low; j <= high - 1; j++)
        {
            if ((!this.isDescending() && arr[j] < pivot) 
                    || (this.isDescending() && arr[j] > pivot)) 
            {
                // Increment index of smaller element
                i++; 
                // swap(arr, i, j);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
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

    public int[] sortInt(int[] arr) {
        return this.sortInt(arr, 0, arr.length-1);
    }

    private int partition(float[] arr, int low, int high)
    {
        float pivot = arr[high]; 
        // Index of smaller element and indicates the right position of pivot found so far
        int i = (low - 1); 
        for(int j = low; j <= high - 1; j++)
        {
            if((!this.isDescending() && arr[j] < pivot)
                    || (this.isDescending() && arr[j] > pivot))
            {
                i++; 
                // swap(arr, i, j);
                float temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap(arr, i + 1, high);
        float temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }
    
    private float[] sortFloat(float[] arr, int low, int high)
    {
        if (low < high) 
        {
            // pi is partitioning index, arr[p] is now at right place 
            int pi = partition(arr, low, high);
            // Separately sort elements before partition and after partition
            sortFloat(arr, low, pi - 1);
            sortFloat(arr, pi + 1, high);
        }
        
        return arr;
    }

    public float[] sortFloat(float[] arr) {
        return this.sortFloat(arr, 0, arr.length-1);
    }
    
    private int partition(String[] arr, int low, int high)
    {
        String pivot = arr[high]; 
        // Index of smaller element and indicates the right position of pivot found so far
        int i = (low - 1); 
        for(int j = low; j <= high - 1; j++)
        {
            // If current element is smaller than the pivot
            if ((!this.isDescending() && arr[j].compareTo(pivot) < 0)
                    || (this.isDescending() && arr[j].compareTo(pivot) > 0)) 
            {
                i++; 
                // swap(arr, i, j);
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap(arr, i + 1, high);
        String temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }
    
    private String[] sortString(String[] arr, int low, int high)
    {
        if (low < high) 
        {
            // pi is partitioning index, arr[p] is now at right place 
            int pi = partition(arr, low, high);
            // Separately sort elements before partition and after partition
            sortString(arr, low, pi - 1);
            sortString(arr, pi + 1, high);
        }
        
        return arr;
    }

    public String[] sortString(String[] arr) {
        return this.sortString(arr, 0, arr.length-1);
    }

}
