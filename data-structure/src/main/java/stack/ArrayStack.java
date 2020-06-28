package stack;

import java.util.Arrays;

public class ArrayStack {

    protected int[] items;

    protected int count;

    protected int capacity;

    public ArrayStack(int capacity) {
        items = new int[capacity];
        count = 0;
        this.capacity = capacity;
    }

    public void push(int num) {
        if (count == capacity) {
            items = Arrays.copyOf(items, capacity + (capacity >> 1));
        }
        items[count] = num;
        count++;
    }

    public int pop() {
        if (count == 0) {
            throw new IllegalStateException("the stack is empty");
        }
        return items[--count];
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(9);
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);

        for (int i = 0; i < 15; i++) {
            int pop = stack.pop();
            System.out.println(pop);
        }
    }
}
