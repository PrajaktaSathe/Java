package Programs.LinkedList;

/* Program to implement simple int linked list from scratch */
public class CustomLinkedList {
  private Node Head;
  private Node Tail;

  private int size;
  public CustomLinkedList() {
    this.size = 0;
  }

  // Node class with private instance variables and constructors

  class Node {
    private int value;
    private Node next;

    public Node(int value) {
      this.value = value;
    }
    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

  }

  // insert method which takes the new value as parameter and inserts at the beginning

  void insertFirst(int val) {
    Node node = new Node(val);
    node.next = Head;
    Head = node;
    if(Tail == null) {
      Tail = Head;
    }
    size++;
  }

  // traverse method to print all the elements present

  public void traverse() {

    Node temp = Head;
    while(temp != null) {
      System.out.print(temp.value + "-> ");
      temp = temp.next;
    }
    System.out.println("END");
  }

  // insertLast method to insert element at the end of the list

  public void insertLast(int val) {
    if(Tail == null) {
      insertFirst(val);
      return;
    }
    Node node = new Node(val);
    Tail.next = node;
    Tail = node;
    size++;
  }

  // method to insert the given value at a given index

  public void insertAtIndex(int val, int index) {
    if(index == 0) {
      insertFirst(val);
      return;
    }
    if(index == size) {
      insertLast(val);
      return;
    }
    Node temp = Head;
    for(int i = 1; i < index && index < size; i++) {
      temp = temp.next;
    }
    Node node = new Node(val, temp.next);
    temp.next = node;
    size++;

  }
}
