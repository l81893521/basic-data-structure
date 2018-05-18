package will.zhang.binarySearchTree;

/**
 * @Author will
 * @Date 2018/5/16 0016 下午 6:32
 **/
public class DBinarySearchTree<E extends Comparable<E>> {

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
    public DBinarySearchTree(){
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

    /**
     * 为二分搜索数添加元素
     * @param e
     */
    public void add(E e){
        root = add(root, e);
    }

    /**
     * 优化插入方法
     * 以node为根的二分搜索树插入元素e
     * @param node
     * @param e
     * @Return 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e){
        if(node == null){
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }else{
            //如果e和node.e相等, 按照自己要求做处理, 这里不做任何处理
        }
        return node;
    }

    /**
     * 查看二分搜索树中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        if(node.e.compareTo(e) == 0){
            return true;
        }else if(node.e.compareTo(e) < 0){
            return contains(node.left, e);
        }else {
            return contains(node.right,e );
        }
    }
}
