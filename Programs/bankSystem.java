/* Case Study - 
Using concepts of object-oriented programming develop solution for banking system having the following operations - 
1. Create an account
2. Deposit money
3. Withdraw money
4. Honor daily withdrawal limit
5. Check the balance
6. Display account information
*/

package caseStudy;
import java.util.Scanner;

// class created for storing attributes and methods of account - 
class Account {
	Scanner sc = new Scanner(System.in);
	public String name; // for storing name of account holder
	public String date; // for storing the date on which the account was created
	public String nationality; // for storing nationality of account holder
	public String verifNum; // for storing verification number such as PAN, Aadhar, etc.
	public static int AccNumCnt = 0; // account number counter
	public int AccID = 1000; // for storing account id
	public double balance; // for storing balance
	
	// function to create an account - 
	public void createAccount() {
		System.out.println("Enter your name: ");
		name = sc.nextLine(); // get name
		System.out.println("Enter date: ");
		date = sc.nextLine(); // get date
		System.out.println("Enter nationality: ");
		nationality = sc.nextLine(); // get nationality
		System.out.println("Enter Aadhar/PAN/verification number: ");
		verifNum = sc.nextLine(); // get verification number
		do {
			System.out.println("Basic balance: ");
			balance = sc.nextDouble(); // initial deposit (must be minimum Rs.1000)
			if (balance < 1000) {
				System.out.println("Initial balance should be minimum Rs.1000!");
			}
			else {
				System.out.println("Account created!");
			}
		} while (balance < 1000);
		AccNumCnt++;
		AccID = AccNumCnt; // assigns account id automatically
	}
	
	// function to deposit money in existing account - 
	public void depositMoney() {
		double depAmt = 0;
		System.out.println("Enter amount that you want to deposit: ");
		depAmt = sc.nextDouble();
		balance = balance + depAmt;
		System.out.println("Your new balance is: " + balance);
	}
	
	// function to withdraw money from an existing account - 
	public void withdrawMoney() {
		double witAmt = 0;
		do {
			System.out.println("Enter amount that you want to withdraw: ");
			witAmt = sc.nextDouble();
			// check if withdrawal amount is less than balance, only proceed if true - 
			if (witAmt > balance) {
				System.out.println("Account balance is less! Cannot withdraw...transaction failed!");
			}
		} while (witAmt > balance);
		balance = balance - witAmt;
		System.out.println("Your current balance is: " + balance);
	}
	
	// function to check balance of existing account - 
	public void checkBal() {
		System.out.println("Your current balance is: " + balance);
	}
	
	// function to display account details - 
	public void displayAcc() {
		System.out.println("Displaying account details: ");
		System.out.println("Name: " + name); // displays name
		System.out.println("Account Number: " + AccID); // displays account number
		System.out.println("Current balance: " + balance); // displays account balance
	}
}

// class created for storing attributes and methods of the bank system -
public class BankSystem {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Account obj = new Account(); // create an object of Account class
		// Implementing menu-driven program - 
		int choice = 0;
		System.out.println("WELCOME TO ABC BANK!");
		do {
			System.out.println("How can we help you today?");
			System.out.println("1: Create account ");
			System.out.println("2: Deposit money ");
			System.out.println("3: Withdraw money ");
			System.out.println("4: Check balance ");
			System.out.println("5: Display account information ");
			System.out.println("6: Exit");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				obj.createAccount();
				break;
			case 2:
				obj.depositMoney();
				break;
			case 3:
				obj.withdrawMoney();
				break;
			case 4:
				obj.checkBal();
				break;
			case 5:
				obj.displayAcc();
				break;
			default:
				if (choice != 6) {
					System.out.println("Invalid Input!");
				}
			}
		} while (choice != 6);
	}
}
