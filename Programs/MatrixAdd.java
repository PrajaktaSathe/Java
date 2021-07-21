// Program to add two matrices in Java - 
package matrix;
import java.util.Scanner;
public class MatrixAdd {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int arr1[][] = new int[10][10];  // New 2D array for first matrix
		int arr2[][] = new int[10][10];  // New 2D array for second matrix
		int sum[][] = new int[10][10];  // New 2D array for sum of the two matrices
		int i, j;
		// Taking details of first matrix -
		System.out.println("Enter details for first matrix: ");
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				System.out.println("Enter number: ");
				arr1[i][j] = sc.nextInt();
			}
		}
		// Taking details of second matrix -
		System.out.println("Enter details for second matrix: ");
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				System.out.println("Enter number: ");
				arr2[i][j] = sc.nextInt();
			}
		}
		System.out.println("Displaying addition of two matrices: ");
		// Performing addition of two matrices - 
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				sum[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
		// Displaying addition of two matrices - 
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				System.out.print(sum[i][j] + " ");
			}
			System.out.println();
		}
	}
}
