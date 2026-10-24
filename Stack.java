/**
 * ============================================================================
 * File: Stack.java
 * Repository: my-java-practice
 * Description: Array-based implementation of the Stack (LIFO) Data Structure.
 *   - push(int data) : Push element onto stack O(1) amortized
 *   - pop()          : Remove and return top element O(1)
 *   - peek()         : Inspect top element without removing O(1)
 *   - isEmpty()      : Check if stack is empty O(1)
 *   - size()         : Return number of elements O(1)
 *   - Dynamic resizing when capacity threshold is reached.
 *
 * How to Compile & Run:
 *   javac Stack.java
 *   java Stack
 * ============================================================================
 */

import java.util.EmptyStackException;

/**
 * Custom Stack implementation utilizing a dynamically resizable array.
 */
public class Stack {
    private int[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 4;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Stack initial capacity must be positive.");
        }
        this.elements = new int[initialCapacity];
        this.top = -1; // -1 denotes an empty stack
    }

    /**
     * Pushes an element onto the top of the stack.
     * Automatically expands capacity when the internal array is full.
     *
     * @param data integer value to push
     */
    public void push(int data) {
        if (top == elements.length - 1) {
            resize(elements.length * 2);
        }
        elements[++top] = data;
        System.out.println("  [Pushed] " + data + " -> Current Stack Size: " + size());
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return top element
     * @throws EmptyStackException if the stack contains no elements
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int value = elements[top--];
        System.out.println("  [Popped] " + value + " -> Remaining Stack Size: " + size());
        return value;
    }

    /**
     * Retrieves the element at the top of the stack without removing it.
     *
     * @return top element
     * @throws EmptyStackException if the stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top];
    }

    /**
     * Checks if the stack has zero elements.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the total count of elements currently in the stack.
     */
    public int size() {
        return top + 1;
    }

    /**
     * Displays current elements from top to bottom.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("  Stack: [Empty]");
            return;
        }
        System.out.print("  Stack (Top to Bottom): [");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + (i > 0 ? ", " : ""));
        }
        System.out.println("]");
    }

    /**
     * Doubles the capacity of the backing array.
     */
    private void resize(int newCapacity) {
        int[] newElements = new int[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size());
        elements = newElements;
        System.out.println("  [Info] Stack auto-expanded capacity to: " + newCapacity);
    }

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("       CUSTOM STACK DATA STRUCTURE DEMO (LIFO)       ");
        System.out.println("=====================================================");

        Stack stack = new Stack(3);

        System.out.println("\n--- Pushing Elements onto Stack ---");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40); // Triggers dynamic array resizing
        stack.display();

        System.out.println("\n--- Inspecting Top Element (peek) ---");
        System.out.println("  Top Element (peek): " + stack.peek());

        System.out.println("\n--- Popping Elements from Stack ---");
        stack.pop();
        stack.pop();
        stack.display();

        System.out.println("\n--- Handling Empty Stack Exception ---");
        stack.pop();
        stack.pop();
        try {
            stack.pop(); // Will throw EmptyStackException
        } catch (EmptyStackException ex) {
            System.err.println("  [Caught Expected Exception] Cannot pop from an empty stack!");
        }

        System.out.println("\n=====================================================");
        System.out.println("          STACK DEMO COMPLETED SUCCESSFULLY          ");
        System.out.println("=====================================================");
    }
}
