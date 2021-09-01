
public class InsertionSort{

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
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && ((!this.isDescending() && arr[j] > key)
                    || (this.isDescending() && arr[j] < key))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
    
    public float[] sortFloat(float [] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            float key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && ((!this.isDescending() && arr[j] > key)
                    || (this.isDescending() && arr[j] < key))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
    
    public String[] sortString(String [] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            String key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && ((!this.isDescending() &&  arr[j].compareTo(key) > 0)
                    || (this.isDescending() &&  arr[j].compareTo(key) < 0))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}