// Program to demonstrate 2D arrays (Hard-coded) - 
// package TwoDArrays;
class TwoD {
	private int i, j;
	// Initializing new array of the order 2 X 2
	private int arr[][] = new int[10][10];
	public void assign() {
		
		System.out.println("Assigning numbers to array: ");
		// Assigning numbers to array
		int n = 5;
			for (i = 0; i <= 3; i++) {
				for (j = 0; j <= 3; j++) {
					arr[i][j] = n * 2;
					n += 5;
				}
			}
	}
	public void display() {
		// Displaying array
		System.out.println("Displaying array: ");
		for (i = 1; i <= 3; i++) {
			for (j = 1; j <= 3; j++) {
				System.out.println("2D Array: " + "["+i+"]" + "["+j+"] : " + arr[i][j]);
			}
		}
	}
}

public class TwoDArrays {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoD obj = new TwoD();
		obj.assign();
		obj.display();
	}
}
