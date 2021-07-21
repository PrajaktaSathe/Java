// Program to show ArrayOutOfBoundsException Error handling - 
package arrayOutOfBounds;
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
