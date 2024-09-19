// This program demonstrates a new and most efficient way to calculate number of digits by using logarithmic functions
/* the normal and most used algorithm to calculate number of digits is
while(num>0){
numberOfDigits=num%10;
numberOfDigits++;
num=num%10;
}
this algorithm has a time complexity of O(n)
we can simply calculate it by log(number)+1.
here is the code for it :- */
import java.util.Scanner;

class NumberOfDigits{
	public static void main(String[] args)
	{
		Scanner sc = new Scaneer(System.in);

		System.out.print("Enter a number");
		int num = sc.nextInt();

		if (num == 0)
			System.out.print("Number of digits: 1");

		else
		{
			int digits = (int)Math.log10(Math.abs(num))+1;

			System.out.println("Number of digits: " + digits);
		}
		sc.close();
	}
}

