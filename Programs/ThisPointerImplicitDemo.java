package Programs;

// Program to demonstrate the use of 'this' pointer -
class ThisPointerImplicit_Box {
	int l, h, b;
	ThisPointerImplicit_Box(int l, int b, int h) {
		this.l = l;
		this.b = b;
		this.h = h;
	}
	int vol() {
		return l * b * h;
	}
}
/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from ThisPointerImplicit to ThisPointerImplicitDemo
 * 2. Added package name
 */
public class ThisPointerImplicitDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisPointerImplicit_Box b1 = new ThisPointerImplicit_Box(2, 4, 6);
		ThisPointerImplicit_Box b2 = new ThisPointerImplicit_Box(5, 5, 5);
		System.out.println("Volume = " + b1.vol());
		System.out.println("Volume = " + b2.vol());
	}
}
