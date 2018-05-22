package will.zhang.segmentTree;

/**
 * 线段树
 * @param <E>
 */
public class ASegmentTree<E> {

    //存放数据的线段树
    private E[] tree;
    //存放数据的数组
    private E[] data;

    public ASegmentTree(E[] arr){
        //数组初始化
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //线段树初始化, 最坏情况下, 线段树需要占用 4 * arr.length的空间
        tree = (E[]) new Object[arr.length * 4];
    }

    /**
     * 获取数组中索引为index的元素
     * @param index
     * @return
     */
    public E get(int index){
         if(index < 0 || index >= data.length){
             throw new IllegalArgumentException("Index is Illegal.");
         }
         return data[index];
    }

    /**
     * 获取数组大小
     * @return
     */
    public int getSize(){
        return data.length;
    }

    /**
     * 获取给定索引值在完全二叉树中的左孩子
     * @param index
     * @return
     */
    public int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 获取给定索引值在完全二叉树中的右孩子
     * @param index
     * @return
     */
    public int rightChild(int index){
        return 2 * index + 2;
    }
}
