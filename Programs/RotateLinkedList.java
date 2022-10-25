import java.util.*;

/**
 * Definition for singly-linked list.
 **/
class SinglyLinkedNode {
    int val;
    SinglyLinkedNode next;

    public SinglyLinkedNode(int val) {
        this.val = val;
    }
}

/**
 * This is a program to demo rotating a singly linked list.
 * @see <a href=
 *      "https://leetcode.com/problems/rotate-list/description/">LeetCode
 *      description</a>
 * Time complexity O(n), Space O(1)
 */
public class RotateLinkedList {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the LinkedList in the form of [1,2,3,4,5]");
            String input = sc.nextLine();
            System.out.println("Enter the number of rotations");
            int k = sc.nextInt();
            String str = input.replaceAll(" ", "") .substring(1, input.length() - 1);
            String[] nodesStr = str.split(",");
            if (nodesStr.length == 0) {
                System.out.println("[]");
                return;
            }

            SinglyLinkedNode head = buildLinkedList(nodesStr);
            RotateLinkedList solution = new RotateLinkedList();
            SinglyLinkedNode newHead =  solution.rotateRight(head, k);
            printOutput(newHead);
        } catch (Exception e) {
            System.out.println(
                    "Something went wrong. Check the linkedlist format is in the form of [1,2,3,4,5] and rotation is an integer");
        }
    }

    private static SinglyLinkedNode buildLinkedList(String[] nodesStr) {
        SinglyLinkedNode head = null;
        SinglyLinkedNode node = null;
        for (int i = 0; i < nodesStr.length; i++) {
            SinglyLinkedNode newNode = new SinglyLinkedNode(Integer.valueOf(nodesStr[i]));
            if (i == 0) {
                head = newNode;
            }

            if (node == null) {
                node = newNode;
            } else {
                node.next = newNode;
                node = newNode;
            }
        }

        return head;
    }

    private static void printOutput(SinglyLinkedNode newHead) {
        System.out.print("[");
        do {
            System.out.print(newHead.val);
            newHead = newHead.next;
            if (newHead != null) {
                System.out.print(",");
            }
        } while(newHead != null);
        System.out.println("]");
    }

    /**
     * Given the head of a linked list, rotate the list to the right by k places.
     * @param head
     * @param k
     * @return head of linked list
     */
    public SinglyLinkedNode rotateRight(SinglyLinkedNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        // Finding the size of the list
        int size = 1; // start with 1 because we're starting with head
        SinglyLinkedNode currentEnd = head;
        while (currentEnd.next != null) {
            currentEnd = currentEnd.next;
            size++;
        }

        // linking the end to the head
        currentEnd.next = head;

        // determine the rotation based on k
        int rotate = 0;
        if (k < size) {
            rotate = k;
        } else {
            rotate = k % size;
        }

        // Find the index of the end node
        int endNode = size - rotate;

        // set the newEnd to head and loop until you get to the new end node
        SinglyLinkedNode newEnd = head;
        for (int i = 1; i < endNode; i++) {
            newEnd = newEnd.next;
        }

        // set the new head node and terminate the new end node
        SinglyLinkedNode newHead = newEnd.next;
        newEnd.next = null;
        return newHead;
    }
}