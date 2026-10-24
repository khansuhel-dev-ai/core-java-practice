class DStack {
    
    int nums[] = new int[10];
    int top = -1;

    public void push(int num) {
        if (top == nums.length - 1) {
            System.out.println("Stack is full. Cannot push " + num);
            return;
        }
        nums[++top] = num;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;
        }
        int num = nums[top--];
        return num;
    }

    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty. Cannot peek.");
            return -1;
        }
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

public class DynamicStack{
    public static void main(String[] args) {
        DStack stack = new DStack();
        for (int i = 1; i <= 12; i++) {
            stack.push(i);
        }
        stack.display();

        System.out.println("Popped: " + stack.pop());
        stack.display();

        System.out.println("Peek: " + stack.peek());
        System.out.println("Is Empty: " + stack.isEmpty());
        System.out.println("Is Full: " + stack.isFull());
    }
}
