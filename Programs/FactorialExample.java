/*
 * Factorial of Large numbers in Java using BigInteger.
 * Input - Enter Test cases,Enter Number.
*/
package Programs;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from factoflargenum to FactorialExample
 * 2. Added package name
 * 3. Optimized imports
 */
public class FactorialExample {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            BigInteger bg = BigInteger.ONE;
            for(int i=n;i>0;i--)
                bg = bg.multiply(BigInteger.valueOf(i));
            System.out.println(bg);
        }
    }
}