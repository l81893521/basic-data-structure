package will.zhang.queue;

public interface Queue<E> {

    /**
     * 获取队列的大小
     * @return
     */
    int getSize();

    /**
     * 查看队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 把元素放入到队列中:入队
     * @param e
     */
    void enqueue(E e);

    /**
     * 从队列中取出元素:出队
     * @return
     */
    E dequeue();

    /**
     * 查看队列第一个元素
     * @return
     */
    E getFront();
}
