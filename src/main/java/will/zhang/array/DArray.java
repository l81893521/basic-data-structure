package will.zhang.array;

/**
 * @Author will
 * @Date 2018/4/30 0030 下午 3:19
 * 增加contains find 和 remove
 **/
public class DArray {

    //存放数组的数组
    private int[] data;

    //data数组中有多少个有效的元素
    private int size;

    public DArray(){
        this(10);
    }

    /**
     * 构造函数, 传入数组容量capacity构造Array
     * @param capacity
     */
    public DArray(int capacity){
        data = new int[capacity];
        size = 0;
    }

    /**
     * 获取数组中元素的个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 获取数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.length == 0;
    }

    /**
     * 向数组末尾添加一个元素
     * @param e
     */
    public void addLast(int e){
        add(size, e);
    }

    /**
     * 向数组的开头添加一个元素
     * @param e
     */
    public void addFirst(int e){
        add(0, e);
    }

    /**
     * 在数组index位置中插入一个元素
     * @param index
     * @param e
     */
    public void add(int index, int e){
        if(size == data.length){
            throw new IllegalArgumentException("Add failed. Array is full");
        }
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }
        //把arr[index...size-1]的元素都往后移动一个位置
        for(int i = size - 1 ; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;

    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        //以下方法等同于Arrays.toString(arr);
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if(i != size - 1){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public int get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     * @param index
     * @param e
     */
    public void set(int index, int e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(int e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引位置, 如果找不到则返回-1
     * @param e
     * @return
     */
    public int find(int e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除索引为index的元素, 返回被删除的元素
     * @param index
     * @return
     */
    public int remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed, index is illegal");
        }
        int result = data[index];
        //arr[index+1...size-1]的元素都往前移动一位, 覆盖被删除的元素
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return result;
    }

    /**
     * 删除数组中的第一个元素
     * @return
     */
    public int removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组中的最后一个元素
     * @return
     */
    public int removeLast(){
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * 为什么这个方法不返回一个int值呢?
     * 因为用户调用的时候就已经知道自己要删除的元素e了
     * @param e
     */
    public void removeElement(int e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    public static void main(String[] args) {
        DArray arr = new DArray(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

    }

}
