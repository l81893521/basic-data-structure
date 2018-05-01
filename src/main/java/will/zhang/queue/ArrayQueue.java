package will.zhang.queue;

/**
 * @Author will
 * @Date 2018/5/1 0001 下午 7:49
 * 实现基本的数组队列
 *
 * 复杂度分析
 * void enqueue(E)      O(1) 均摊
 * E dequeue()          O(n)
 * E front()            O(1)
 * int getSize()        O(1)
 * boolean isEmpty()    O(1)
 **/
public class ArrayQueue<E> implements Queue<E> {

    //使用上一章实现的动态数组来存放数据
    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 获取队列的容量
     * @return
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: ");
        builder.append("front [");
        builder.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if(i != array.getSize() - 1){
                builder.append(", ");
            }
        }
        builder.append("] tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
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
