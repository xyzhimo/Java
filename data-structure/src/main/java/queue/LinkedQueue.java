package queue;

import list.Node;

public class LinkedQueue {

    private Node head;

    private Node tail;

    public void enqueue(int num) {
        Node node = new Node(num, null);
        if (tail == null) {
            // 出错的地方
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }

    public Integer dequeue() {
        if (head == null) {
            return null;
        }
        Integer result = head.data;
        head = head.next;
        // 出错的地方
        if (head == null) {
            tail = null;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);

        for (int i = 0; i < 9; i++) {
            System.out.println(queue.dequeue());
        }


        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        for (int i = 0; i < 1; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
