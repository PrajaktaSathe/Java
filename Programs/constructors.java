// Program to demonstrate the use of constructors in Java -
class Area {
	int length, breadth;
	// Parameterized constructor
	Area(int m, int n) {
		length = m;
		breadth = n;
	}
	// Function to calculate area of rectangle
	void rectArea(Area a) {
		System.out.println("Length = " + a.length); // Printing length 
		System.out.println("Breadth = " + a.breadth); // Printing breadth
		int area = a.length * a.breadth; // Calculating area
		System.out.println("Area of rectangle is: " + area); // Printing area
	}
}

public class constructors {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Area rect1 = new Area(50, 25); // Object 1 instantiation 
		Area rect2 = new Area(30, 60); // Object 2 instantiation
		rect1.rectArea(rect1); // Function call to calculate and print area of first object
		rect2.rectArea(rect2); // Function call to calculate and print area of second object
	}
}
