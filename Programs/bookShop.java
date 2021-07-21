package bookShop;
import java.util.Scanner;

// class for book shop inventory management- 
public class bookShop {
	Scanner sc = new Scanner(System.in);
	String genre; // genre of book
	int engg; // engineering genre
	int nonfiction; // non-fiction genre
	int fiction; // fiction genre
	int childrens; // children's genre
	int comics; // comics genre
	int classics; // classics genre
	int ch;
	
	// constructor for initializing genres to 0 - 
	bookShop() {
		engg = 0;
		nonfiction = 0;
		fiction = 0;
		childrens = 0;
	}
	
	// function to add books to stock -
	public void addToStock() {
		System.out.println("Menu: ");
		System.out.println("1. Engineering");
		System.out.println("2. Non-fiction");
		System.out.println("3. Fiction");
		System.out.println("4. Children's ");
		System.out.println("5. Comics");
		System.out.println("6. Classics");
		System.out.println("7. Exit");
		do {
			System.out.println("Enter genre of book: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				engg++;
				break;
			case 2:
				nonfiction++;
				break;
			case 3:
				fiction++;
				break;
			case 4:
				childrens++;
				break;
			case 5:
				comics++;
				break;
			case 6:
				classics++;
				break;
			default:
				if (ch != 7) {
					System.out.println("Invalid input! Please enter correct choice!");
				}
			}
		} while (ch != 7);
	}
	
	// function to update inventory - 
	public void updateInventory() {
		System.out.println("Menu: ");
		System.out.println("1. Engineering");
		System.out.println("2. Non-fiction");
		System.out.println("3. Fiction");
		System.out.println("4. Children's ");
		System.out.println("5. Comics");
		System.out.println("6. Classics");
		System.out.println("7. Exit");
		do {
			System.out.println("Enter genre of book that was purchased by customer: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				engg--;
				break;
			case 2:
				nonfiction--;
				break;
			case 3:
				fiction--;
				break;
			case 4:
				childrens--;
				break;
			case 5:
				comics--;
				break;
			case 6:
				classics--;
				break;
			default:
				if (ch != 7) {
					System.out.println("Invalid input! Please enter correct choice!");
				}
			} 
		} while (ch != 7);
	}
	
	// function to display details of book inventory - 
	public void displayInventory() {
		System.out.println("Displaying inventory details: ");
		System.out.println("Number of books available in inventory - ");
		System.out.println("Genre \t No. of books");
		System.out.println("Engineering: \t" + engg);
		System.out.println("Non-Fiction: \t" + nonfiction);
		System.out.println("Fiction: \t" + fiction);
		System.out.println("Children's: \t" + childrens);
		System.out.println("Comics: \t" + comics);
		System.out.println("Classics: \t" + classics);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to book shop inventory management portal!");
		bookShop book = new bookShop();
		int choice;
		do {
			System.out.println("What do you want to do?");
			System.out.println("1. Add to stock");
			System.out.println("2. Update inventory");
			System.out.println("3. Display inventory details");
			System.out.println("4. Exit portal");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				book.addToStock();
				break;
			case 2:
				book.updateInventory();
				break;
			case 3:
				book.displayInventory();
				break;
			default:
				if (choice != 4) {
					System.out.println("Invalid Input!");
				}
			}
		} while (choice != 4);
	}
}
