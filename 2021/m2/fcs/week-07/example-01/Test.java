import java.util.Random;

public class Test{

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
        
        public static void printArray(float [] arr){
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
                
	public static void printArray(String [] arr){
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

	public static void main(String [] args){
		InsertionSort insertion = new InsertionSort();
		SelectionSort selection = new SelectionSort();
                MergeSort merge = new MergeSort();
                QuickSort quick = new QuickSort();
                
		int []arr = {98,23,74,17,29,34,81,72,3};
                merge.setDescending(false);
                merge.sortInt(arr);
                printArray(arr);
                merge.setDescending(true);
                merge.sortInt(arr);
                printArray(arr);

                float[] arr2 = {24,341233,4,15,23,43,4,55};
                merge.setDescending(false);
                merge.sortFloat(arr2);
                printArray(arr2);
                merge.setDescending(true);
                merge.sortFloat(arr2);
                printArray(arr2);
      
                String[] arr3 = {"a","z","c","d","e","f","f","g"};
                merge.setDescending(false);
                merge.sortString(arr3);
                printArray(arr3);
                merge.setDescending(true);
                merge.sortString(arr3);
                printArray(arr3);

	}
}