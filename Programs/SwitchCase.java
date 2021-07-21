// Program to demonstrate switch case statements in Java 
package switchCase;
import java.util.Scanner;
public class SwitchCase {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number from 1 to 5: ");
		int num = sc.nextInt();
		switch (num) {
		case 1: 
			System.out.println("You have entered 1.");
			break;
		case 2:
			System.out.println("You have entered 2.");
			break;
		case 3:
			System.out.println("You have entered 3.");
			break;
		case 4:
			System.out.println("You have entered 4.");
			break;
		case 5:
			System.out.println("You have entered 5.");
			break;
		default:
			System.out.println("You have entered a number less than 1 or greater than 5.");
		}
	}
}
