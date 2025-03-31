// Program to calculate the area of rectangle using return values - 

public class AreaRect {
	public static void main(String[] args) {
		
		AreaRect areaRect = new AreaRect();
		
		// TODO Auto-generated method stub
		int a1, a2;
		Rectangle r1 = areaRect.new Rectangle();
		Rectangle r2 = areaRect.new Rectangle();
		r1.l = 10;
		r1.b = 15;
		a1 = r1.l * r1.b;
		r2.getData(20, 30);
		a2 = r2.calcArea();
		System.out.println("The area of first rectangle = " + a1);
		System.out.println("The area of second rectangle = " + a2);
	}
	
	public class Rectangle {
		public int l, b;
		public void getData(int m, int n) {
			l = m;
			b = n;
		}
		public int calcArea() {
			int area = l * b;
			return area;
		}
	}
}