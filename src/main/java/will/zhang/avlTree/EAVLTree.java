package will.zhang.avlTree;


import java.util.ArrayList;

/**
 * AVL树, 是一颗基于二分搜索树实现的平衡二叉树
 */
public class EAVLTree<K extends Comparable, V>{

    //根节点
    private Node root;
    private int size;

    public EAVLTree(){
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

        //平衡维护(LL), 进行右旋转操作
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //平衡维护(RR), 进行左旋转操作
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }

        //平衡维护(LR), 进行左旋再右旋操作
        if(balanceFactor > 1 && getBalanceFactor((node.left)) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //平衡维护(RL), 进行右旋再左旋操作
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
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

        Node resultNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            resultNode = node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            resultNode = node;
        }else{  //e == node.e
            //如果待删除的节点, 没有左子树
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                resultNode = rightNode;
            }else if(node.right == null){   //如果待删除的节点, 没有右子树
                Node leftNode = node.left;
                node.left = null;
                size--;
                resultNode = leftNode;
            }else {

                //如果同时存在左右子树
                //找出待删除节点的右子树中的最小值的节点, 也就是前驱(也可以找左子树中的最大值, 后继)
                Node successor = minimum(node.right);
                //把前驱的右子树, 指向删除最小值后的右子树(删除前驱原来所在的位置的节点)
                //不能再使用removeMin(Node)这个函数, 因为这个函数并没有维护自平衡, 有可能打破平衡二叉树
                //successor.right = removeMin(node.right);
                successor.right = remove(node, successor.key);
                //前驱的左子树和右子树已经正确连接上
                successor.left = node.left;
                //清除原来的引用
                node.left = node.right = null;
                //返回
                resultNode = successor;
            }
        }

        //resultNode有可能存在空的情况
        if(resultNode == null) return null;

        //更新Height
        //取当前节点的左子树和右子树高度较高的再加上1
        resultNode.height = 1 + Math.max(getHeight(resultNode.left), getHeight(resultNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(resultNode);

        //平衡维护(LL), 进行右旋转操作
        if(balanceFactor > 1 && getBalanceFactor(resultNode.left) >= 0){
            return rightRotate(resultNode);
        }
        //平衡维护(RR), 进行左旋转操作
        if(balanceFactor < -1 && getBalanceFactor(resultNode.right) <= 0){
            return leftRotate(resultNode);
        }

        //平衡维护(LR), 进行左旋再右旋操作
        if(balanceFactor > 1 && getBalanceFactor(resultNode.left) < 0){
            resultNode.left = leftRotate(resultNode.left);
            return rightRotate(resultNode);
        }
        //平衡维护(RL), 进行右旋再左旋操作
        if(balanceFactor < -1 && getBalanceFactor(resultNode.right) > 0){
            resultNode.right = rightRotate(resultNode.right);
            return leftRotate(resultNode);
        }
        return resultNode;
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
     * 判断该二叉树是否是一颗二分搜索树
     * @return
     */
    private boolean isBinarySearchTree(){
        ArrayList<K> keys = new ArrayList<>();
        //进行中序遍历, 这个时候keys应该是升序排列的
        inOrder(root, keys);
        //检验是否是升序
        for (int i = 1; i < keys.size(); i++) {
            //如果i-1比i还大, 代表有问题
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     * @return
     */
    private boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以node为根的二叉树是否是一颗平衡二叉树
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        //如果平衡因子的绝对值 > 1, 直接返回false
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对以node为根的二分搜索树进行中序遍历
     * 并将结果保存到keys中
     * @param node
     * @param keys
     */
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);

    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t3 = x.right;
        //右旋
        x.right = y;
        y.left = t3;
        //更新高度值,必须先更新y, 因为x的高度值和y是相关的
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t2 = x.left;
        //进行左旋转
        x.left = y;
        y.right = t2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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
