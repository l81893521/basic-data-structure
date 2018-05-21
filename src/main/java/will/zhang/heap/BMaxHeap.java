package will.zhang.heap;

/**
 * 使用数组实现最大堆
 * @param <E>
 */
public class BMaxHeap<E extends Comparable<E>> {

    //存放数据
    private Array<E> data;

    public BMaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public BMaxHeap(){
        data = new Array<>();
    }

    /**
     * 获取堆的大小
     * @return
     */
    public int size(){
        return data.getSize();
    }

    /**
     * 返回堆是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 返回父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 堆的核心方法, 元素上浮至适当位置(符合堆的定义的位置, 比父亲节点小并且比孩子节点大)
     * @param k
     */
    private void siftUp(int k) {
        //如果当前k比父亲节点parent(k)大, 则交换位置
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

}
