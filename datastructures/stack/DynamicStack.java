package datastructures.stack;

/**
 * ============================================================================
 * Class: DynamicStack
 * Package: datastructures.stack
 * Description: Dynamically resizable Stack implementation that automatically
 *              doubles its array capacity when full, avoiding fixed-size limits.
 * ============================================================================
 */
class DStack {
    private int[] nums;
    private int top;
    private static final int INITIAL_CAPACITY = 4;

    public DStack() {
        this(INITIAL_CAPACITY);
    }

    public DStack(int initialCapacity) {
        this.nums = new int[initialCapacity];
        this.top = -1;
    }

    public void push(int num) {
        if (top == nums.length - 1) {
            resize(nums.length * 2); // Automatically double capacity
        }
        nums[++top] = num;
        System.out.println("  [Pushed] " + num + " (Size: " + (top + 1) + ", Capacity: " + nums.length + ")");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("  Stack is empty. Cannot pop.");
            return -1;
        }
        int num = nums[top--];
        System.out.println("  [Popped] " + num + " (Remaining: " + (top + 1) + ")");
        return num;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("  Stack is empty. Cannot peek.");
            return -1;
        }
        return nums[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getSize() {
        return top + 1;
    }

    public int getCapacity() {
        return nums.length;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("  Stack is empty.");
            return;
        }
        System.out.print("  DynamicStack (Top to Bottom): [ ");
        for (int i = top; i >= 0; i--) {
            System.out.print(nums[i] + (i > 0 ? ", " : " "));
        }
        System.out.println("] (Capacity: " + nums.length + ")");
    }

    private void resize(int newCapacity) {
        int[] expanded = new int[newCapacity];
        System.arraycopy(nums, 0, expanded, 0, nums.length);
        nums = expanded;
        System.out.println("  >> Stack auto-resized capacity to: " + newCapacity);
    }
}

public class DynamicStack {
    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("        DYNAMIC RESIZABLE STACK DEMONSTRATION        ");
        System.out.println("=====================================================");

        DStack stack = new DStack(3); // Start with small capacity of 3

        System.out.println("\n--- Pushing 6 elements (triggers auto-resizing) ---");
        for (int i = 10; i <= 60; i += 10) {
            stack.push(i);
        }
        stack.display();

        System.out.println("\n--- Popping elements ---");
        stack.pop();
        stack.pop();
        stack.display();

        System.out.println("\nPeek: " + stack.peek());
        System.out.println("Is Empty: " + stack.isEmpty());
    }
}

