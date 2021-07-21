// Program to demonstrate the order of constructor call - 
package derivedConstructor;

class Base {
	public Base() {
		System.out.println("Base class constructor called!");  // Will be called first
	}
	public Base(int x) {
		System.out.println(x);
	}
}
class Derived extends Base {
	public Derived() {
		System.out.println("Derived class constructor called!");  // Will be called after base class constructor
	}
}
class Derived1 extends Derived {
	public Derived1() {
		System.out.println("Derived1 class constructor called!");  // Will be called after the first derived class
	}
}
public class DerivedConstructors {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Derived1 obj = new Derived1();
	}
}
