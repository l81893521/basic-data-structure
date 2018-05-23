package will.zhang.unionFind;

/**
 * 第四版的并查集(基于Rank的优化)
 * 合理的合并, 让树的高度更加低
 */
public class DUnionFind implements UF {

    //parnet[i]代表它所指向的节点
    private int[] parent;
    //rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;

    public DUnionFind(int size){
        parent = new int[size];
        rank = new int[size];

        //初始化的时候每一个节点都指向自己, 也就是每一个节点都是一棵独立的树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            //初始化的层数都为1
            rank[i] = 1;
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
        //合并的时候需要考虑到两个集合所属的树的高度, 将rank小的树合并到rank高的树下()
        //而非一味的往同一个方式合并两个集合, 这样子在坏的情况下树的高度会变得非常高, 甚至退化成为一个链表
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
            //rank不用维护, 合并后并不会导致rank[qRoot]增加高度
        }else if(rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
            //依然不用维护rank, , 合并后并不会导致rank[pRoot]增加高度
        }else{  //rank[qRoot] = rank[pRoot]
            //同等高度谁指向谁并不重要
            parent[qRoot] = pRoot;
            //只要相等, 才会导致高度增加, 而且只会增加1
            rank[pRoot] += 1;
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
