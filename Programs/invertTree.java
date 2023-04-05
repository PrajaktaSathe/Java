// inverts a binary tree using recusion (basically gives a mirror image)

public class treeNode { // Just a definition to keep everything concise
    int val;
    treeNode left;
    treeNode right;
}

public static void invert(treeNode node) {
    treeNode placeholder = node.left; // -----
    node.left = node.right; //  Inverts two nodes
    node.right = placeholder; // ----

    if(node.left != null) invert(node.left); // recursion 
    if(node.right != null) invert(node.right);
}
