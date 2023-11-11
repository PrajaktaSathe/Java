package Programs;

class CustomNullPointerException {
    public CustomNullPointerException() {
        try {
            throw new NullPointerException("Null pointer exception");
        } catch (NullPointerException e) {
            System.out.println("Caught!");
            throw e; // Rethrow an exception
        }
    }
}

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from excThrow to ExceptionThrowExample
 * 2. Added package name
 * 3. Optimized imports
 */
public class ExceptionThrowExample {
    public static void main(String[] args) {
        try {
//			throw new ArithmeticException("Hello"); // "Hello" is a parameter
            CustomNullPointerException obj = new CustomNullPointerException();
        }
//		catch(ArithmeticException e) {
//			System.out.println(e.getMessage());
//		}
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            // e.getLocalizedMessage() and e.getMessage() prints out the same output currently
            System.out.println("Recaught!");
        } finally {
            System.out.println("Finally block will always be executed!");
        }
    }
}
