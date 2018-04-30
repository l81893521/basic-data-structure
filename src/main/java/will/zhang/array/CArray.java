package will.zhang.array;

import java.util.Arrays;

/**
 * @Author will
 * @Date 2018/4/30 0030 下午 3:19
 * 对数组进行二次封装
 **/
public class CArray {

    //存放数组的数组
    private int[] data;

    //data数组中有多少个有效的元素
    private int size;

    /**
     * 构造函数, 传入数组容量capacity构造Array
     * @param capacity
     */
    public CArray(int capacity){
        data = new int[capacity];
        size = 0;
    }

    public CArray(){
        this(10);
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

}
