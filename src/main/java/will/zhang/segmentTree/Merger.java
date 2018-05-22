package will.zhang.segmentTree;

public interface Merger<E> {

    /**
     * 返回元素A和B计算出来之后的结果
     * @param a
     * @param b
     * @return
     */
    E merge(E a, E b);
}
