// Java implementation of recursive Binary Search
import java.util.*;
class BinarySearch {
	// Returns index of target if it is present in arr else return -1
	int binarySearch(int arr[], int first, int last, int target)
	{
		if (last >= first) {
			int mid = first + (last - first) / 2;

            //  if element is present in the middle
			if (arr[mid] == target)
				return mid;

			// If element is smaller than mid, then it can only be present in first subarray
			if (target < arr[mid])
				return binarySearch(arr, first, mid - 1, target);

			// Else the element can only be present in the second subarray
			return binarySearch(arr, mid + 1, last, target);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	// Driver method to test above
	public static void main(String args[])
	{
		BinarySearch ob = new BinarySearch();
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int arr[] = new int[n];
		for(int i=0;i<n;i++){
			arr[i]=kb.nextInt();
				}
		int length = arr.length;
		int target = 10;
		int result = ob.binarySearch(arr, 0, length - 1, target);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at index " + result);
			kb.close();
	}
}
