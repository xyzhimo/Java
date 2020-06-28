package stack;

import list.Node;

public class LinkedStack {

    protected Node head;

    public void push(int num) {
        Node node = new Node(num, null);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public int pop() {
        if (head == null) {
            throw new IllegalStateException("the stack is empty");
        }
        Node node = head;
        head = head.next;
        return node.data;
    }

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);

        for (int i = 0; i < 10; i++) {
            int pop = stack.pop();
            System.out.println(pop);
        }
    }
}
