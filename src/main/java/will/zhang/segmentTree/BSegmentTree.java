package will.zhang.segmentTree;

/**
 * 线段树
 * @param <E>
 */
public class BSegmentTree<E> {

    //存放数据的线段树
    private E[] tree;
    //存放数据的数组
    private E[] data;
    //由调用方实现的接口, 表示线段树左孩子和右孩子该怎么计算
    private Merger<E> merger;

    public BSegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        //数组初始化
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //线段树初始化, 最坏情况下, 线段树需要占用 4 * arr.length的空间
        tree = (E[]) new Object[arr.length * 4];

        //创建线段树
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 以treeIndex为根, 创建出data[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            //递归到底的情况, tree[treeIndex] = data[r]也可以, 因为l==r
            tree[treeIndex] = data[l];
            return;
        }

        //计算左右孩子的索引
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        //计算出线段树需要从哪里开始分割
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid+1, r);

        //tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
        //如果这样写, 线段树就局限于只能求和这个业务了
        //应该传进来一个接口, 是求和, 求最小值, 求最大值, 或者差值由用户来实现
        //实现Merger接口即可
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);

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

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] != null){
                builder.append(tree[i]);
            }else{
                builder.append("Null");
            }
            if(i != tree.length - 1){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        BSegmentTree<Integer> tree = new BSegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(tree);
    }
}
