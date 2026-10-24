package datastructures.common;

/**
 * ============================================================================
 * Class: Node<T>
 * Package: datastructures.common
 * Description: Generic Node class designed for linear linked data structures:
 *              - Singly Linked List (uses 'next')
 *              - Doubly Linked List (uses 'next' and 'prev')
 *              - Circular Linked List (uses 'next' wrapped to head)
 * ============================================================================
 *
 * @param <T> type of data stored within the node
 */
public class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    /**
     * Constructs a single node holding data.
     * Both next and prev pointers default to null.
     *
     * @param data value to store
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
