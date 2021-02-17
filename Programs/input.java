import java.util.Scanner; // import required files for input

public class MyInput {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in); // create new object of the scanner class
		int x;
		System.out.println("Enter a number: "); // displaying a message (prompt) for the user input
		x = sc.nextInt(); // save the user input as variable x
		System.out.println("Number is: " + x); 
	}
}