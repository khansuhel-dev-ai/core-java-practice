package datastructures.linkedlist;

import datastructures.common.Node;
import java.util.Scanner;

/**
 * ============================================================================
 * Class: SinglyLinkedList
 * Package: datastructures.linkedlist
 * Description: Generic Singly Linked List utilizing common Node<T>.
 *   - Insertion: At Start O(1), At End O(n), At Specific Index O(n)
 *   - Deletion: At Specific Index O(n)
 *   - Traversal: Display elements from head to tail O(n)
 * ============================================================================
 */
public class SinglyLinkedList {
    private Node<Integer> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void insertAtStart(int data) {
        Node<Integer> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtEnd(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node<Integer> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position " + position + " out of bounds (Valid: 0 to " + size + ").");
        }
        if (position == 0) {
            insertAtStart(data);
            return;
        }
        Node<Integer> newNode = new Node<>(data);
        Node<Integer> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void deleteAtPosition(int position) {
        if (head == null) {
            throw new IllegalStateException("Cannot delete from an empty list.");
        }
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Position " + position + " out of bounds (Valid: 0 to " + (size - 1) + ").");
        }
        if (position == 0) {
            head = head.next;
            size--;
            return;
        }
        Node<Integer> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public void display() {
        if (head == null) {
            System.out.println("  SinglyLinkedList: [Empty] (null)");
            return;
        }
        System.out.print("  SinglyLinkedList (Size " + size + "): ");
        Node<Integer> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        System.out.println("=====================================================");
        System.out.println("        SINGLY LINKED LIST DEMONSTRATION             ");
        System.out.println("=====================================================");

        list.insertAtStart(10);
        list.insertAtStart(5);
        list.insertAtEnd(20);
        list.insertAtPosition(15, 2);
        list.display();

        System.out.println("Deleting node at index 1 (value 10):");
        list.deleteAtPosition(1);
        list.display();
    }
}

