// Program to demonstrate the use of if-else if-else statements -
package largestNum;
import java.util.Scanner;
public class LargestNum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x;
		double y;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first number: ");
		x = sc.nextDouble();
		System.out.println("Enter second number: ");
		y = sc.nextDouble();
		if (x > y) {
			System.out.println("X is greater than Y.");
		}
		else if (y > x) {
			System.out.println("Y is greater than X");
		}
		else {
			System.out.println("X and Y are equal.");
		}
	}
}
