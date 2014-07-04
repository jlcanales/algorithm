package org.aljuarismi.structures;


/**
 * Created by mldev on 06/06/14.
 */
public class MaxHeap<E extends Comparable<? super E>> extends AbstractHeap<E> {

    /**
     * Constructor supporting preloading of heap contents
     *
     * @param h
     * @param num
     */
    public MaxHeap(E[] h, int num) {
        super(h, num);
    }


    /**
     * Default Constructor  of heap
     */
    public MaxHeap() {
        super();
    }

    /**
     * Put element in its correct place
     */
    @Override
    protected void siftdown(int pos) {
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j<(Heap.size()-1)) && (Heap.get(j).compareTo(Heap.get(j+1)) < 0))
                j++; // j is now index of child with greater value
            if (Heap.get(pos).compareTo(Heap.get(j)) >= 0) return;
            DSutil.swap(Heap, pos, j);
            pos = j;  // Move down
        }
    }

    @Override
    protected void bubbleup(int pos){
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        while ((pos > 0) &&
                (Heap.get(pos).compareTo(Heap.get(parent(pos))) > 0)) {
            DSutil.swap(Heap, pos, parent(pos));
            pos = parent(pos);
        }
    }

}
