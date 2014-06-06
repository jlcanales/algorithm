package org.aljuarismi.structures;

import org.aljuarismi.structures.heap.HeapNotificable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by mldev on 06/06/14.
 */
public abstract class AbstractHeap<E extends Comparable<? super E>> implements Collection<E> {

    protected ArrayList<E> Heap;   // Pointer to the heap array

    /** Constructor supporting preloading of heap contents */
    public AbstractHeap(E[] h, int num){
        Heap = new ArrayList<E>();

        for(int i = 0; i < num; i++){
            insert(h[i]);
        }
    }

    /** Constructor supporting preloading of heap contents */
    public AbstractHeap(){
        Heap = new ArrayList<E>();
    }


    @Override
    public int size() {
        return Heap.size();
    }

    @Override
    public boolean isEmpty() {
        return Heap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return Heap.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return Heap.iterator();
    }

    @Override
    public Object[] toArray() {
        return Heap.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return Heap.toArray(a);
    }

    @Override
    public boolean add(E e) {
        insert(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int pos = Heap.indexOf(o);
        if(pos >= 0){
            remove(pos);
            return true;
        }
        else return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Heap.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E element: c){
            insert(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for(Object element: c){
            if(remove(element)){
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        Heap.clear();
    }




    /**
     * @return True if pos a leaf position, false otherwise
     */
    protected boolean isLeaf(int pos)
    { return (pos >= Heap.size()/2) && (pos < Heap.size()); }

    /**
     * @return Position for left child of pos
     */
    protected int leftchild(int pos) {
        assert pos < Heap.size()/2 : "Position has no left child";
        return 2*pos + 1;
    }

    /**
     * @return Position for right child of pos
     */
    protected int rightchild(int pos) {
        assert pos < (Heap.size()-1)/2 : "Position has no right child";
        return 2*pos + 2;
    }

    /**
     * @return Position for parent
     */
    protected int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
    }

    /** Insert val into heap */
    public void insert(E val) {
        int curr = Heap.size();
        Heap.add(val);             // Start at end of heap

        for(Class c: val.getClass().getInterfaces()){
            if(c.equals(HeapNotificable.class)) {
                ((HeapNotificable) val).setHeapPosition(Heap.size() - 1);
            }
        }

        // Now sift up until curr's parent's key > curr's key
        bubbleup(curr);
    }
    /** Heapify contents of Heap */
    public void buildheap()
    { for (int i=Heap.size()/2-1; i>=0; i--) siftdown(i); }

    /**
     * Put element in its correct place
     */
    protected abstract void siftdown(int pos);

    protected abstract void bubbleup(int pos);

    /** Remove and return maximum value */
    public E removeTop() {
        assert Heap.size() > 0 : "Removing from empty heap";
        DSutil.swap(Heap, 0, Heap.size()-1); // Swap maximum with last value
        //remove the last element
        E topElement = Heap.remove(Heap.size()-1);

        if (Heap.size() != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return topElement;
    }

    /** Remove and return element at specified position */
    public E remove(int pos) {
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        E element;
        if (pos == (Heap.size() - 1)) {
            element = Heap.remove(Heap.size() - 1);// Last element, no work to be done
        }
        else {
            DSutil.swap(Heap, pos, Heap.size()-1); // Swap with last value
            element = Heap.remove(Heap.size()-1);
            // If we just swapped in a big value, push it up
            //bubbleup(pos);
            siftdown(pos); // If it is little, push down
        }
        return element;
    }


}
