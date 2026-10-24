/**
 * ============================================================================
 * File: BinarySearchTree.java
 * Repository: my-java-practice
 * Description: Comprehensive Binary Search Tree (BST) Data Structure implementation:
 *   1. Node Definition (TreeNode with left & right pointers)
 *   2. Insertion (O(log n) average, O(n) worst-case) with duplicate handling
 *   3. Deletion (Handling 0, 1, and 2 children via in-order successor)
 *   4. Search & Lookup
 *   5. Depth-First Traversals: In-Order, Pre-Order, Post-Order
 *   6. Breadth-First Traversal: Level-Order (BFS using Queue)
 *   7. Tree Metrics: Height, Node Depth, Total Size, Min/Max Value, Leaf Count
 *   8. Visual 2D ASCII Tree Diagram Display
 *   9. Interactive Command-Line Menu Driver
 *
 * How to Compile & Run:
 *   javac BinarySearchTree.java
 *   java BinarySearchTree
 * ============================================================================
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Node class representing an individual node in the Binary Search Tree.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

/**
 * Custom Binary Search Tree implementation managing root and operations.
 */
class BST {
    private TreeNode root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    // ========================================================================
    // 1. INSERTION
    // ========================================================================

    /**
     * Inserts a value into the BST.
     * Time Complexity: O(log n) average, O(n) worst.
     */
    public void insert(int val) {
        if (contains(val)) {
            System.out.println("  [Warning] Value " + val + " already exists in BST. Duplicates not permitted.");
            return;
        }
        root = insertRec(root, val);
        size++;
        System.out.println("  Successfully inserted: " + val);
    }

    private TreeNode insertRec(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }
        if (val < current.val) {
            current.left = insertRec(current.left, val);
        } else if (val > current.val) {
            current.right = insertRec(current.right, val);
        }
        return current;
    }

    // ========================================================================
    // 2. DELETION
    // ========================================================================

    /**
     * Deletes a value from the BST.
     * Handles all 3 cases: Leaf, Single Child, Two Children (via In-Order Successor).
     * Time Complexity: O(log n) average, O(n) worst.
     */
    public boolean delete(int val) {
        if (!contains(val)) {
            System.out.println("  [Delete Failed] Value " + val + " does not exist in the BST.");
            return false;
        }
        root = deleteRec(root, val);
        size--;
        System.out.println("  Successfully deleted: " + val);
        return true;
    }

    private TreeNode deleteRec(TreeNode current, int val) {
        if (current == null) {
            return null;
        }

        // Navigate to the target node
        if (val < current.val) {
            current.left = deleteRec(current.left, val);
        } else if (val > current.val) {
            current.right = deleteRec(current.right, val);
        } else {
            // Node found! Handle the 3 deletion cases:

            // Case 1: Node has no children (Leaf node)
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            // Case 3: Node has two children
            // Find the In-Order Successor (smallest value in right subtree)
            int successorVal = findMinValue(current.right);
            current.val = successorVal; // Copy successor value to current node
            // Recursively delete the successor node from right subtree
            current.right = deleteRec(current.right, successorVal);
        }
        return current;
    }

    // ========================================================================
    // 3. SEARCH & LOOKUP
    // ========================================================================

    public boolean contains(int val) {
        return searchRec(root, val) != null;
    }

    private TreeNode searchRec(TreeNode current, int val) {
        if (current == null || current.val == val) {
            return current;
        }
        return (val < current.val) ? searchRec(current.left, val) : searchRec(current.right, val);
    }

    // ========================================================================
    // 4. TRAVERSALS (In-Order, Pre-Order, Post-Order, Level-Order)
    // ========================================================================

    /**
     * In-Order Traversal: Left -> Root -> Right
     * Crucial Property: Always outputs nodes in sorted ascending order!
     */
    public void inOrder() {
        System.out.print("  In-Order Traversal   (Sorted L-Root-R) : [ ");
        inOrderRec(root);
        System.out.println("]");
    }

    private void inOrderRec(TreeNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.val + " ");
            inOrderRec(node.right);
        }
    }

    /**
     * Pre-Order Traversal: Root -> Left -> Right
     * Ideal for cloning, serialization, and prefix tree generation.
     */
    public void preOrder() {
        System.out.print("  Pre-Order Traversal  (Root-L-R)        : [ ");
        preOrderRec(root);
        System.out.println("]");
    }

    private void preOrderRec(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    /**
     * Post-Order Traversal: Left -> Right -> Root
     * Ideal for bottom-up node destruction and postfix mathematical expression evaluation.
     */
    public void postOrder() {
        System.out.print("  Post-Order Traversal (L-R-Root)        : [ ");
        postOrderRec(root);
        System.out.println("]");
    }

    private void postOrderRec(TreeNode node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.val + " ");
        }
    }

    /**
     * Level-Order Traversal (Breadth-First Search / BFS):
     * Explores nodes horizontally level-by-level using a FIFO Queue.
     */
    public void levelOrder() {
        System.out.print("  Level-Order Traversal (BFS Level-by-Level): [ ");
        if (root == null) {
            System.out.println("]");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        System.out.println("]");
    }

    // ========================================================================
    // 5. METRICS: HEIGHT, DEPTH, SIZE, MIN, MAX & LEAVES
    // ========================================================================

    /**
     * Height of the tree: Maximum number of edges from root to any leaf node.
     * Empty tree has height -1; Single node tree has height 0.
     */
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Calculates the depth of a specific node (number of edges from root to that node).
     *
     * @param targetVal node value
     * @return depth (0 for root, -1 if not found)
     */
    public int getDepth(int targetVal) {
        return calculateDepth(root, targetVal, 0);
    }

    private int calculateDepth(TreeNode current, int targetVal, int currentDepth) {
        if (current == null) {
            return -1; // Value not found
        }
        if (current.val == targetVal) {
            return currentDepth;
        }
        return (targetVal < current.val)
                ? calculateDepth(current.left, targetVal, currentDepth + 1)
                : calculateDepth(current.right, targetVal, currentDepth + 1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Finds the minimum value stored in the BST (leftmost descendant).
     */
    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        return findMinValue(root);
    }

    private int findMinValue(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /**
     * Finds the maximum value stored in the BST (rightmost descendant).
     */
    public int getMax() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.val;
    }

    /**
     * Counts the total number of leaf nodes (nodes with 0 children).
     */
    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // ========================================================================
    // 6. VISUAL 2D ASCII TREE DISPLAY
    // ========================================================================

    /**
     * Displays a clean, hierarchical ASCII 2D diagram of the BST.
     */
    public void displayTree() {
        if (root == null) {
            System.out.println("  Tree is currently empty.");
            return;
        }
        System.out.println("\n  --- 2D Hierarchical Tree Structure ---");
        printTreeHelper(root, "", true);
        System.out.println("  --------------------------------------\n");
    }

    private void printTreeHelper(TreeNode node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println("  " + prefix + (isTail ? "└── " : "├── ") + node.val);
            List<TreeNode> children = new java.util.ArrayList<>();
            if (node.left != null) children.add(node.left);
            if (node.right != null) children.add(node.right);

            for (int i = 0; i < children.size(); i++) {
                printTreeHelper(children.get(i), prefix + (isTail ? "    " : "│   "), i == children.size() - 1);
            }
        }
    }
}

public class BinarySearchTree {

    public static void main(String[] args) {
        BST tree = new BST();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================================");
        System.out.println("    FULL-FLEDGED BINARY SEARCH TREE (BST) SUITE      ");
        System.out.println("=====================================================");

        // Pre-populating default balanced sample data for instant exploration:
        int[] initialValues = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("Pre-loading sample BST values: " + java.util.Arrays.toString(initialValues));
        for (int v : initialValues) {
            tree.insert(v);
        }

        tree.displayTree();

        boolean running = true;
        while (running) {
            System.out.println("\nSelect Tree Operation:");
            System.out.println("  1. Insert a Value");
            System.out.println("  2. Delete a Value");
            System.out.println("  3. Search for a Value");
            System.out.println("  4. Display 2D Tree Diagram");
            System.out.println("  5. Display All Traversals (In-Order, Pre-Order, Post-Order, Level-Order)");
            System.out.println("  6. Display Tree Metrics (Height, Depth, Size, Min, Max, Leaves)");
            System.out.println("  7. Exit");
            System.out.print("Enter choice (1-7): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter integer value to insert: ");
                    if (scanner.hasNextInt()) {
                        int val = scanner.nextInt();
                        tree.insert(val);
                        tree.displayTree();
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.print("Enter integer value to delete: ");
                    if (scanner.hasNextInt()) {
                        int delVal = scanner.nextInt();
                        tree.delete(delVal);
                        tree.displayTree();
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Enter integer value to search: ");
                    if (scanner.hasNextInt()) {
                        int searchVal = scanner.nextInt();
                        boolean found = tree.contains(searchVal);
                        if (found) {
                            System.out.println("  >> FOUND: Value " + searchVal + " exists at depth " 
                                    + tree.getDepth(searchVal) + " (Root is depth 0).");
                        } else {
                            System.out.println("  >> NOT FOUND: Value " + searchVal + " does not exist in the BST.");
                        }
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                    break;

                case 4:
                    tree.displayTree();
                    break;

                case 5:
                    System.out.println("\n--- Depth-First & Breadth-First Traversals ---");
                    tree.inOrder();
                    tree.preOrder();
                    tree.postOrder();
                    tree.levelOrder();
                    break;

                case 6:
                    if (tree.isEmpty()) {
                        System.out.println("  Tree is empty.");
                        break;
                    }
                    System.out.println("\n--- Tree Properties & Metrics ---");
                    System.out.println("  Total Nodes (Size)      : " + tree.getSize());
                    System.out.println("  Tree Height (Edges)     : " + tree.getHeight());
                    System.out.println("  Minimum Value           : " + tree.getMin());
                    System.out.println("  Maximum Value           : " + tree.getMax());
                    System.out.println("  Total Leaf Nodes        : " + tree.countLeaves());
                    break;

                case 7:
                    System.out.println("Exiting Binary Search Tree program. Happy coding!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-7.");
            }
        }

        scanner.close();
    }
}
