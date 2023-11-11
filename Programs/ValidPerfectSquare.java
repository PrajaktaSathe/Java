package Programs;

import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file name from isValidPerfectSquare.java to ValidPerfectSquare
 * 2. Added package name
 * 3. Optimized imports
 * 4. Added missing main method in order to test
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        long high = (long) num + 1;
        long low = -1;
        while (low != high - 1) {
            long mid = (low + high) / 2;
            if (mid * mid == num) return true;
            else if (mid * mid > num) high = mid;
            else low = mid;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int inputNum = userInput.nextInt();
        ValidPerfectSquare square = new ValidPerfectSquare();
        if (square.isPerfectSquare(inputNum))
            System.out.println("[ " + inputNum + " ] is a perfect square number!");
        else System.out.println("[ " + inputNum + " ] is not a perfect square number!");
    }
}
