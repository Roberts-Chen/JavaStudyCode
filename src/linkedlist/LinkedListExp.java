package linkedlist;


import java.util.PriorityQueue;
import java.util.Queue;

class LinkedNode {
    public int val;
    public LinkedNode next;
    public LinkedNode(int val) {
        this.val = val;
        next = null;
    }
    public LinkedNode() {
    }
}
public class LinkedListExp {
    public static void main(String[] args) {
        LinkedNode head = new LinkedNode(-1);
        LinkedNode p = head;
        // 尾插法：在链表的尾部插入数据
        for (int i = 0; i < 10; i++) {
            LinkedNode node = new LinkedNode(i);
            p.next = node;
            p = node;
        }

        // 直接使用其内置的比较器，默认是一个小根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);
        System.out.println(pq.peek());

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Integer::compareTo);
        pq2.offer(3);
        pq2.offer(1);
        pq2.offer(2);
        System.out.println(pq2.peek());
    }
}
