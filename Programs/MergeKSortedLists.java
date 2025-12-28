//Contributed by Dev jr - https://github.com/Dev-jr-8

class Solution {
    //Function to merge K sorted linked list.
    //Logic for merging two sorted linked list
    Node mergetwolist(Node head1, Node head2) {
        Node a = head1;
        Node b = head2;
        Node head = null;
        Node tail = null;

        if (a == null) return b;
        if (b == null) return a;

        if (a.data < b.data) {
            head = a;
            tail = a;
            a = a.next;
        } else {
            head = b;
            tail = b;
            b = b.next;
        }

        while (a != null && b != null) {
            if (a.data < b.data) {
                tail.next = a;
                tail = a;
                a = a.next;
            } else {
                tail.next = b;
                tail = b;
                b = b.next;
            }
        }
        if (a == null) tail.next = b;
        if (b == null) tail.next = a;

        return head;
    }

    Node mergeKList(Node[] arr, int K) {
        //Add your code here.
        Node ans = null;
        //Taking pair of sorted linked list and merging them, Storing their start point(head)
        //in ans reference variable
        for (Node i : arr) {
            ans = mergetwolist(i, ans);
        }
        return ans;
    }
    //Time Complexity : o(n^2)
    //Space Complexity : o(1)
}

//geeksforgeeks driver code

//{ Driver Code Starts
    import java.util .*;

class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}


class GfG {
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();

            Node[] a = new Node[N];

            for (int i = 0; i < N; i++) {
                int n = sc.nextInt();

                Node head = new Node(sc.nextInt());
                Node tail = head;

                for (int j = 0; j < n - 1; j++) {
                    tail.next = new Node(sc.nextInt());
                    tail = tail.next;
                }

                a[i] = head;
            }

            Solution g = new Solution();

            Node res = g.mergeKList(a, N);
            if (res != null)
                printList(res);
            System.out.println();
        }
    }
}