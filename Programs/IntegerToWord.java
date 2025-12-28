/*	This program converts numbers into its word counterparts.
	This can only handle integer inputs from -999 to 999.
*/

import java.util.Scanner;

public class IntegerToWord {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a # (bet. -999 to 999): ");
        int submit = s.nextInt();                                            // User Input

        int num = Math.abs(submit);                                            // Turns negative input to Positive Input

        int hundreds = num / 100;                                            // Gets the left most digit (hundreds) of the Input

        int ones = num % 10;                                                // Gets the right most digit (ones) of the Input

        int digitMiddleRight = num % 100;                                    // Gets the Middle digit (tens) and Right Digit (ones).

        int tens = digitMiddleRight / 10;                                    // It is now possible to get the middle digit (tens).


        if (num >= -999 && num <= 999) {                                        // Input must be within this range.
            if (submit < 0)                                                    // Checks if USER INPUT is negative.
            {
                System.out.print("Negative ");                                // Prints NEGATIVE. The first word if the USER INPUT is Negative.
            } else if (num == 0)                                                // If Input is 0. then Zero
            {
                System.out.println("Zero");
            } else {
                System.out.print("");                                        // If Input is Positive. Just leave blank.
            }

            if (hundreds >= 1)                                                // Remember that int hundreds was used to get the left most digit.
            {
                switch (hundreds) {
                    case 1:
                        System.out.print("One hundred ");
                        break;
                    case 2:
                        System.out.print("Two hundred ");
                        break;
                    case 3:
                        System.out.print("Three hundred ");
                        break;
                    case 4:
                        System.out.print("Four hundred ");
                        break;
                    case 5:
                        System.out.print("Five hundred ");
                        break;
                    case 6:
                        System.out.print("Six hundred ");
                        break;
                    case 7:
                        System.out.print("Seven hundred ");
                        break;
                    case 8:
                        System.out.print("Eight hundred ");
                        break;
                    case 9:
                        System.out.print("Nine hundred ");
                }
            }

            // This If-Else statement will only run if (USER_INPUT % 100) has a value.

            if (digitMiddleRight >= 11 && digitMiddleRight <= 19)            // If (USER INPUT % 100) has a value between the range 11 to 19.
            {
                switch (digitMiddleRight) {
                    case 11:
                        System.out.print("Eleven ");
                        break;
                    case 12:
                        System.out.print("Twelve ");
                        break;
                    case 13:
                        System.out.print("Thirteen ");
                        break;
                    case 14:
                        System.out.print("Fourteen ");
                        break;
                    case 15:
                        System.out.print("Fifteen ");
                        break;
                    case 16:
                        System.out.print("Sixteen ");
                        break;
                    case 17:
                        System.out.print("Seventeen ");
                        break;
                    case 18:
                        System.out.print("Eighteen ");
                        break;
                    case 19:
                        System.out.print("Nineteen ");
                }
            } else                                                            // if (USER INPUT % 100) is not equal to the values in the range 11-19
            {
                switch (tens)                                                // The middle digit.
                {
                    case 1:
                        System.out.print("Ten ");
                        break;
                    case 2:
                        System.out.print("Twenty ");
                        break;
                    case 3:
                        System.out.print("Thirty ");
                        break;
                    case 4:
                        System.out.print("Forty ");
                        break;
                    case 5:
                        System.out.print("Fifty ");
                        break;
                    case 6:
                        System.out.print("Sixty ");
                        break;
                    case 7:
                        System.out.print("Seventy ");
                        break;
                    case 8:
                        System.out.print("Eighty ");
                        break;
                    case 9:
                        System.out.print("Ninety ");
                }

                switch (ones)                                                // Right most digit.
                {
                    case 1:
                        System.out.print("One ");
                        break;
                    case 2:
                        System.out.print("Two ");
                        break;
                    case 3:
                        System.out.print("Three ");
                        break;
                    case 4:
                        System.out.print("Four ");
                        break;
                    case 5:
                        System.out.print("Five ");
                        break;
                    case 6:
                        System.out.print("Six ");
                        break;
                    case 7:
                        System.out.print("Seven ");
                        break;
                    case 8:
                        System.out.print("Eight ");
                        break;
                    case 9:
                        System.out.print("Nine ");                        // Notice how System.out.print() is commonly used. Since this SOP statement prints on the same line and shit.
                }
            }
        } else {
            System.out.print("Invalid Value!");                                // If the inputted value IS NOT in range -999 to 999.
        }
        s.close();
    }
}