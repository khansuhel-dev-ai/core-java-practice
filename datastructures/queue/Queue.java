package datastructures.queue;

import java.util.Scanner;

class QueueDemo {
    private int[] nums;
    private int front;
    private int rear;
    private int size;

    public QueueDemo() {
        this(10);
    }

    public QueueDemo(int capacity) {
        if (capacity <= 0) {
            System.out.println("Warning: Capacity must be positive. Defaulting to 5.");
            capacity = 5;
        }
        this.nums = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public int getCapacity() {
        return nums.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == nums.length;
    }

    public void enqueue(int num) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot enqueue " + num);
            return;
        }
        nums[rear] = num;
        rear = (rear + 1) % nums.length;
        size++;
        System.out.println("Successfully enqueued: " + num);
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return -1;
        }
        int num = nums[front];
        front = (front + 1) % nums.length;
        size--;
        System.out.println("Dequeued: " + num);
        return num;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot peek.");
            return -1;
        }
        System.out.println("Front element (peek): " + nums[front]);
        return nums[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty: [ ]");
            return;
        }
        System.out.print("Queue (Front to Rear): [ ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % nums.length;
            System.out.print(nums[index] + (i < size - 1 ? " -> " : " "));
        }
        System.out.println("]");
    }
}

public class Queue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("         CUSTOM QUEUE PRACTICE (FIFO)    ");
        System.out.println("=========================================");

        System.out.print("Enter the size of the queue: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a positive integer for queue size: ");
            scanner.next();
        }
        int userSize = scanner.nextInt();

        QueueDemo queue = new QueueDemo(userSize);
        System.out.println("Queue created with capacity: " + queue.getCapacity());

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("  1. Enqueue (Insert element)");
            System.out.println("  2. Dequeue (Remove element)");
            System.out.println("  3. Peek (View front element)");
            System.out.println("  4. Display Queue");
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
                    System.out.print("Enter integer value to enqueue: ");
                    if (scanner.hasNextInt()) {
                        int value = scanner.nextInt();
                        queue.enqueue(value);
                    } else {
                        System.out.println("Invalid input. Must be an integer.");
                        scanner.next();
                    }
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.peek();
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    System.out.println("Is Empty : " + queue.isEmpty());
                    System.out.println("Is Full  : " + queue.isFull());
                    System.out.println("Occupancy: " + queue.getSize() + " / " + queue.getCapacity());
                    break;
                case 6:
                    System.out.println("Exiting Queue program. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from 1 to 6.");
            }
        }

        scanner.close();
    }
}

