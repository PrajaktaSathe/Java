package Programs;//Contributed by Dev jr - https://github.com/Dev-jr-8

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BinaryTreeInorder {
    // Function to return a list containing the inorder traversal of the tree.
    ArrayList<Integer> inOrder(BinaryTreeInorder_Node root) {

        //New ArrayList to return 
        ArrayList<Integer> li = new ArrayList<Integer>();

        travel(root, li);

        return li;
    }

    public static void travel(BinaryTreeInorder_Node root, ArrayList<Integer> ans) {
        if (root == null) return;

        //if there is node on left, go there
        if (root.left != null) travel(root.left, ans);
        //add the element to list
        ans.add(root.data);
        //if there is node on right, go there
        if (root.right != null) travel(root.right, ans);
    }
}
//Time Complexity : o(n)
//Space Complexity : o(n)

//Driver Code to test the Above Class
class BinaryTreeInorder_Node {
    int data;
    BinaryTreeInorder_Node left;
    BinaryTreeInorder_Node right;

    BinaryTreeInorder_Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTreeInorder_GFG {

    static BinaryTreeInorder_Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        BinaryTreeInorder_Node root = new BinaryTreeInorder_Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<BinaryTreeInorder_Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            BinaryTreeInorder_Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new BinaryTreeInorder_Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length) break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new BinaryTreeInorder_Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            String s = br.readLine();
            BinaryTreeInorder_Node root = buildTree(s);
            BinaryTreeInorder g = new BinaryTreeInorder();
            ArrayList<Integer> res = g.inOrder(root);
            for (int i = 0; i < res.size(); i++)
                System.out.print(res.get(i) + " ");
            System.out.print("\n");
            t--;
        }
    }
}