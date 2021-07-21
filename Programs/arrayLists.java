package ArrayLists;
import java.util.*;

public class ArrayLists {
	public static void main(String[] args) {
		ArrayList<String> cars = new ArrayList<String>();
		cars.add("Mercedes"); // adds elements to the ArrayList
		cars.add("Audi");
		cars.add("Ferrari");
		cars.add("Jaguar");
		System.out.println(cars.isEmpty()); // prints true if list is empty or false if elements are present
		System.out.println(cars.size()); // prints the size / number of elements present in the list
		System.out.println(cars); // prints the entire list
		System.out.println(cars.get(0)); // prints element at 0th index of ArrayList
		cars.set(1, "Bugatti"); // changes value of the element at the specified index
		System.out.println(cars);
		// loop through the ArrayList
		for (int i = 0; i < cars.size(); i++) {
			System.out.println(cars.get(i));
		}
		Collections.sort(cars); // sort method from collections class is used to sort the elements in the list alphabetically/numerically
		System.out.println(cars);
		cars.clear(); // clears the list
		System.out.println(cars);
	}
}
