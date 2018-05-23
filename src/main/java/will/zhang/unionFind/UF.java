package will.zhang.unionFind;

/**
 * 并查集的基本接口
 */
public interface UF {

    int getSize();

    /**
     * p和q是否相连
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 合并p和q
     * @param p
     * @param q
     * @return
     */
    void unionElements(int p, int q);
}
