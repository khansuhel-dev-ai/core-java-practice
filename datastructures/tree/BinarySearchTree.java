package datastructures.tree;

import datastructures.common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * ============================================================================
 * Class: BinarySearchTree
 * Package: datastructures.tree
 * Description: Binary Search Tree implementation utilizing common TreeNode<T>.
 *   - Insertion, Deletion (3 cases), Search
 *   - In-Order, Pre-Order, Post-Order, Level-Order
 *   - Height, Depth, Size, Min, Max, Leaves
 *   - 2D Hierarchical ASCII Tree Rendering
 * ============================================================================
 */
class BST {
    private TreeNode<Integer> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public void insert(int val) {
        if (contains(val)) {
            System.out.println("  [Warning] Value " + val + " already exists in BST.");
            return;
        }
        root = insertRec(root, val);
        size++;
        System.out.println("  Successfully inserted: " + val);
    }

    private TreeNode<Integer> insertRec(TreeNode<Integer> current, int val) {
        if (current == null) {
            return new TreeNode<>(val);
        }
        if (val < current.data) {
            current.left = insertRec(current.left, val);
        } else if (val > current.data) {
            current.right = insertRec(current.right, val);
        }
        return current;
    }

    public boolean delete(int val) {
        if (!contains(val)) {
            System.out.println("  [Delete Failed] Value " + val + " not found.");
            return false;
        }
        root = deleteRec(root, val);
        size--;
        System.out.println("  Successfully deleted: " + val);
        return true;
    }

    private TreeNode<Integer> deleteRec(TreeNode<Integer> current, int val) {
        if (current == null) return null;

        if (val < current.data) {
            current.left = deleteRec(current.left, val);
        } else if (val > current.data) {
            current.right = deleteRec(current.right, val);
        } else {
            // Case 1: Leaf node
            if (current.left == null && current.right == null) return null;

            // Case 2: One child
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Case 3: Two children
            int successorVal = findMinValue(current.right);
            current.data = successorVal;
            current.right = deleteRec(current.right, successorVal);
        }
        return current;
    }

    public boolean contains(int val) {
        return searchRec(root, val) != null;
    }

    private TreeNode<Integer> searchRec(TreeNode<Integer> current, int val) {
        if (current == null || current.data == val) return current;
        return (val < current.data) ? searchRec(current.left, val) : searchRec(current.right, val);
    }

    public void inOrder() {
        System.out.print("  In-Order Traversal   (Sorted L-Root-R) : [ ");
        inOrderRec(root);
        System.out.println("]");
    }

    private void inOrderRec(TreeNode<Integer> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    public void preOrder() {
        System.out.print("  Pre-Order Traversal  (Root-L-R)        : [ ");
        preOrderRec(root);
        System.out.println("]");
    }

    private void preOrderRec(TreeNode<Integer> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public void postOrder() {
        System.out.print("  Post-Order Traversal (L-R-Root)        : [ ");
        postOrderRec(root);
        System.out.println("]");
    }

    private void postOrderRec(TreeNode<Integer> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void levelOrder() {
        System.out.print("  Level-Order Traversal (BFS Level-by-Level): [ ");
        if (root == null) {
            System.out.println("]");
            return;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll();
            System.out.print(current.data + " ");
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        System.out.println("]");
    }

    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(TreeNode<Integer> node) {
        if (node == null) return -1;
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }

    public int getDepth(int targetVal) {
        return calculateDepth(root, targetVal, 0);
    }

    private int calculateDepth(TreeNode<Integer> current, int targetVal, int depth) {
        if (current == null) return -1;
        if (current.data == targetVal) return depth;
        return (targetVal < current.data)
                ? calculateDepth(current.left, targetVal, depth + 1)
                : calculateDepth(current.right, targetVal, depth + 1);
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return root == null; }

    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        return findMinValue(root);
    }

    private int findMinValue(TreeNode<Integer> node) {
        while (node.left != null) node = node.left;
        return node.data;
    }

    public int getMax() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        TreeNode<Integer> current = root;
        while (current.right != null) current = current.right;
        return current.data;
    }

    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(TreeNode<Integer> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    public void displayTree() {
        if (root == null) {
            System.out.println("  Tree is currently empty.");
            return;
        }
        System.out.println("\n  --- 2D Hierarchical Tree Structure ---");
        printTreeHelper(root, "", true);
        System.out.println("  --------------------------------------\n");
    }

    private void printTreeHelper(TreeNode<Integer> node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println("  " + prefix + (isTail ? "└── " : "├── ") + node.data);
            List<TreeNode<Integer>> children = new ArrayList<>();
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
        System.out.println("=====================================================");
        System.out.println("    FULL-FLEDGED BINARY SEARCH TREE (BST) SUITE      ");
        System.out.println("=====================================================");

        int[] sample = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("Pre-loading sample values: " + java.util.Arrays.toString(sample));
        for (int v : sample) tree.insert(v);

        tree.displayTree();
        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
        tree.levelOrder();

        System.out.println("\n--- Metrics ---");
        System.out.println("  Tree Height: " + tree.getHeight());
        System.out.println("  Total Nodes: " + tree.getSize());
        System.out.println("  Min: " + tree.getMin() + " | Max: " + tree.getMax());
        System.out.println("  Leaf Nodes : " + tree.countLeaves());

        System.out.println("\n--- Deleting node 30 ---");
        tree.delete(30);
        tree.displayTree();
    }
}
