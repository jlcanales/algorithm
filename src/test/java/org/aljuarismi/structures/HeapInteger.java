package org.aljuarismi.structures;

import org.aljuarismi.structures.heap.HeapNotificable;

/**
 * Created by mldev on 06/06/14.
 */
public class HeapInteger implements HeapNotificable,Comparable<HeapInteger>{

    private int heapPosition;

    private Integer value;



    public HeapInteger(int value) {
        this.value = value;
    }

    @Override
    public void setHeapPosition(int heapPosition) {
        this.heapPosition = heapPosition;

    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    @Override
    public int getHeapPosition() {
        return heapPosition;
    }

    @Override
    public int compareTo(HeapInteger o) {
        return value.compareTo(o.getValue());
    }
}
