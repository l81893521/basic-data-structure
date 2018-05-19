package will.zhang.set;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/**
 * @Author will
 * @Date 2018/5/16 0016 下午 6:32
 **/
public class BinarySearchTree<E extends Comparable<E>> {

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
    public BinarySearchTree(){
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

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树
     * @param node
     */
    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);

    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树
     * @param node
     */
    private void inOrder(Node node){
        if(node == null) return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 后序遍历以node为根的二分搜索树
     * @param node
     */
    private void postOrder(Node node){
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的层序遍历
     */
    private void levelOrder(){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node.e);

            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小值
     * @return
     */
    public E minimum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).e;
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
     * 寻找二分搜索树最大值
     * @return
     */
    public E maximum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     * @param node
     * @return
     */
    public Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除二分搜索树中的最小值, 并返回该值
     * @return
     */
    public E removeMin(){
        E result = minimum();
        root = removeMin(root);
        return result;
    }

    /**
     * 删除二分搜索树中的最小值, 并返回该值
     * @return
     */
    public E removeMax(){
        E result = maximum();
        root = removeMax(root);
        return result;
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
     * 删除以node为根的二分搜索树的最大值的节点
     * 返回删除节点之后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除二分搜索树元素为e的节点
     * @param e
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二分搜索树元素为e的节点
     * 返回删除节点之后新的二分搜索树的根
     * @param node
     * @param e
     */
    private Node remove(Node node, E e){
        if(node == null){
            return null;
        }

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
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

    /*
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * 打印方法，非二叉搜索树主体
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        generateBSTString(root, 0, builder);
        return builder.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }
        return builder.toString();
    }
    /*
    ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
     */


    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            binarySearchTree.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while(!binarySearchTree.isEmpty()){
            nums.add(binarySearchTree.removeMin());
        }

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if(nums.get(i-1) > nums.get(i)){
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("RemoveMin test completed.");

        for (int i = 0; i < n; i++) {
            binarySearchTree.add(random.nextInt(10000));
        }

        nums = new ArrayList<>();
        while(!binarySearchTree.isEmpty()){
            nums.add(binarySearchTree.removeMax());
        }

        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++) {
            if(nums.get(i - 1) < nums.get(i)){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("RemoveMax test completed.");
    }
}
