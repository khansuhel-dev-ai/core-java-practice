package datastructures.linkedlist;

import datastructures.common.Node;

/**
 * ============================================================================
 * Class: DoublyLinkedList
 * Package: datastructures.linkedlist
 * Description: Doubly Linked List utilizing common Node<T> with both 'next'
 *              and 'prev' pointers, enabling bidirectional traversal.
 *   - Insertion: At Start O(1), At End O(1) via tail pointer, At Position O(n)
 *   - Deletion: At Position O(n) with both pointer updates
 *   - Traversals: Forward (head to tail) and Backward (tail to head)
 * ============================================================================
 */
public class DoublyLinkedList {
    private Node<Integer> head;
    private Node<Integer> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    /**
     * Inserts a new node at the front of the list.
     * Time Complexity: O(1)
     */
    public void insertAtStart(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Inserts a new node at the end of the list.
     * Time Complexity: O(1) via tail pointer
     */
    public void insertAtEnd(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Inserts a new node at a specified zero-based index.
     * Time Complexity: O(n)
     */
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position " + position + " out of bounds (Valid: 0 to " + size + ").");
        }
        if (position == 0) {
            insertAtStart(data);
            return;
        }
        if (position == size) {
            insertAtEnd(data);
            return;
        }

        Node<Integer> newNode = new Node<>(data);
        Node<Integer> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    /**
     * Deletes a node at a specified zero-based index.
     * Time Complexity: O(n)
     */
    public void deleteAtPosition(int position) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from an empty list.");
        }
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Position " + position + " out of bounds (Valid: 0 to " + (size - 1) + ").");
        }

        if (position == 0) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return;
        }

        if (position == size - 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }

        Node<Integer> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    /**
     * Displays elements from Head to Tail (Forward).
     */
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("  Forward : [Empty] (null)");
            return;
        }
        System.out.print("  Forward  (Head -> Tail): null <-> ");
        Node<Integer> current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " <-> " : " <-> null\n"));
            current = current.next;
        }
    }

    /**
     * Displays elements from Tail to Head (Backward).
     */
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("  Backward: [Empty] (null)");
            return;
        }
        System.out.print("  Backward (Tail -> Head): null <-> ");
        Node<Integer> current = tail;
        while (current != null) {
            System.out.print(current.data + (current.prev != null ? " <-> " : " <-> null\n"));
            current = current.prev;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println("=====================================================");
        System.out.println("        DOUBLY LINKED LIST DEMONSTRATION             ");
        System.out.println("=====================================================");

        list.insertAtStart(20);
        list.insertAtStart(10);
        list.insertAtEnd(40);
        list.insertAtPosition(30, 2); // Insert 30 between 20 and 40

        System.out.println("After insertions:");
        list.displayForward();
        list.displayBackward();

        System.out.println("\nDeleting node at index 2 (value 30):");
        list.deleteAtPosition(2);
        list.displayForward();
        list.displayBackward();
    }
}
