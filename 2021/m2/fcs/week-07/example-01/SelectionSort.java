
public class SelectionSort {
    
    // controls weather to sort in descending order.
    private boolean descending = false;

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    public int[] sortInt(int [] arr) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if ((!this.isDescending() && arr[j] < arr[min_idx])
                        || (this.isDescending() && arr[j] > arr[min_idx]))
                    min_idx = j;
            // Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
    
    public float[] sortFloat(float [] arr) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if ((!this.isDescending() && arr[j] < arr[min_idx])
                        || (this.isDescending() && arr[j] > arr[min_idx]))
                    min_idx = j;
            // Swap the found minimum element with the first element
            float temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
    
    public String[] sortString(String [] arr) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if ((!this.isDescending() && arr[j].compareTo(arr[min_idx]) < 0)
                        || (this.isDescending() && arr[j].compareTo(arr[min_idx]) > 0))
                    min_idx = j;
            // Swap the found minimum element with the first element
            String temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
    
}