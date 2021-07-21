package inheritInterface;
interface it1 {
	int x = 10, y = 20;
	public void add(int a, int b);
	public void sub(int a, int b);
}
class arithmOp implements it1 {
	public void add(int p, int q) {
		System.out.println("Addition of two numbers: " +  (p + q));
	}
	public void sub(int p, int q) {
		System.out.println("Addition of two numbers: " +  (p - q));
	}
}

public class StudentInterface {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arithmOp obj = new arithmOp();
		obj.add(10, 15);
		obj.sub(15, 10);
		System.out.println(it1.x + it1.y); // This works without errors (actually a wrong method - don't use)
		System.out.println(obj.x + obj.y); // This works but gives errors.
	}

}
