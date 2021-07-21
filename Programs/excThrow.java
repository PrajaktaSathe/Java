package exceptThr;

class t {
	public t() {
		try {
			throw new NullPointerException("Null pointer exception");
		}
		catch(NullPointerException e) {
			System.out.println("Caught!");
			throw e; // Rethrow an exception
		}
	}
}

public class ExceptThrow {
	public static void main(String[] args) {
		try {
//			throw new ArithmeticException("Hello"); // "Hello" is a parameter
			t obj = new t();
		}
//		catch(ArithmeticException e) {
//			System.out.println(e.getMessage());
//		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
			// e.getLocalizedMessage() and e.getMessage() prints out the same output currently
			System.out.println("Recaught!");
		}
		finally {
			System.out.println("Finally block will always be executed!");
		}
	}
}
