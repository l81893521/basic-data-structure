package will.zhang.avlTree;


import will.zhang.map.Map;

/**
 * AVL树
 * 除了计算节点高度了平衡因子, 其余代码均为二分搜索树的代码
 */
public class AAVLTree<K extends Comparable, V>{

    //根节点
    private Node root;
    private int size;

    public AAVLTree(){
        root = null;
        size = 0;
    }

    /**
     * 往二分搜索树添加一个节点
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 以node为根的二分搜索树插入元素(key, value)
     * @param node
     * @param key
     * @param value
     * @Return 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }else{
            //之前如果e和node.e相等, 按照自己要求做处理, 这里不做任何处理
            //但是在映射(Map)中, 通常用户都是想修改该key的值, 所以改变了一下处理方式
            node.value = value;
        }
        //更新Height
        //取当前节点的左子树和右子树高度较高的再加上1
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //平衡因子大于1, 已经不满足平衡二叉树的条件了
        if(Math.abs(balanceFactor) > 1){
            //需要进行自平衡操作
        }

        return node;
    }

    /**
     * 删除键等于key的节点
     * @param key
     * @return
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二分搜索树键为key的节点
     * 返回删除节点之后新的二分搜索树的根
     * @param node
     * @param key
     */
    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else{  //e == node.e
            //如果待删除的节点, 没有左子树
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if(node.right == null){   //如果待删除的节点, 没有右子树
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //如果同时存在左右子树
            //找出待删除节点的右子树中的最小值的节点, 也就是前驱(也可以找左子树中的最大值, 后继)
            Node successor = minimum(node.right);
            //把前驱的右子树, 指向删除最小值后的右子树(删除前驱原来所在的位置的节点)
            successor.right = removeMin(node.right);
            //前驱的左子树和右子树已经正确连接上
            successor.left = node.left;
            //清除原来的引用
            node.left = node.right = null;
            //返回
            return successor;
        }
    }

    /**
     * 是否存在节点key
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 获取节点key
     * @param key
     * @return
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 更新节点key的值
     * @param key
     * @param newValue
     */
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 获取二分搜索树节点的个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 当前二分搜索树是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在以node为根的二分搜索树中
     * 返回key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else{
            return getNode(node.right, key);
        }
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除以node为根的二分搜索树的最小值的节点
     * 返回删除节点之后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 获得节点的高度值
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获取节点Node的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        //左子树的高度减去右子树的高度
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 节点
     * avl树的私有类
     * 对用户屏蔽, 用户无需知道
     */
    private class Node{
        //这里用了K key和V value分别存放映射的键和值
        public K key;
        public V value;
        //左节点和右节点
        public Node left, right;
        //记录每一个节点的高度值
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            //初始化的时候节点的高度值为1
            height = 1;
        }
    }
}
