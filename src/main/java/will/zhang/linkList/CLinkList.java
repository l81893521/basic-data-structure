package will.zhang.linkList;

/**
 * @Author will
 * @Date 2018/5/2 0002 下午 8:31
 **/
public class CLinkList<E> {

    //为了方便让整个链表的所有节点都拥有上一个节点, 所以设立虚拟头节点
    //链表的虚拟头节点, 不存放任何元素, 第一个节点是hummyHead.next
    private Node dummyHead;
    //链表的元素个数
    private int size;

    public CLinkList(){
        //对于一个空的链表来说, 是存在一个对用户不可见的虚拟头节点
        dummyHead = new Node(null, null);
        size = 0;
    }

    /**
     * 获取链表的元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在链表的index位置添加元素
     * 注意 : 当你选择链表的时候, 其实就已经打算不使用索引了
     * 因为在链表中是不存在索引的
     * 所以次方法当作练习即可
     * 日常工作中, 需要用到索引必须使用ArrayList
     * @param index
     * @param e
     */
    public void add(int index, E e){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index");
        }

        //prev表示插入位置index的前一个元素, 找到它就找到了插入位置
        //这也是为什么要使用索引就不要用链表的原因
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //Node node = new Node(e);
        //node.next = prev.next;
        //prev.next = node;
        //上面三句简写成一句
        prev.next = new Node(e, prev.next);

        size++;
    }

    /**
     * 在链表头添加元素
     * @param e
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 在链表的末尾添加元素
     * @param e
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 节点
     * 链表的内部类
     */
    private class Node {
        //数据存放在节点中
        public E e;
        //下一个节点
        public Node next;

        public Node(){
            this(null, null);
        }

        public Node(E e){
            this(e, null);
        }

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
