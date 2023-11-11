/* My code is to create an income tax calculator. 
The first step is to ask whether the user is single or married, then based on that information it takes their secondary input (income) and calculates how much tax they must pay.
*/
package Programs;

import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from IncomeTax_Calculate to IncomeTaxCalculator
 * 2. Added package name
 * 3. Optimized imports
 */
public class IncomeTaxCalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your income in lakhs par annum: ");
        float tax = 0;
        float income = sc.nextFloat();
        if (income <= 2.5) {
            tax = tax + 0;
        } else if (income > 5f && income <= 10.0f) {
            tax = tax + 0.05f * (5.0f - 2.5f);
            tax = tax + 0.2f * (income - 5f);
        } else if (income > 10.0f) {
            tax = tax + 0.05f * (5.0f - 2.5f);
            tax = tax + 0.2f * (10.0f - 5f);
            tax = tax + 0.3f * (income - 10.0f);
        }
        System.out.println("The total tax paid by the employee is : " + tax);
    }
}
