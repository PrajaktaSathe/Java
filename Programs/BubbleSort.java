// Program to sort an array in Java (using bubble sort) - 
public class BubbleSort {
	public static void main(String[] args) {
		int arr[] = {10, 45, 15, 13, 54}; // Initializing an array
		int temp;
		// Two for loops to traverse the array - 
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				// check if element is greater - 
				if (arr[i] > arr[j]) {
					temp = arr[i]; // assign arr[i] element to temp position
					arr[i] = arr[j]; // assign arr[j] element to arr[i] position
					arr[j] = temp; // assign temp value to arr[j] position
				}
			}
		}
		System.out.println("Sorted array: ");
		// For loop to print the sorted array - 
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
