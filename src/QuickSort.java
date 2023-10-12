import java.util.ArrayList;

import com.sun.tools.javac.util.List;

public class QuickSort {
	public static void main(String[] args) {
		
	} 
	public static void sort(int[] arr){
		if (arr.length>1){
			int pivot= arr[0];
			int temp;
			List<Integer> left = new ArrayList<>();
			List<Integer> right = new ArrayList<>();
			for (int i=0; i<arr.length;i++){
				if (arr[i] <=pivot){
					left.add(arr[i]);
				}
				else right.add(arr[i]);
			}
			sort(new int[]{left});
			sort(new int[]{right});
			int[] r = new int[left.length + right.length];
			for (int i=0;i<left.length;i++)
				r[i] = left[i];
			for (int i=0;i<right.length;i++)
				r[i+left.length] = right[i];
			for (int i=0;i<r.length;i++)
				arr[i] = r[i];
		}


	}
}
