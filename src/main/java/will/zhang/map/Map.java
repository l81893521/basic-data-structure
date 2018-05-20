package will.zhang.map;

/**
 * 映射(Map)的基本接口定义
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {

    /**
     * 添加key和value到Map中
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 根据key删除对应的key跟value
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 查看该key是否存在于映射中
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * 根据key从映射中查找value
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 根据key更新映射中对应的value值
     * @param key
     * @param newValue
     */
    void set(K key, V newValue);

    /**
     * 获取映射的大小
     * @return
     */
    int getSize();

    /**
     * 映射是否为空
     * @return
     */
    boolean isEmpty();
}
