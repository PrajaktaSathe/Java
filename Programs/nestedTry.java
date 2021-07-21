package nestedBlocks;

public class NestedTry {

	public static void main(String[] args) {
		int a = 20, b;
		int ch[] = new int[20];
		try {
			b = a/0;
			try {
				System.out.println("Value of b = " + b);
			}	
			catch(Exception e) {
				System.out.println("Inner block Exception occured!");
			}
		}
			catch(Exception e) {
				System.out.println("Outer block exception occured!");
			}
			finally {
				System.out.println("Quit");
				System.out.println("This block will always execute!");
			}
				// finally block can be used to catch any exception
	}
}

/* Note: Inner catch will use the outer catch block but outer catch will not use inner catch block exception 
 * */
