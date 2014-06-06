package org.aljuarismi.structures.heap;

/**
 * Created by mldev on 06/06/14.
 */
public interface HeapNotificable {

    /**
     * Allow to a heap to notify what is the element position in the
     * heap when that position is changed.
     * @param heapPosition
     */
    public void setHeapPosition(int heapPosition);

    /**
     * Allow to get the heap position notified by the heap.
     * @return heap Position
     */
    public int getHeapPosition();
}
