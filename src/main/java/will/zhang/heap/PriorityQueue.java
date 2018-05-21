package will.zhang.heap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private DMaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new DMaxHeap();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
