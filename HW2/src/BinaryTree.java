import java.util.Scanner;

/**
 * This class implements a binary tree.
 * 
 */
public class BinaryTree {

    /**
     * Nodes of the tree
     * 
     */
    class Node {
        public int value;
        public Node parent;
        public Node left;
        public Node right;

        // Constructor default
        public Node() {
            parent = null;
            left = null;
            right = null;
        }

        // Constructor
        public Node(int number) {
            value = number;
            parent = null;
            left = null;
            right = null;
        }
    }

    // Root of the tree
    public Node root;

    // Constructor
    public BinaryTree() {
        root = null;
    }

    /**
     * Insert a new node in the tree
     * 
     * @param k
     */
    public void insert(int number) {
        Node newNode = new Node(number);

        // If root is empty, set it as root
        // If root isn't empty, fill in the tree
        if (root == null) {
            // Root is empty, set the new node as root
            root = newNode;
        } else {
            // Root isn't empty, fill in the tree
            Node parent = root;
            while (true) {
                // If the new node is the same as the parent, stop
                if (parent.value == number) {
                    // The new node is the same as the parent, stop the loop
                    return;
                }
                // If the new node is less than the parent, go to the left
                if (parent.value > number) {
                    // If the left child is empty, set the new node as left child
                    if (parent.left == null) {
                        // Set the new node as left child
                        parent.left = newNode;
                        newNode.parent = parent;
                        // Stop the loop
                        return;
                        // If the left child isn't empty, set the parent to the left child
                    } else {
                        // Set the parent to the left child
                        // and continue the loop
                        parent = parent.left;
                    }
                    // If the new node is greater than the parent, go to the right
                } else {
                    // If the right child is empty, set the new node as right child
                    if (parent.right == null) {
                        // Set the new node as right child
                        parent.right = newNode;
                        newNode.parent = parent;
                        // Stop the loop
                        return;
                        // If the right child isn't empty, set the parent to the right child
                    } else {
                        // Set the parent to the right child
                        // and continue the loop
                        parent = parent.right;
                    }
                }
            }
        }
    }

    /**
     * Traverse the tree in LVR order
     * 
     * @param Node t
     */
    private void LVR(Node node) {
        // If the node isn't empty, traverse the tree
        if (node != null) {
            // Traverse the left subtree
            LVR(node.left);
            // Print the node
            System.out.print(node.value + " ");
            // Traverse the right subtree
            LVR(node.right);
        }
    }

    /**
     * Traverse the tree in RVL order
     * 
     * @param Node t
     */
    private void RVL(Node node) {
        // If the node isn't empty, traverse the tree
        if (node != null) {
            // Traverse the right subtree
            RVL(node.right);
            // Print the node
            System.out.print(node.value + " ");
            // Traverse the left subtree
            RVL(node.left);
        }
    }

    /**
     * Traverse the tree in VLR order
     * 
     * @param Node t
     */
    private void VLR(Node node) {
        // If the node isn't empty, traverse the tree
        if (node != null) {
            // Print the node
            System.out.print(node.value + " ");
            // Traverse the left subtree
            VLR(node.left);
            // Traverse the right subtree
            VLR(node.right);
        }
    }

    /**
     * Traverse the tree in VRL order
     * 
     * @param Node t
     */
    private void VRL(Node node) {
        // If the node isn't empty, traverse the tree
        if (node != null) {
            // Print the node
            System.out.print(node.value + " ");
            // Traverse the right subtree
            VRL(node.right);
            // Traverse the left subtree
            VRL(node.left);
        }
    }

    /**
     * Traverse the tree in LRV order
     * 
     * @param Node t
     */
    private void LRV(Node node) {
        // If the node isn't empty, traverse the tree
        if (node != null) {
            // Traverse the left subtree
            LRV(node.left);
            // Traverse the right subtree
            LRV(node.right);
            // Print the node
            System.out.print(node.value + " ");
        }
    }

    /**
     * Traverse the tree in RLV order
     * 
     * @param Node t
     */
    private void RLV(Node t) {
        // If the node isn't empty, traverse the tree
        if (t != null) {
            // Traverse the right subtree
            RLV(t.right);
            // Traverse the left subtree
            RLV(t.left);
            // Print the node
            System.out.print(t.value + " ");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Ask the user to entire the tree elements
        System.out.print("Enter the tree elements separated by spaces: ");

        // Get user input from console
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        // Close the scanner
        in.close();

        // Seperate the input by spaces and break it into an array
        String[] inputArray = input.split(" ");

        // Assuming** the array is good, enter it into the tree
        BinaryTree bst = new BinaryTree();
        // Loop through the array and insert each element into the tree
        for (int i = 0; i < inputArray.length; i++) {
            // Convert the string to an integer and insert it into the tree
            bst.insert(Integer.parseInt(inputArray[i]));
        }
        // Output the results of the tree traversals
        bst.printTrees();
    }

    /**
     * Prints the results of the tree traversals
     */
    private void printTrees() {
        // LVR
        System.out.print("Depth First Traversal: Inorder, LVR -> ");
        LVR(root);

        // RVL
        System.out.println();
        System.out.print("Depth First Traversal: Inorder, RVL -> ");
        RVL(root);

        // VLR
        System.out.println();
        System.out.print("Depth First Traversal: Preorder, VLR -> ");
        VLR(root);
        System.out.println();

        // VRL
        System.out.print("Depth First Traversal: Preorder, VRL -> ");
        VRL(root);

        // LRV
        System.out.println();
        System.out.print("Depth First Traversal: Postorder, LRV -> ");
        LRV(root);

        // RLV
        System.out.println();
        System.out.print("Depth First Traversal: Postorder, RLV -> ");
        RLV(root);
    }

}