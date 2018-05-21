package will.zhang.heap;

/**
 * 使用数组实现最大堆
 * @param <E>
 */
public class AMaxHeap<E extends Comparable<E>> {

    //存放数据
    private Array<E> data;

    public AMaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public AMaxHeap(){
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

}
