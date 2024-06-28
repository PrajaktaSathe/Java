public class circularLinkedList {
	Node head;
	int size;
	
	public circularLinkedList() {
		head=new Node();
		head.next=head;
		size=0;
		head.data=-1;
	}
	/**
	 * adds a new element end of the linkedlist
	 * @param num
	 */
	public void add(int num) {
		
		Node newNode=new Node(num);
		Node currNode=head;
		
		if(size==0)
		{
			head.data=num;
		}
		else {
			while(!currNode.next.equals(head))
			{
				currNode=currNode.next;
				
			}
			newNode.next=currNode.next;
			currNode.next=newNode;
		}
		size++;
			
	}
	
	/**
	 * deletes index 
	 * index 0 is equal to head of linkedlist
	 * @param index
	 */
	public void delete(int index) {

		Node currNode=head;
		Node currNodePrev=head;
		
		if(size==0)
			System.out.println("Linkedlist is empty");
		if(index>=size)
			System.out.println("index of deletion is larger then size of linkedlist");
		else{
			if(index==0){
				while(!currNode.next.equals(head)){
					currNode=currNode.next;
				}
				currNode.next=head.next;
				head=head.next;
			}else{
				for(int i=0;i<index;i++){
					currNodePrev=currNode;
					currNode=currNode.next;
				}
				currNodePrev.next=currNode.next;
				currNode=null;
			}
		}
		System.out.println("after deletion of "+index+". element :");
		print();
	}
	/**
	 * prints linkedlist 
	 * 
	 */
	public void print() {
		System.out.print(head.data+" ");
		Node currNode=head.next;
		
		while(!currNode.equals(head)) {
			System.out.print(currNode.data+" ");
			currNode=currNode.next;
		}
		System.out.println();
		
		
	}
	/**
	 * returns size of linked list
	 * @return integer size
	 */
	public int size(){
		return size;
	}
	
	/**
	 * returns element of given index
	 * 0 is denotes head node
	 * @param index
	 * @return
	 */
	public int getElement(int index){
		Node currNode=head;
		for(int i=0;i<index;i++){
			currNode=currNode.next;
		}
		return currNode.getNodeElement();
	}
	
	
}



