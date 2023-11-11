package Programs;

import java.util.*;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from fibo to FibonacciExample
 * 2. Added package name
 * 3. Optimized imports
 * 4. Re-formatted code as per Java Coding practice
 */
public class FibonacciExample {

    public static void main(String[] args) {
        // write your code here

        int a = 0;
        int b = 1;

        Scanner sc = new Scanner(System.in);

        int iteratorCount = sc.nextInt();
        sc.close();

        for (int i = 0; i < iteratorCount; i++) {
            System.out.print(a + " ");
            int c = a + b;
            a = b;
            b = c;
        }
        // fibbnacci using dynamic programming
        int n = 44;
        System.out.println(fibdp(n));
    }

    public static int fibdp(int n) { // bottom up approach
        int storage[] = new int[n + 1];
        storage[0] = 0;
        storage[1] = 1;
        for (int i = 2; i <= n; i++) {
            storage[i] = storage[i - 1] + storage[i - 2];
        }
        return storage[n];
    }
}
