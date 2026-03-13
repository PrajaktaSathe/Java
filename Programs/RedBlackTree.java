package Programs;

public class RedBlackTree {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Node class
    private class Node {
        int data;
        Node left, right, parent;
        boolean color;

        Node(int data) {
            this.data = data;
            this.color = RED; // New nodes are always red
        }
    }

    // Left rotate
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    // Right rotate
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;

        x.parent = y.parent;
        if (y.parent == null)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        x.right = y;
        y.parent = x;
    }

    // Insert a node into the Red-Black Tree
    public void insert(int data) {
        Node newNode = new Node(data);
        root = bstInsert(root, newNode);
        fixInsert(newNode);
    }

    // Standard BST insertion
    private Node bstInsert(Node root, Node node) {
        if (root == null)
            return node;

        if (node.data < root.data) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    // Fix Red-Black Tree violations after insertion
    private void fixInsert(Node node) {
        Node parent, grandparent;

        while (node != root && node.color == RED && node.parent.color == RED) {
            parent = node.parent;
            grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                if (uncle != null && uncle.color == RED) { // Case 1: Uncle red
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.right) { // Case 2: node is right child
                        leftRotate(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Case 3: node is left child
                    rightRotate(grandparent);
                    boolean tmpColor = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = tmpColor;
                    node = parent;
                }
            } else {
                Node uncle = grandparent.left;

                if (uncle != null && uncle.color == RED) { // Case 1
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) { // Case 2
                        rightRotate(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Case 3
                    leftRotate(grandparent);
                    boolean tmpColor = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = tmpColor;
                    node = parent;
                }
            }
        }
        root.color = BLACK; // root must always be black
    }

    // Inorder traversal (sorted order)
    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.data + (node.color == RED ? "(R) " : "(B) "));
            inorderHelper(node.right);
        }
    }

    // Preorder traversal (tree structure)
    public void preorder() {
        preorderHelper(root);
        System.out.println();
    }

    private void preorderHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + (node.color == RED ? "(R) " : "(B) "));
            preorderHelper(node.left);
            preorderHelper(node.right);
        }
    }

    // Search for a value in the tree
    public boolean search(int key) {
        Node current = root;
        while (current != null) {
            if (key < current.data)
                current = current.left;
            else if (key > current.data)
                current = current.right;
            else
                return true;
        }
        return false;
    }

    // Main method to test the Red-Black Tree
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = { 10, 20, 30, 15, 25, 5, 1, 50, 60 };

        for (int val : values)
            tree.insert(val);

        System.out.println("Inorder traversal (sorted with colors):");
        tree.inorder();

        System.out.println("Preorder traversal (tree structure with colors):");
        tree.preorder();

        System.out.println("Search for 25: " + tree.search(25));
        System.out.println("Search for 100: " + tree.search(100));
    }
}