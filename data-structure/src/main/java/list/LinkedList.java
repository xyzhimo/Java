package list;

public class LinkedList {

    /**
     * 头指针
     */
    private Node head;

    public void insertHead(Node node) {
        if (head != null) {
            node.next = head;
        }
        head = node;
    }

    public void insertHead(Integer data) {
        Node node = new Node(data, null);
        insertHead(node);
    }

    public void insertTail(Integer data) {
        Node node = new Node(data, null);
        insertTail(node);
    }

    public void insertTail(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("node is null");
        }
        if (head == null) {
            head = node;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
//            node.next = null;
        }
    }

    public void printAll() {
        Node point = head;
        while (point != null) {
            System.out.println(point.data);
            point = point.next;
        }
    }

    /**
     * 删除所有重复的值节点
     */
    public void deleteNode0(Object data) {
        Node p = this.head;
        if (p == null) {
            return;
        }

        while (p != null) {

            if (head.data.equals(data)) {
                this.head = this.head.next;
            }

            Node q = p.next;
            if (q != null && q.data.equals(data)) {
                p.next = q.next;
                continue;
            }
            p = q;
        }
    }

    /**
     * 删除单个值的节点
     */
    public void deleteNode1(Object data) {
        if (head == null) {
            return;
        }

        Node p = this.head;

        // 当出现数据相同, q = p.prev;
        Node q = null;
        while (p != null && !p.data.equals(data)) {
            // 如果数据不同
            q = p;
            p = p.next;
        }

        if (p == null) {
            return;
        }

        if (q == null) {
            head = head.next;
        } else {
            q.next = p.next;
        }
    }

    public void reverse0() {
        if (head == null) {
            return;
        }
        Node p = head;
        Node q = null;
        while (p != null) {
            Node o = p.next;
            p.next = q;
            q = p;
            p = o;
        }
        head = q;
    }

    public Node reverse1(Node current) {
        if (current == null || current.next == null) {
            return current;
        }
        Node nextNode = current.next;
        current.next = null;
        Node tail = reverse1(nextNode);
        nextNode.next = current;
        head = tail;
        return head;
    }

    /**
     * 环的检测
     */
    public boolean isCycle() {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast != null && fast == slow) {
                return true;
            }
        }
        return false;
    }

    public LinkedList mergeList(LinkedList a, LinkedList b) {
        LinkedList c = new LinkedList();
        Node p = a.head;
        Node q = b.head;
        while (p != null || q != null) {
            if (p == null) {
                c.insertTail(q);
                break;
            }

            if (q == null) {
                c.insertTail(p);
                break;
            }

            if (p.data < q.data) {
                c.insertTail(p.data);
                p = p.next;
            } else {
                c.insertTail(q.data);
                q = q.next;
            }
        }
        return c;
    }

    public void deleteLastN(int n) {
        Node p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }


        Node o = head;
        Node q;
        int count = 1;
        while (o != null) {
            q = o;
            o = o.next;
            if (count == length - n) {
                q.next = o.next;
            }
            count++;
        }
    }

    public void getMiddleNode() {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 偶数
        if (fast == null) {
            // 下位中位数
            System.out.println(slow.next.data);
        } else {
            // 奇数中位数
            System.out.println(slow.data);
        }
    }


    public boolean isBack() {
        Node fast = head;
        Node slow = head;
        Node prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node after = slow.next;
            slow.next = prev;
            prev = slow;
            slow = after;
        }
        // 偶数
        if (fast == null) {
            while (slow != null && prev != null) {
                if (!slow.data.equals(prev.data)) {
                    return false;
                }
                slow = slow.next;
                prev = prev.next;
            }
            return true;
        } else {
            Node forward = slow.next;
            while (prev != null && forward != null) {
                if (!prev.data.equals(forward.data)) {
                    return false;
                }
                prev = prev.next;
                forward = forward.next;
            }
            return true;
        }
    }

    public void printAll0(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        printAll0(node.next);
        System.out.println(node.data);
    }



    // 两个有序的链表合并
    // 删除链表倒数第 n 个结点
    // 求链表的中间结点
    // 判断是否是回文

    public static void main(String[] args) {
        LinkedList list0 = new LinkedList();
//        list.insertHead(5);
//        list.insertHead(4);
//        list.insertHead(3);
//        list.insertHead(2);
//        list.insertHead(1);
        list0.insertTail(1);
        list0.insertTail(2);
        list0.insertTail(10);
        list0.insertTail(14);
        list0.insertTail(53);
        list0.insertTail(78);
//        list.insertTail(new Node(6, node));


//        list.deleteNode1(3);
//        list.printAll();

//        list.reverse0();
//        list.reverse1(list.head);
//        boolean cycle = list.isCycle();
//        System.out.println(cycle);


        LinkedList list1 = new LinkedList();
//        list.insertHead(5);
//        list.insertHead(4);
//        list.insertHead(3);
//        list.insertHead(2);
//        list.insertHead(1);
        list1.insertTail(7);
        list1.insertTail(8);
        list1.insertTail(19);
        list1.insertTail(30);
        list1.insertTail(41);

        LinkedList list2 = new LinkedList();
        LinkedList linkedList = list2.mergeList(list0, list1);

        linkedList.printAll();

        System.out.println("===============删除倒数第K个节点=================");
        list1.deleteLastN(3);
        list1.printAll();

        System.out.println("================找到链表的中间节点================");
        list0.getMiddleNode();

        System.out.println("================是否是回文字符串=================");
        LinkedList list_b = new LinkedList();
        list_b.insertTail(1);
        list_b.insertTail(2);
        list_b.insertTail(3);
        list_b.insertTail(10);
        list_b.insertTail(5);
        list_b.insertTail(10);
        list_b.insertTail(3);
        list_b.insertTail(2);
        list_b.insertTail(1);
        System.out.println(list_b.isBack());
    }
}
