package Programs;

import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from MyInput to UserInputExample
 * 2. Added package name
 * 3. Optimized imports
 */
public class UserInputExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // create new object of the scanner class
        int x;
        System.out.println("Enter a number: "); // displaying a message (prompt) for the user input
        x = sc.nextInt(); // save the user input as variable x
        System.out.println("Number is: " + x);
    }
}