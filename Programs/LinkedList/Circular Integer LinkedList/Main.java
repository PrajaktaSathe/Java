public class Main {
	
	public static void main(String[] args) {
		circularLinkedList list = new circularLinkedList();

		/**adds 10 random number in linkedlist */
		for(int i=0;i<10;i++){
			int number = (int)(Math.random()*10);
			list.add(number);
		}
		System.out.println("linkedlist:");
		list.print();
		System.out.println("size of linkedlist: "+list.size());
		System.out.println("\n");

		list.delete(1);
		list.delete(0);
		
		System.out.println("1. element is : "+list.getElement(1));
		System.out.println("head of linkedlist is: "+list.getElement(0));
		
	}
	
}
