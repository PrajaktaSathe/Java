// Program to demonstrate the use of classes and objects (basic) - 
package classes;
class MyClass {
	private int x, y;
	// Function to set the values of x and y -
	public void set(int m, int n) {
		x = m;
		y = n;
	}
	// Function to display the product along with the values of x and y - 
	public void display() {
		System.out.println("The value of x = " + x);
		System.out.println("The value of y = " + y);
		System.out.println("The product of x and y = " + x * y);
	}
}
public class JavaClasses {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Object instantiation - 
		MyClass obj = new MyClass();
		obj.set(15, 50);
		obj.display();
	}

}
