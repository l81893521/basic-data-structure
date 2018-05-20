package will.zhang.set;

/**
 * 基于链表实现的集合
 *
 * 时间复杂度分析
 * 添加
 * add(e)   O(n)
 * 查
 * contains(e)  O(n)
 * 删
 * remove(e)    O(n)
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList();
    }

    @Override
    public void add(E e) {
        //避免添加重复元素
        if(!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
