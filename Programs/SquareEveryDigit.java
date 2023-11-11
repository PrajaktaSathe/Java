package Programs;

import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from SquareDigit to SquareEveryDigit
 * 2. Added package name
 * 3. Optimized imports
 */
public class SquareEveryDigit {

    public int squareDigits(int n) {

        String strDigits = String.valueOf(n);
        String result = "";

        for (char c : strDigits.toCharArray()) {
            int digit = Character.digit(c, 10);
            result += digit * digit;
        }

        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int userNum = userInput.nextInt();
        SquareEveryDigit squareEveryDigit = new SquareEveryDigit();
        System.out.println("Square of a digit [ " + userNum + " ] is " + squareEveryDigit.squareDigits(userNum));
    }

}
