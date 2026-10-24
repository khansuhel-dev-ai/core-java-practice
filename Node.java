/**
 * ============================================================================
 * File: Node.java
 * Repository: my-java-practice
 * Description: Node definition for the Singly Linked List data structure.
 * ============================================================================
 */

public class Node {
    public int data;
    public Node next;

    /**
     * Constructs a new Node storing the given integer data.
     * The next pointer is initialized to null.
     *
     * @param data integer value to store in the node
     */
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
