package will.zhang.binarySearchTree;

/**
 * @Author will
 * @Date 2018/5/16 0016 下午 6:32
 **/
public class ABinarySearchTree<E extends Comparable<E>> {

    /**
     * 节点
     * 二分搜索树的私有类
     * 对用户屏蔽, 用户无需知道
     */
    private class Node{
        //存放数据
        public E e;
        //左节点和右节点
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * 数的大小
     */
    private int size;

    /**
     * 默认构造函数
     */
    public ABinarySearchTree(){
        root = null;
        size = 0;
    }

    /**
     * 二分搜索树的大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 树是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }
}
