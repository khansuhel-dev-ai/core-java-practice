package datastructures.linkedlist;

import datastructures.common.Node;

/**
 * ============================================================================
 * Class: CircularLinkedList
 * Package: datastructures.linkedlist
 * Description: Circular Linked List utilizing common Node<T>.
 *              The last node (tail) connects directly back to head (tail.next == head).
 *   - Insertion: At Start O(n) without tail pointer or O(1) with tail pointer
 *   - Insertion: At End O(1) with tail pointer
 *   - Deletion: By Value O(n)
 *   - Display: Safe do-while traversal avoiding infinite loops
 * ============================================================================
 */
public class CircularLinkedList {
    private Node<Integer> head;
    private Node<Integer> tail;
    private int size;

    public CircularLinkedList() {
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
     * Inserts a new node at the beginning of the circular list.
     * Time Complexity: O(1) using tail pointer
     */
    public void insertAtStart(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // Point to self
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head; // Maintain circular link from tail
        }
        size++;
    }

    /**
     * Inserts a new node at the end of the circular list.
     * Time Complexity: O(1) using tail pointer
     */
    public void insertAtEnd(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Reconnect circular link to head
        }
        size++;
    }

    /**
     * Deletes the first occurrence of a specific value.
     * Time Complexity: O(n)
     */
    public boolean deleteByValue(int data) {
        if (isEmpty()) {
            System.out.println("  List is empty. Cannot delete " + data);
            return false;
        }

        Node<Integer> current = head;
        Node<Integer> prev = tail;

        // Search for node to delete
        do {
            if (current.data == data) {
                // If only one node exists
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) {
                        head = current.next;
                        tail.next = head;
                    }
                    if (current == tail) {
                        tail = prev;
                        tail.next = head;
                    }
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        return false;
    }

    /**
     * Displays all elements in the circular list without looping infinitely.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("  CircularList: [Empty] (circular)");
            return;
        }

        System.out.print("  CircularList (Size " + size + "): ");
        Node<Integer> current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(Back to Head: " + head.data + ")");
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        System.out.println("=====================================================");
        System.out.println("       CIRCULAR LINKED LIST DEMONSTRATION            ");
        System.out.println("=====================================================");

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtStart(5); // Insert 5 at start
        list.display();

        System.out.println("\nDeleting value 20:");
        list.deleteByValue(20);
        list.display();

        System.out.println("\nDeleting head value 5:");
        list.deleteByValue(5);
        list.display();
    }
}

