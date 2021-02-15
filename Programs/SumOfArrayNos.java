// Program to add all the numbers in an array - 
package sumOfNos;
import java.util.Scanner;
public class SumOfNos {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0; // Initializing a variable for the sum of the numbers
		int numbers[] = new int[25]; // Initializing an array for integers
		Scanner sc = new Scanner(System.in);
		// For loop to take user input for the array and adding the numbers to the sum variable 
		for (int i = 0; i < 20; i++) {
			System.out.println("Enter number: ");
			numbers[i] = sc.nextInt();
			sum = sum + numbers[i];
		}
		System.out.println("Sum = " + sum);
	}
}
