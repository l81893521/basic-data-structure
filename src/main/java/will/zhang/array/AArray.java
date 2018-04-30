package will.zhang.array;

/**
 * @Author will
 * @Date 2018/4/30 0030 下午 3:19
 * 对数组进行二次封装
 **/
public class AArray {

    //存放数组的数组
    private int[] data;

    //data数组中有多少个有效的元素
    private int size;

    /**
     * 构造函数, 传入数组容量capacity构造Array
     * @param capacity
     */
    public AArray(int capacity){
        data = new int[capacity];
        size = 0;
    }

    public AArray(){
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
}
