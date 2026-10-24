package datastructures.common;

/**
 * ============================================================================
 * Class: TreeNode<T>
 * Package: datastructures.common
 * Description: Generic Node class designed for hierarchical tree data structures:
 *              - Binary Tree
 *              - Binary Search Tree (BST)
 *              - AVL Tree / Red-Black Tree
 * ============================================================================
 *
 * @param <T> type of data stored within the node
 */
public class TreeNode<T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    /**
     * Constructs a single tree node holding data.
     * Both left and right child pointers default to null.
     *
     * @param data value to store
     */
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
