package Programs;

// Program to demonstrate 'this' pointer (explicit method) -
/**
 * BugFix
 * Descriptions:
 * 1. Added package name
 */
class Box {
	int len = 50;
	void display() {
		int len = 100;
		System.out.println("Local value = " + len);
		System.out.println("Instance value = " + this.len);
	}
}
public class ThisPointerExplicit {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box b = new Box();
		b.display();
	}
}
