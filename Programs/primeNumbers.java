// Program to find out the prime numbers in a given range in Java -
package primeNo;
import java.util.Scanner; // To take input from user 
public class PrimeNo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a, b;
		System.out.println("Enter first value: ");
		a = sc.nextInt();
		System.out.println("Enter last value: ");
		b = sc.nextInt();
		int i, j, num = 0, c = 0;
		System.out.println("List of prime numbers from " + a + " to " + b + ": ");
		for (i = a; i <= b; i++) {
			num = i;
			c = 0;
			j = 2; // Start checking from 2
			while (j < num) {  
				if ((num % j) == 0) {
					c = 1;
					break;
				}
				j++;
			}
			if (c == 0) {
				System.out.println(num);
			}
		}
	}
}
