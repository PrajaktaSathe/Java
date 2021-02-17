// Program to demonstrate overloading in Java - 
package overloading;
class SqRectArea {
	public void Area(int a) {
		System.out.println("The side of square = " + a);
		System.out.println("The area of square = " + (a * a));
	}
	public void Area(int l, int b) {
		System.out.println("Length = " + l);
		System.out.println("Breadth = " + b);
		System.out.println("The area of rectangle = " + (l * b));
	}
}
public class Overloading {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqRectArea sq = new SqRectArea(); // New object instantiation for square
		sq.Area(5); // Calling function with 1 parameter
		SqRectArea rect = new SqRectArea(); // New object instantiation for rectangle
		rect.Area(10, 5); // Calling function with 2 parameters
	}
}
