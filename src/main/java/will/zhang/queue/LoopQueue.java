package will.zhang.queue;

import java.util.Arrays;

/**
 * @Author will
 * @Date 2018/5/1 0001 下午 8:13
 * 循环队列实现
 *
 * void enqueue(E)      O(1) 均摊
 * E dequeue()          O(1) 均摊
 * E front()            O(1)
 * int getSize()        O(1)
 * boolean isEmpty()    O(1)
 **/
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    //使用两个指针分别表示队列的队头和队尾
    private int front, tail;
    //队列的元素个数
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    /**
     * 获取队列的容量
     * @return
     */
    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //如果循环队列已经满了
        if((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }

        E ret = data[front];
        //手动去除引用, 垃圾回收
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //缩容
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    /**
     * 队列扩容
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        //以下方法等同于Arrays.toString(arr);
        builder.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            builder.append(data[i]);
            if((i + 1) % data.length != tail){
                builder.append(", ");
            }
        }
        builder.append("] tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
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
