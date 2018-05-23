package will.zhang.unionFind;

/**
 * 第一版并查集(Quick Find)
 * 之所以叫做QuickFind, 是因为isConnected(p,q)的时间复杂度是O(1)级别, 而unionElements(p,q)的时间复杂度是O(n)级别的
 */
public class AUnionFind implements UF{

    private int[] id;

    public AUnionFind(int size){
        id = new int[size];
        //初始化, 每一个元素都所属不同的集合, 没有一个元素是相连的
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }
    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查看元素p和元素q是否所属同一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if(pId == qId) return;

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId){
                id[i] = qId;
            }
        }
    }

    /**
     * 查找元素p所对应集合的编号
     * @param p
     * @return
     */
    private int find(int p){
        if(p < 0 || p > id.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }
}
