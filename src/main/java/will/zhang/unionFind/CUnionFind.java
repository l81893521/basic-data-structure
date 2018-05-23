package will.zhang.unionFind;

/**
 * 第三版的并查集(基于size优化)
 * 和第二版主题结构差别并不大
 * 是基于合并的时候需要考虑到树的高度的一个优化
 */
public class CUnionFind implements UF {

    //parnet[i]代表它所指向的节点
    private int[] parent;
    //sz[i]表示以i为根的集合中元素个数
    private int[] sz;

    public CUnionFind(int size){
        parent = new int[size];
        sz = new int[size];

        //初始化的时候每一个节点都指向自己, 也就是每一个节点都是一棵独立的树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查看p和q是否属于同一个集合
     * 时间复杂度O(h), p是树的高度
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
     * 时间复杂度O(h), p是树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        //主要优化是在合并两个集合的时候
        //合并的时候需要考虑到两个集合所属的树的高度, 将矮的树合并到高的树下()
        //而非一味的往同一个方式合并两个集合, 这样子在坏的情况下树的高度会变得非常高, 甚至退化成为一个链表
        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{  //sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    /**
     * 查询p所对应的集合编号
     * 时间复杂度O(h), p是树的高度
     * @param p
     * @return
     */
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        while(p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
