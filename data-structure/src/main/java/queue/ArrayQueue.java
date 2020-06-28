package queue;

public class ArrayQueue {

    private int[] items;

    private int capacity;

    private int head;

    private int tail;

    public ArrayQueue(int capacity) {
        items = new int[capacity];
        this.capacity = capacity;
        head = 0;
        tail = 0;
    }

    /**
     * 数组在入队时做一次数据迁移
     */
    public void enqueue(int num) {
        if (tail == capacity) {
            if (head == 0) {
                throw new IllegalStateException("the queue is full");
            }
            // 新的最后一个指针
            // 减法总是需要 +1 才能直到有几个数字, 但是数组总是从 0 开始, 所以不需要加一
            tail = capacity - head;
            // 迁移数据
            for (int i = 0; i < tail; i++) {
                items[i] = items[head + i];
            }
            head = 0;
        }
        items[tail++] = num;
    }

    public int dequeue() {
        if (head == tail) {
            throw new IllegalStateException("the queue is empty");
        }
        return items[head++];
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);

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

        System.out.println(queue.head);
        System.out.println(queue.tail);

        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }

        System.out.println(queue.head);
        System.out.println(queue.tail);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.head);
        System.out.println(queue.tail);
    }
}
