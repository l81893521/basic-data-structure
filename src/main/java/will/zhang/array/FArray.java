package will.zhang.array;

/**
 * @Author will
 * @Date 2018/4/30 0030 下午 3:19
 * 增加 resize
 **/
public class FArray<T> {

    //存放数组的数组
    private T[] data;

    //data数组中有多少个有效的元素
    private int size;

    public FArray(){
        this(10);
    }

    /**
     * 构造函数, 传入数组容量capacity构造Array
     * @param capacity
     */
    public FArray(int capacity){
        data = (T[]) new Object[capacity];
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
    public void addLast(T e){
        add(size, e);
    }

    /**
     * 向数组的开头添加一个元素
     * @param e
     */
    public void addFirst(T e){
        add(0, e);
    }

    /**
     * 在数组index位置中插入一个元素
     * @param index
     * @param e
     */
    public void add(int index, T e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        if(size == data.length){
            resize(2 * data.length);
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
    public T get(int index){
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
    public void set(int index, T e){
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
    public boolean contains(T e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
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
    public int find(T e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
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
    public T remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed, index is illegal");
        }
        T result = data[index];
        //arr[index+1...size-1]的元素都往前移动一位, 覆盖被删除的元素
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //只是为了去除引用, 满足垃圾回收条件, 释放内存
        //data[size]存在值, 也不会影响数组的正常使用, 添加新值也会自动覆盖
        //避免对象游离(游离对象 loitering objects)
        //loitering objects != memory leak
        data[size] = null;

        if(size == data.length / 2){
            resize(data.length / 2);
        }
        return result;
    }

    /**
     * 删除数组中的第一个元素
     * @return
     */
    public T removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组中的最后一个元素
     * @return
     */
    public T removeLast(){
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * 为什么这个方法不返回一个int值呢?
     * 因为用户调用的时候就已经知道自己要删除的元素e了
     * @param e
     */
    public void removeElement(T e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    /**
     * 数组扩容, 每次扩容一倍
     * @param newCapacity
     */
    private void resize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public static void main(String[] args) {
        FArray<Integer> arr = new FArray();
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
