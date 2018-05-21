package will.zhang.heap;

import java.util.Random;

/**
 * 使用数组实现最大堆
 * @param <E>
 */
public class CMaxHeap<E extends Comparable<E>> {

    //存放数据
    private Array<E> data;

    public CMaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public CMaxHeap(){
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
     * 查询堆顶元素(最大元素)
     * @return
     */
    public E findMax(){
        if(data.isEmpty()){
            throw new IllegalArgumentException("Heap is Empty!");
        }
        return data.get(0);
    }

    public E extractMax(){
        E result = findMax();
        //取到堆顶元素后, 把堆顶和最后一个元素交换, 然后删除最后一个元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        //把交换后的元素下沉到适合的位置
        siftDown(0);
        return result;
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

    /**
     * 堆的核心方法, 元素下沉到适当位置(符合堆的定义的位置, 比父亲节点小并且比孩子节点大)
     * @param k
     */
    private void siftDown(int k) {
        //循环条件未左孩子没有越界
        while(leftChild(k) < data.getSize()){
            //左孩子所在的索引
            int j = leftChild(k);
            //如果右孩子没有越界并且大于左孩子
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                //如果熟悉了可以写成j++,为了语义清晰所以写成j = rightChild(k)
                j = rightChild(k);
            }

            //这时候j的索引已经表示左右孩子中较大的那一个

            //左右孩子中最大值和k对比, 如果k已经比左右孩子大, 则下沉结束
            if(data.get(k).compareTo(data.get(j)) >= 0){
                break;
            }
            //否则k和j交换
            data.swap(k, j);
            //索引也走到j的位置, 准备下一轮
            k = j;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;

        CMaxHeap<Integer> heap = new CMaxHeap<>(n);

        Random random = new Random();
        //往堆中插入100万随机数
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        //每一次取出最大值放进arr中, 如果没错, arr已经是从大到小排列好
        for (int i = 0; i < n; i++) {
            arr[i] = heap.extractMax();
        }

        //如果i-1比i还要小, 那么代表有问题
        for (int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test MaxHeap Completed.");
    }

}
