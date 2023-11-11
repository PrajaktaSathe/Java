package Programs;

/**
 * BugFix
 * Descriptions:
 * 1. Added public modifier to main class
 * 2. Added package name
 * 3. Optimized imports
 */
public class FactorialDemo {
    public static void main(String args[]) {
        int fact = 0;
        for (int i = 0; i <= 10; i++) {
            fact = factorial(i);
            System.out.println("factorial of " + i + " is: " + fact);
        }

    }

    static int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return (n * factorial(n - 1));
    }

}  
