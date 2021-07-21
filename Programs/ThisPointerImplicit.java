// Program to demonstrate the use of 'this' pointer - 
package thisPointerImplicit;
class Box {
	int l, h, b;
	Box(int l, int b, int h) {
		this.l = l;
		this.b = b;
		this.h = h;
	}
	int vol() {
		return l * b * h;
	}
}
public class ThisPointerImplicit {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box b1 = new Box(2, 4, 6);
		Box b2 = new Box(5, 5, 5);
		System.out.println("Volume = " + b1.vol());
		System.out.println("Volume = " + b2.vol());
	}
}
