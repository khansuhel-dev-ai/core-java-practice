/**
 * ============================================================================
 * File: LinkedList.java
 * Repository: my-java-practice
 * Description: Interactive Singly Linked List Data Structure implementation.
 *   - Insertion: At Start O(1), At End O(n), At Specific Index O(n)
 *   - Deletion: At Specific Index O(n)
 *   - Traversal: Display elements from head to tail O(n)
 *   - Interactive Command-Line Menu Driver
 *
 * How to Compile & Run:
 *   javac Node.java LinkedList.java
 *   java LinkedList
 * ============================================================================
 */

import java.util.Scanner;

/**
 * SinglyLinkedList implementation managing head pointer and basic operations.
 */
class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Checks if the list contains no elements.
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the total number of nodes in the list.
     * Time Complexity: O(1)
     */
    public int getSize() {
        return size;
    }

    /**
     * Inserts a new node at the beginning of the list.
     * Time Complexity: O(1)
     *
     * @param data integer value to insert
     */
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Inserts a new node at the end of the list.
     * Time Complexity: O(n)
     *
     * @param data integer value to insert
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    /**
     * Inserts a new node at a specified zero-based index.
     * Time Complexity: O(n)
     *
     * @param data     integer value to insert
     * @param position zero-based index
     */
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position " + position + " is out of bounds (Valid range: 0 to " + size + ").");
        }
        if (position == 0) {
            insertAtStart(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Deletes a node at the specified zero-based index.
     * Time Complexity: O(n)
     *
     * @param position zero-based index
     */
    public void deleteAtPosition(int position) {
        if (head == null) {
            throw new IllegalStateException("Cannot delete from an empty list.");
        }
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Position " + position + " is out of bounds (Valid range: 0 to " + (size - 1) + ").");
        }
        if (position == 0) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    /**
     * Prints all nodes in sequence from head to tail.
     * Time Complexity: O(n)
     */
    public void display() {
        if (head == null) {
            System.out.println("  List: [Empty] (null)");
            return;
        }
        System.out.print("  List (Size " + size + "): ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedList {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================================");
        System.out.println("   SINGLY LINKED LIST INTERACTIVE PRACTICE DRIVER    ");
        System.out.println("=====================================================");

        boolean running = true;
        while (running) {
            System.out.println("\nSelect Operation:");
            System.out.println("  1. Insert at Start");
            System.out.println("  2. Insert at End");
            System.out.println("  3. Insert at Position");
            System.out.println("  4. Delete at Position");
            System.out.println("  5. Display List");
            System.out.println("  6. Exit");
            System.out.print("Enter choice (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter integer value to insert at start: ");
                        int valStart = scanner.nextInt();
                        list.insertAtStart(valStart);
                        list.display();
                        break;
                    case 2:
                        System.out.print("Enter integer value to insert at end: ");
                        int valEnd = scanner.nextInt();
                        list.insertAtEnd(valEnd);
                        list.display();
                        break;
                    case 3:
                        System.out.print("Enter integer value to insert: ");
                        int valPos = scanner.nextInt();
                        System.out.print("Enter position index (0 to " + list.getSize() + "): ");
                        int pos = scanner.nextInt();
                        list.insertAtPosition(valPos, pos);
                        list.display();
                        break;
                    case 4:
                        if (list.isEmpty()) {
                            System.out.println("List is currently empty. Nothing to delete.");
                            break;
                        }
                        System.out.print("Enter position index to delete (0 to " + (list.getSize() - 1) + "): ");
                        int delPos = scanner.nextInt();
                        list.deleteAtPosition(delPos);
                        list.display();
                        break;
                    case 5:
                        list.display();
                        break;
                    case 6:
                        System.out.println("Exiting LinkedList driver. Happy coding!");
                        running = false;
                        break;
                    default:
                        System.out.println("Choice " + choice + " is unrecognized. Please enter 1-6.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.err.println("Operation Error: " + ex.getMessage());
            }
        }
        scanner.close();
    }
}
