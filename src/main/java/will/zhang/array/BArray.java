package will.zhang.array;

/**
 * @Author will
 * @Date 2018/4/30 0030 下午 3:19
 * 增加add方法
 **/
public class BArray {

    //存放数组的数组
    private int[] data;

    //data数组中有多少个有效的元素
    private int size;

    public BArray(){
        this(10);
    }

    /**
     * 构造函数, 传入数组容量capacity构造Array
     * @param capacity
     */
    public BArray(int capacity){
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
        //如果index比size还大, 那么意味着元素不是紧密的排列, 所以要抛出异常
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
}
