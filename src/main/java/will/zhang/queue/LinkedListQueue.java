package will.zhang.queue;

import will.zhang.stack.LinkedListStack;

/**
 * @Author will
 * @Date 2018/5/8 0008 下午 4:48
 * 使用带有尾指针的链表实现一个队列
 **/
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //头结点和尾节点
    private Node head, tail;
    //元素的个数
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        //tail为空的情况下, 其实也代表了head也为空, 说明了队列没有任何元素
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        Node  retNode = head;
        head = head.next;
        retNode.next = null;
        //当head = head.next的时候, head有可能为空, 也就是链表只有一个元素的时候
        //这时需要把tail也维护一下
        if(head == null){
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: front ");
        Node cur = head;
        while (cur != null){
            builder.append(cur + "->");
            cur = cur.next;
        }
        builder.append("NULL tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
