package genericProgramming;
public class genProg<T> {
	private T item;
	public void setItem(T item) {
		this.item = item;
	}
	
	public T getItem() {
		return this.item;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		genProg<String> gc1 = new genProg<>();
		gc1.setItem("Hello");
		String item1 = gc1.getItem();
		System.out.println(item1);
		
		genProg<Integer> gc2 = new genProg<>();
		gc2.setItem(new Integer(1));
		Integer item2 = gc2.getItem();
		System.out.println(item2);
		
		genProg<Double> gc3 = new genProg<>();
		gc3.setItem(new Double(3.33));
		Double item3 = gc3.getItem();
		System.out.println(item3);
		
		genProg<Boolean> gc4 = new genProg<>();
		gc4.setItem(new Boolean(false));
		Boolean item4 = gc4.getItem();
		System.out.println(item4);
	}
}
