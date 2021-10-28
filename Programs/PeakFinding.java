// Implement the Peak finding algorithm for 1-D array 
import java.util.Scanner;
public class Main{
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the size of the array");
		int size = scanner.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the array");
		for(int i=0;i<size;i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(arr[peakFinder(arr,arr.length-1,0)]);
	}
	public static int peakFinder(int[] arr,int high,int low) {
		int middle = (low+high)/2;
		if(middle < arr.length-1 && arr[middle] < arr[middle+1]) {
			return peakFinder(arr, high, middle+1);
		}
		else if(middle > 0 && arr[middle] < arr[middle-1]) {
			return peakFinder(arr, middle-1, low);
		}
		else {
			return middle;
		}
	}
}
