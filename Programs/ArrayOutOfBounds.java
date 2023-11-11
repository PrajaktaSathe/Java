// Program to show ArrayOutOfBoundsException Error handling - 
package Programs;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file from arrayOutOfBounds to ArrayOutOfBounds
 * 2. Added package name
 */
public class ArrayOutOfBounds {

	public static void main(String[] args) {
		int arr[] = new int[20];
		try {
			arr[22] = 60;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Enception occured");
		}
		System.out.println("Quit");
	}
}
