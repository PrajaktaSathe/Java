// Program to demonstrate abstract classes in Java -
package abstractClass;
// No function definition can be added
// Objects of abstract class cannot be created,but object references can be created
// Syntax: abstract type method-name(parameter-list);
// abstract class class-name {}
// No abstract constructors
// Abstract class must be a subclass

abstract class area {
	double dim1, dim2;
	public area(double x, double y) {
		dim1 = x;
		dim2 = y;
	}
	abstract double area1();
}

class rectangle extends area {
	rectangle(double a, double b) {
		super(a, b);
	}
	double area1() {
		System.out.println("Area of rectangle = ");
		return dim1*dim2;
	}
}

class triangle extends area {
	triangle(double a, double b) {
		super(a, b);
	}
	double area1() {
		System.out.println("Area of triangle = ");
		return (0.5*dim1*dim2);
	}
}

public class AbstractClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rectangle r = new rectangle(10, 15);
		triangle t = new triangle(2, 5);
		System.out.println(r.area1());
		System.out.println(t.area1());
	}
}