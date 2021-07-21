package excHandling;
import java.util.*;
import java.io.*;

public class ArrayListProg {
	public static void main(String[] args) throws IOException {
		System.out.println("Program for implementing ArrayList: ");
		ArrayList<Character> obj = new ArrayList<Character>();
		
		Scanner sc = new Scanner(System.in);
		char ans;
		do {
			System.out.println("Main Menu");
			System.out.println("1. Create \n2. Display \n3. Insert \n4. Delete \n5. Modify");
			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Inserting some elements in the array...");
				System.out.println("How many characters do you want to add to the list? ");
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {
					System.out.println("Enter character: ");
					char ch = sc.next().charAt(0);
					if (Character.isDigit(ch))
						throw new IOException("Invalid data type!");
					else
						obj.add(ch);
				}
				break;
			case 2:
				System.out.println("The size of the array is: " + obj.size());
				System.out.println("The array elements are: " + obj);
				break;
			case 3:
				System.out.println("Inserting some elements in the array in between: ");
				System.out.println("Enter the index at which you want to insert new character: ");
				int i = sc.nextInt();
				System.out.println("Enter character: ");
				char ch = sc.next().charAt(0);
				if (Character.isDigit(ch))
					throw new IOException("Invalid data type!");
				else
					obj.add(i, ch);
				break;
			case 4:
				System.out.println("Enter the index of the number that you want to delete: ");
				int index = sc.nextInt();
				System.out.println("Removing " + index + "th element from the array!");
				obj.remove(index);
				break;
			case 5:
				System.out.println("Updating the elements from the array: ");
				System.out.println("Enter the index at which you want to modify the character: ");
				i = sc.nextInt();
				System.out.println("Enter character: ");
				ch = sc.next().charAt(0);
				if (Character.isDigit(ch))
					throw new IOException("Invalid data type!");
				else
					obj.set(i, ch);
				break;
			}
			System.out.println("Do you want to go back to main menu?(y/n)");
			ans = sc.next().charAt(0);
		} while (ans == 'y' || ans == 'Y');
	}
}
