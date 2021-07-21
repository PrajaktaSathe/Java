package staticCount;
class Static {
	static int count = 0;
	void increment() {
		count++;
	}
	void display() {
		System.out.println(count);
	}
}
public class StaticCount {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Instantiating objects and calling increment function, as well as displaying the value of count variable: ");
		Static obj1 = new Static();
		obj1.increment(); // Value of count variable increased after calling the increment function on the first object
		obj1.display(); // Displays the value of the count after incrementing
		Static obj2 = new Static();
		obj2.increment(); // Value of count variable increased after calling the increment function on the second object
		obj2.display(); // Displays the value of the count after incrementing
		Static obj3 = new Static();
		obj3.increment(); // Value of count variable increased after calling the increment function on the third object
		obj3.display(); // Displays the value of the count after incrementing
		System.out.println("Now displaying values of the static variable count, for all the objects: ");
		// Now, displaying value of static variable count after object instantiation for all objects
		// Since the count variable is static, it will display the same value for all objects
		System.out.println("The value of count for first object: ");
		obj1.display();
		System.out.println("The value of count for second object: ");
		obj2.display();
		System.out.println("The value of count for third object: ");
		obj3.display();
	}
}
