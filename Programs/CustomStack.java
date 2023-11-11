package Programs;

import java.util.EmptyStackException;

//Demostrate the Implementation of Stack Using
/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from Stack to CustomStack
 * 2. Added package name
 * 3. Optimized imports
 */
class CustomStackNode {

    private int data;
    private CustomStackNode nextNode;

    public CustomStackNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public CustomStackNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(CustomStackNode nextNode) {
        this.nextNode = nextNode;
    }
}


// Different Stack operation
public class CustomStack {

    int length = 0;
    CustomStackNode top = null;

    public CustomStack() {
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void push(int data) {
        CustomStackNode tempNode = new CustomStackNode(data);
        tempNode.setNextNode(top);
        top = tempNode;
        length++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        CustomStackNode node = top;
        top = top.getNextNode();
        length--;
        return node.getData();
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }
}