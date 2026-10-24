import java.util.Scanner;

class StackDemo {
    private int nums[];
    private int top;

    // Default constructor (defaults to capacity of 10)
    public StackDemo() {
        this(10);
    }

    // Parameterized constructor to set array size dynamically based on user input
    public StackDemo(int size) {
        if (size <= 0) {
            System.out.println("Warning: Size must be positive. Defaulting capacity to 5.");
            size = 5;
        }
        this.nums = new int[size];
        this.top = -1;
    }

    public int getCapacity() {
        return nums.length;
    }

    public int getSize() {
        return top + 1;
    }

    public void push(int num) {
        if (top == nums.length - 1) {
            System.out.println("Stack is full. Cannot push " + num);
            return;
        }
        nums[++top] = num;
        System.out.println("Successfully pushed: " + num);
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;
        }
        int num = nums[top--];
        System.out.println("Popped: " + num);
        return num;
    }

    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty. Cannot peek.");
            return -1;
        }
        System.out.println("Top element (peek): " + nums[top]);
        return nums[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == nums.length - 1;
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack (Top to Bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}

public class Stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("         CUSTOM STACK PRACTICE           ");
        System.out.println("=========================================");

        // Take stack size as input from the user
        System.out.print("Enter the size of the stack: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid integer for stack size: ");
            scanner.next();
        }
        int userSize = scanner.nextInt();

        // Create the stack with user-defined capacity
        StackDemo stack = new StackDemo(userSize);
        System.out.println("Stack created with capacity: " + stack.getCapacity());

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("  1. Push");
            System.out.println("  2. Pop");
            System.out.println("  3. Peek");
            System.out.println("  4. Display Stack");
            System.out.println("  5. Check Empty / Full Status");
            System.out.println("  6. Exit");
            System.out.print("Enter your choice (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter integer value to push: ");
                    if (scanner.hasNextInt()) {
                        int value = scanner.nextInt();
                        stack.push(value);
                    } else {
                        System.out.println("Invalid input. Must be an integer.");
                        scanner.next();
                    }
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println("Is Empty : " + stack.isEmpty());
                    System.out.println("Is Full  : " + stack.isFull());
                    System.out.println("Occupancy: " + stack.getSize() + " / " + stack.getCapacity());
                    break;
                case 6:
                    System.out.println("Exiting Stack program. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from 1 to 6.");
            }
        }

        scanner.close();
    }
}
