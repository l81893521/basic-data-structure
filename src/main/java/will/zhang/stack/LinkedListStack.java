package will.zhang.stack;

/**
 * @Author will
 * @Date 2018/5/8 0008 下午 4:05
 **/
public class LinkedListStack<E> implements Stack<E> {

    //使用链表实现一个盏
    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Stack : top ");
        builder.append(list);

        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
