package Programs;

import java.util.*;

public class Fibo {
      
      public static void main(String[] args) {
            // Print first n Fibonacci numbers
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter number of terms: ");
            int n = sc.nextInt();
            sc.close();
            
            int a = 0, b = 1;
            System.out.println("Fibonacci Series:");
            for (int i = 0; i < n; i++) {
                  System.out.print(a + " ");
                  int c = a + b;
                  a = b;
                  b = c;
            }
            
            // Dynamic Programming approach to find nth Fibonacci number
            int term = 10; // Change this to desired term index
            System.out.println("\n\n" + term + "th Fibonacci number using DP: " + fibdp(term));
      }
      
      // Bottom-up Dynamic Programming approach
      public static int fibdp(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;
            
            int[] storage = new int[n + 1];
            storage[0] = 0;
            storage[1] = 1;
            
            for (int i = 2; i <= n; i++) {
                  storage[i] = storage[i - 1] + storage[i - 2];
            }
            
            return storage[n];
      }
}
