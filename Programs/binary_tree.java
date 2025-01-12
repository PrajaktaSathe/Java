import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class BinaryTree {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    // Insertion in Binary Tree
    public static Node insert(Node root, int value) {
        Node newNode = new Node(value);
        if (root == null) {
            return newNode;
        }

        // Level order insertion
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == null) {
                current.left = newNode;
                return root;
            } else if (current.right == null) {
                current.right = newNode;
                return root;
            } else {
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        return root;
    }

    // Deletion in Binary Tree
    public static Node delete(Node root, int value) {
        if (root == null) return null;

        if (root.data == value) {
            return null; // Root node itself is to be deleted
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node targetNode = null;
        Node deepestNode = null;

        // Find the node to delete and the deepest node
        while (!queue.isEmpty()) {
            deepestNode = queue.poll();

            if (deepestNode.data == value) {
                targetNode = deepestNode;
            }

            if (deepestNode.left != null) {
                queue.add(deepestNode.left);
            }

            if (deepestNode.right != null) {
                queue.add(deepestNode.right);
            }
        }

        if (targetNode != null) {
            targetNode.data = deepestNode.data;
            root = removeDeepestNode(root, deepestNode);
        }
        return root;
    }

    // Remove deepest node (used after deletion)
    private static Node removeDeepestNode(Node root, Node deepestNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node temp = null;

        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == deepestNode) {
                deepestNode = null;
                break;
            }

            if (temp.right != null) {
                if (temp.right == deepestNode) {
                    temp.right = null;
                    break;
                } else {
                    queue.add(temp.right);
                }
            }

            if (temp.left != null) {
                if (temp.left == deepestNode) {
                    temp.left = null;
                    break;
                } else {
                    queue.add(temp.left);
                }
            }
        }
        return root;
    }

    // Search in Binary Tree
    public static boolean search(Node root, int value) {
        if (root == null) return false;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.data == value) {
                return true; // Node found
            }
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return false; // Node not found
    }

    // Main method to execute operations
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = null;

        int choice;
        do {
            System.out.println("\n--- Binary Tree Operations ---");
            System.out.println("1. Insert Node");
            System.out.println("2. Delete Node");
            System.out.println("3. Search Node");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter node value to insert: ");
                    int insertValue = scanner.nextInt();
                    root = insert(root, insertValue);
                    System.out.println("Node inserted successfully.");
                    break;
                case 2:
                    System.out.print("Enter node value to delete: ");
                    int deleteValue = scanner.nextInt();
                    root = delete(root, deleteValue);
                    System.out.println("Node deleted successfully.");
                    break;
                case 3:
                    System.out.print("Enter node value to search: ");
                    int searchValue = scanner.nextInt();
                    if (search(root, searchValue)) {
                        System.out.println("Node found.");
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
