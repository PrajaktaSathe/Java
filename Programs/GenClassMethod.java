package genclass;

public class GenClassMethod {	
	public static <T> void printItems(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arr1[] = {"Cat", "Mouse", "Dog"};
		Integer arr2[] = {1, 2, 3};
		Double arr3[] = {10.5, 3.33, 7.33};
		
		GenClassMethod.printItems(arr1);
		GenClassMethod.printItems(arr2);
		GenClassMethod.printItems(arr3);

	}
}

// Using generic program, implement a stack