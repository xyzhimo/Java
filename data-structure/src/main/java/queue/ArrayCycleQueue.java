package queue;

public class ArrayCycleQueue {

    private int[] items;

    private int capacity;

    private int head;

    private int tail;

    public ArrayCycleQueue(int capacity) {
        items = new int[capacity];
        this.capacity = capacity;
        head = 0;
        tail = 0;
    }

    /**
     * 循环数组总是会少一个空间
     */
    public void enqueue(int num) {
        if ((tail + 1) % capacity == head) {
            throw new IllegalStateException("the queue is full");
        }
        items[tail] = num;
        tail = (tail + 1) % capacity;
    }

    public int dequeue() {
        if (head == tail) {
            throw new IllegalStateException("the queue is empty");
        }
        int item = items[head];
        head = (head + 1) % capacity;
        return item;
    }

    public static void main(String[] args) {
        ArrayCycleQueue queue = new ArrayCycleQueue(10);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);

        System.out.println(queue.head);
        System.out.println(queue.tail);

        System.out.println("=================");

        for (int i = 0; i < 9; i++) {
            System.out.println(queue.dequeue());
        }

        System.out.println("=================");
        System.out.println(queue.head);
        System.out.println(queue.tail);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);


        System.out.println("=================");
        System.out.println(queue.head);
        System.out.println(queue.tail);

        System.out.println("=================");
        for (int i = 0; i < 4; i++) {
            System.out.println(queue.dequeue());
        }

        System.out.println("=================");
        System.out.println(queue.head);
        System.out.println(queue.tail);

    }
}
