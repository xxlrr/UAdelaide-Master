/*
*   Foundations of Computer Science
*   2018, Semester 01
*   Practical-Exam-04
*
*   student (id): a111111
*   student (name): John Smitth
*
* Note: in order to finish your exam you need to make changes in this class
* Problem 03
 */
public class Selection extends Sort {

    @Override
    public int[] sortIntByIndex(int[] arr) {
        int[] index = this.getIndex(arr.length);
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < arr.length - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

            int tempIndex = index[min_idx];
            index[min_idx] = index[i];
            index[i] = tempIndex;
        }
        return index;
    }

    @Override
    public int[] sortInt(int[] arr) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    @Override
    public int[] sortStringByIndex(String[] arr) {
        int[] index = this.getIndex(arr.length);
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < arr.length - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min_idx]) < 0) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            String temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

            int tempIndex = index[min_idx];
            index[min_idx] = index[i];
            index[i] = tempIndex;
        }
        return index;
    }

    @Override
    public String[] sortString(String[] arr) {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[min_idx]) < 0) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            String temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

}
