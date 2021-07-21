// Program to show ArithmeticException Error handling - 
public class arithExceptions {
	public static void main(String[] args) {
		// try block - 
		try {
			int a = 20, b;
			b = a/0;
			System.out.println("Value of b = " + b);
		}
		// catch block - 
		catch(ArithmeticException e) {
			System.out.println("Exception occured!");
		}
		// finally block - 
		finally {
			System.out.println("Quit");
			System.out.println("This block will always execute!");
		}
		// finally block can be used to catch any exception
	}
}
