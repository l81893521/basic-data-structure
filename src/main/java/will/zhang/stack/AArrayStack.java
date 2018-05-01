package will.zhang.stack;

/**
 * @Author will
 * @Date 2018/5/1 0001 下午 3:45
 * 盏的复杂度分析
 * void push(E)         O(1) 均摊
 * E pop()              O(1) 均摊
 * E peek()             O(1)
 * int getSize()        O(1)
 * boolean isEmpty()    O(1)
 **/
public class AArrayStack<E> implements Stack<E> {

    //使用上一章实现的动态数组来存放数据
    private Array<E> array;

    public AArrayStack(int capacity){
        array = new Array(capacity);
    }

    public AArrayStack(){
        array = new Array();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看盏的容量
     * @return
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if(i != array.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        AArrayStack<Integer> stack = new AArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

    }
}
