package will.zhang.set;

/**
 * 基于二分搜索树实现的集合
 *
 * 时间复杂度分析(h=树的高度)
 * 增
 * add(e)   O(logn)
 * 查
 * contains(e)  O(logn)
 * 删
 * remove(e)    O(logn)
 * @param <E>
 */
public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> binarySearchTree;

    public BinarySearchTreeSet(){
        binarySearchTree = new BinarySearchTree<>();
    }
    @Override
    public void add(E e) {
        //我们实现的二分搜索树并没有对重复元素进行处理
        //所以符合set的要求
        binarySearchTree.add(e);
    }

    @Override
    public void remove(E e) {
        binarySearchTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return binarySearchTree.contains(e);
    }

    @Override
    public int getSize() {
        return binarySearchTree.size();
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }
}
