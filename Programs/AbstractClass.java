// Program to demonstrate abstract classes in Java -
package abstractClass;
// No function definition can be added
// Objects of abstract class cannot be created,but object references can be created
// Syntax: abstract type method-name(parameter-list);
// abstract class class-name {}
// No abstract constructors
// Abstract class must be a subclass

abstract class Area {
    double dim1, dim2;

    public Area(double x, double y) {
        dim1 = x;
        dim2 = y;
    }

    abstract double area1();
}

class Rectangle extends abstractClass.Area {
    Rectangle(double a, double b) {
        super(a, b);
    }

    double area1() {
        System.out.println("Area of rectangle = ");
        return dim1 * dim2;
    }
}

class Triangle extends abstractClass.Area {
    Triangle(double a, double b) {
        super(a, b);
    }

    double area1() {
        System.out.println("Area of triangle = ");
        return (0.5 * dim1 * dim2);
    }
}

public class AbstractClass {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        abstractClass.Rectangle r = new abstractClass.Rectangle(10, 15);
        abstractClass.Triangle t = new abstractClass.Triangle(2, 5);
        System.out.println(r.area1());
        System.out.println(t.area1());
    }
}