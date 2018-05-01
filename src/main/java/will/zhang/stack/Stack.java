package will.zhang.stack;

/**
 * 包含一个盏的基本方法
 * @param <E>
 */
public interface Stack<E> {

    /**
     * 查看盏中元素的个数
     * @return
     */
    int getSize();

    /**
     * 盏是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 往盏放入一个元素:入栈
     */
    void push(E e);

    /**
     * 从盏取出一个元素:出盏
     */
     E pop();

    /**
     * 查看栈顶的元素
     */
     E peek();
}
