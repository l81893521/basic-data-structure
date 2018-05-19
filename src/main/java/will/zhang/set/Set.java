package will.zhang.set;

public interface Set<E> {

    /**
     * 往集合中添加元素
     * 不允许保存重复的元素
     * @param e
     */
    void add(E e);

    /**
     * 从集合中删除元素
     * @param e
     */
    void remove(E e);

    /**
     * 查看集合是否包含元素e
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 获取集合元素个数
     * @return
     */
    int getSize();

    /**
     * 查看集合是否为空
     * @return
     */
    boolean isEmpty();
}
