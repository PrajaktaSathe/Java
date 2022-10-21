//Demostrate the Implementation of Stack Using 
public class Node {

    private int data;
    private Node nextNode;

    public Node(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }
  public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}


// Different Stack operation
public class CustomStack {

    int length = 0;
    Node top = null;

    public CustomStack(){
    }

    public int size(){
        return length;
    }

    public boolean isEmpty(){
        return length == 0;
    }

     public void push(int data) {
        Node tempNode = new Node(data);
        tempNode.setNextNode(top);
        top = tempNode;
        length++;
    }
    
       public int pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Node node = top;
        top = top.getNextNode();
        length--;
        return node.getData();
    }
    
    public int peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }
}