public class Node {
	int data;
	Node next;
	
	public Node(int a) {
		data=a;
		next=null;
		
	}
	
	public Node() {
		data=-1;
		next=null;
	}

	public int getNodeElement(){
		return data;
	}
}
