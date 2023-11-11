//Contributed by Dev jr - https://github.com/Dev-jr-8
package Programs;

import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from Solution to MergeKsortedLists
 * 2. Added package name
 * 3. Optimized imports
 */
public class MergeKsortedLists {
    //Function to merge K sorted linked list.
    //Logic for merging two sorted linked list
    MergeKsortedLists_Node mergetwolist(MergeKsortedLists_Node head1, MergeKsortedLists_Node head2) {
        MergeKsortedLists_Node a = head1;
        MergeKsortedLists_Node b = head2;
        MergeKsortedLists_Node head = null;
        MergeKsortedLists_Node tail = null;

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

    MergeKsortedLists_Node mergeKList(MergeKsortedLists_Node[] arr, int K) {
        //Add your code here.
        MergeKsortedLists_Node ans = null;
        //Taking pair of sorted linked list and merging them, Storing their start point(head)
        //in ans reference variable
        for (MergeKsortedLists_Node i : arr) {
            ans = mergetwolist(i, ans);
        }
        return ans;
    }
    //Time Complexity : o(n^2)
    //Space Complexity : o(1)
}

//geeksforgeeks driver code

//{ Driver Code Starts


class MergeKsortedLists_Node {
    int data;
    MergeKsortedLists_Node next;

    MergeKsortedLists_Node(int key) {
        data = key;
        next = null;
    }
}


class GfG {
    public static void printList(MergeKsortedLists_Node node) {
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

            MergeKsortedLists_Node[] a = new MergeKsortedLists_Node[N];

            for (int i = 0; i < N; i++) {
                int n = sc.nextInt();

                MergeKsortedLists_Node head = new MergeKsortedLists_Node(sc.nextInt());
                MergeKsortedLists_Node tail = head;

                for (int j = 0; j < n - 1; j++) {
                    tail.next = new MergeKsortedLists_Node(sc.nextInt());
                    tail = tail.next;
                }

                a[i] = head;
            }

            MergeKsortedLists g = new MergeKsortedLists();

            MergeKsortedLists_Node res = g.mergeKList(a, N);
            if (res != null)
                printList(res);
            System.out.println();
        }
    }
}