//Contributed by Dev jr - https://github.com/Dev-jr-8

class Solution {
    //Function to remove a loop in the linked list.
    public static void removeLoop(Node head) {
        // code here
        // remove the loop without losing any nodes

        //Slow fast Pointers Approach
        Node slow = head;
        Node fast = head;

        while (fast != null) {
            //moving fast pointer by two nodes and slow pointer by one node
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            //if they met, means loop detected
            if (fast == slow) {
                //reassinging head to slow
                slow = head;

                //if cycle is present in head pointer itself

                if (fast == slow)//new slow is pointing to head
                {
                    while (fast.next != slow) fast = fast.next;
                }

                //else make fast pointer to revolve around the loop
                //make slow pointer to make a move each time towards the cycle

                //if next of both is same(fast is common for both cases)
                //make fast's next as null
                else {
                    while (fast.next != slow.next) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                }
                fast.next = null;
            }

            //Time Complexity : o(n)
            //Space Complexity : o(1)
        }
    }
}

//GeeksforGeeks driver Code
import java.util .*;
        import java.io .*;
        import java.lang .*;

class Node {
    int data;
    Node next;
}

class GFG {
    public static Node newNode(int data) {
        Node temp = new Node();
        temp.data = data;
        temp.next = null;
        return temp;
    }

    public static void makeLoop(Node head, int x) {
        if (x == 0)
            return;
        Node curr = head;
        Node last = head;

        int currentPosition = 1;
        while (currentPosition < x) {
            curr = curr.next;
            currentPosition++;
        }

        while (last.next != null)
            last = last.next;
        last.next = curr;
    }

    public static boolean detectLoop(Node head) {
        Node hare = head.next;
        Node tortoise = head;
        while (hare != tortoise) {
            if (hare == null || hare.next == null) return false;
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        return true;
    }

    public static int length(Node head) {
        int ret = 0;
        while (head != null) {
            ret += 1;
            head = head.next;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int num = sc.nextInt();
            Node head = newNode(num);
            Node tail = head;

            for (int i = 0; i < n - 1; i++) {
                num = sc.nextInt();
                tail.next = newNode(num);
                tail = tail.next;
            }

            int pos = sc.nextInt();
            makeLoop(head, pos);

            Solution x = new Solution();
            x.removeLoop(head);

            if (detectLoop(head) || length(head) != n)
                System.out.println("0");
            else
                System.out.println("1");
        }
    }
}