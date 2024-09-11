package day2.LinkedList;

public class SinglyLinkedList {
	static class Node{
		Object data;
		Node next;
		Node(Object data){
			this.data=data;
		}
	}
	Node head;
	void add(Object d) {
		Node n=new Node(d);
		if(head==null) {
			head=n;
			return;
		}
		Node t=head;
		while(t.next!=null)
			t=t.next;
		t.next=n;
	}
	void addFirst(Object d) {
		Node n=new Node(d);
		if(head==null) {
			head=n;
			return;
		}
		n.next=head;
		head=n;
	}
	void addIndex(Object d,int i) {
		if(i==0)
			addFirst(d);
		Node t=head, n=new Node(d);
		while(i>1 && t!=null) {
			t=t.next;
			i--;
		}
		if(i>1 || t==null) {
			System.out.println("Invalid Index");
			return;
		}
		n.next=t.next;
		t.next=n;
	}
	Object deleteFirst() {
		if(head==null) {
			System.out.println("List is Empty");
			return null;
		}
		Object d=head.data;
		head=head.next;
		return d;
	}
	Object deleteLast() {
		if(head==null) {
			System.out.println("List is Empty");
			return null;
		}
		Node t=head,pr=null;
		while(t.next!=null) {
			pr=t;
			t=t.next;
		}
		if(pr!=null)
			pr.next=null;
		else
			head=null;
		return t.data;
	}
	Object deleteIndex(int i) {
		if(i==0)
			return deleteFirst();
		Node t=head;
		while(i>1 && t!=null) {
			t=t.next;
			i--;
		}
		if(i>1 || t==null || t.next==null) {
			System.out.println("Invalid Index");
			return null;
		}
		Object d=t.next.data;
		t.next=t.next.next;
		return d;
	}
	int getSum() {
		int s=0;
		Node t=head;
		while(t!=null)
			s+=(int)t.data;
		return s;
	}
	boolean searchNode(Object n) {
		Node t=head;
		while(t!=null) {
			if(t.data==n)
				return true;
		}
		return false;
	}
	SinglyLinkedList mergeSLL(SinglyLinkedList f,SinglyLinkedList l) {
		if(f.head==null && l.head!=null)
			return l;
		else if(l.head==null)
			return f;
		Node t=f.head;
		while(t.next!=null)
			t=t.next;
		t.next=l.head;
		return f;
	}
	void revLL() {
		Node p=null,n=null,c=head;
		while(c!=null) {
			n=c.next;
			c.next=p;
			p=c;
			c=n;
		}
		head=p;
	}
	SinglyLinkedList convertNTL(int a) {
		SinglyLinkedList s=new SinglyLinkedList();
		do {
			s.addFirst(a%10);
			a=a/10;
		}while(a!=0);
		return s;
	}
	SinglyLinkedList addNum(int a,int b) {
		SinglyLinkedList p=convertNTL(a);
		SinglyLinkedList q=convertNTL(b);
		SinglyLinkedList r=new SinglyLinkedList();
		Node t1=p.head;
		Node t2=q.head;
		int cr=0;
		while(t1!=null && t2!=null) {
			int sum=(int)t1.data+(int)t2.data+cr;
			r.addFirst(sum%10);
			cr=sum/10;
			t1=t1.next;
			t2=t2.next;
		}
		while(t1!=null) {
			int sum=(int)t1.data+cr;
			r.addFirst(sum%10);
			cr=sum/10;
			t1=t1.next;
		}
		while(t2!=null) {
			int sum=(int)t2.data+cr;
			r.addFirst(sum%10);
			cr=sum/10;
			t2=t2.next;
		}
		return r;
	}
	
}
