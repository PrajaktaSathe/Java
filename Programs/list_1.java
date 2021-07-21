import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// HW - 
// WAP a Java program which will create a list of int, string and decimal numbers using arraylist and linkedlists 

public class list_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Array list for strings -
		List<String> listStrings = new ArrayList<String>();
		listStrings.add("One");
		listStrings.add("Two");
		listStrings.add("Three");
		listStrings.add("Four");
		System.out.println(listStrings);
		System.out.println(listStrings.get(1));
		listStrings.set(3, "Using set method!");
		System.out.println(listStrings);
		
		// Array list for integers - 
		List<Integer> listIntegers = new ArrayList<Integer>();
		listIntegers.add(1);
		listIntegers.add(2);
		listIntegers.add(3);
		listIntegers.add(4);
		System.out.println(listIntegers);
		// Iterating over a list - 
		Iterator<Integer> iter = listIntegers.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		// Linked List for numbers -
		LinkedList<Number> numbers = new LinkedList<Number>();
		numbers.add(10);
		numbers.add(10.32);
		numbers.add(45.3333333);
		System.out.println(numbers);
		
	}
}
