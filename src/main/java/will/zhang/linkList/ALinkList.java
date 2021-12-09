package will.zhang.linkList;

/**
 * @Author will
 * @Date 2018/5/2 0002 下午 8:31
 * 编写必须的Node节点对象
 **/
public class ALinkList<E> {

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
