package org.aljuarismi.structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by mldev on 06/06/14.
 */
public class AbstractHeap<E extends Comparable<? super E>> implements Collection<E> {

    private ArrayList<E> Heap;   // Pointer to the heap array

    /** Constructor supporting preloading of heap contents */
    public AbstractHeap(E[] h, int num, int max){
        Heap = new ArrayList<E>();

        for(int i = 0; i < num; i++){
            insert(h[i]);
        }
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
    private boolean isLeaf(int pos)
    { return (pos >= Heap.size()/2) && (pos < Heap.size()); }

    /**
     * @return Position for left child of pos
     */
    private int leftchild(int pos) {
        assert pos < Heap.size()/2 : "Position has no left child";
        return 2*pos + 1;
    }

    /**
     * @return Position for right child of pos
     */
    private int rightchild(int pos) {
        assert pos < (Heap.size()-1)/2 : "Position has no right child";
        return 2*pos + 2;
    }

    /**
     * @return Position for parent
     */
    private int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
    }

    /** Insert val into heap */
    public void insert(E val) {
        int curr = Heap.size();
        Heap.add(val);             // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        bubbleup(curr);
    }
    /** Heapify contents of Heap */
    public void buildheap()
    { for (int i=Heap.size()/2-1; i>=0; i--) siftdown(i); }

    /**
     * Put element in its correct place
     */
    private void siftdown(int pos) {
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j<(Heap.size()-1)) && (Heap.get(j).compareTo(Heap.get(j+1)) > 0))
                j++; // j is now index of child with l∫ower value
            if (Heap.get(pos).compareTo(Heap.get(j)) >= 0) return;
            DSutil.swap(Heap, pos, j);
            pos = j;  // Move down
        }
    }

    private void bubbleup(int pos){
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        while ((pos > 0) &&
                (Heap.get(pos).compareTo(Heap.get(parent(pos))) < 0)) {
            DSutil.swap(Heap, pos, parent(pos));
            pos = parent(pos);
        }
    }

    /** Remove and return maximum value */
    public E removeTop() {
        assert Heap.size() > 0 : "Removing from empty heap";
        DSutil.swap(Heap, 0, Heap.size()-1); // Swap maximum with last value
        //remove the last element
        E topElement = remove(Heap.size()-1);

        if (Heap.size() != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return topElement;
    }

    /** Remove and return element at specified position */
    public E remove(int pos) {
        assert (pos >= 0) && (pos < Heap.size()) : "Illegal heap position";
        E element;
        if (pos == (Heap.size() - 1)) {
            element = remove(Heap.size() - 1);// Last element, no work to be done
        }
        else {
            DSutil.swap(Heap, pos, Heap.size()-1); // Swap with last value
            element = remove(Heap.size()-1);
            // If we just swapped in a big value, push it up
            //bubbleup(pos);
            siftdown(pos); // If it is little, push down
        }
        return element;
    }


}
