package org.aljuarismi.structures;

import org.aljuarismi.algorithm.sort.QuickSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by mldev on 09/06/14.
 */
public class SortList<E extends Comparable<? super E>> implements List<E> {

    private static Logger log = LoggerFactory.getLogger(SortList.class);

    /**
     * Internal list to store sorted elements
     */
    private ArrayList<E> elementList;

    /**
     * Quick Sort function for list total ordering
     */
    private QuickSort<E> sort;

    /**
     * Default Constructor
     */
    public SortList() {
        super();
        elementList = new ArrayList<E>();
        sort = new QuickSort<E>();
    }



    @Override
    public int size() {
        return elementList.size();
    }

    @Override
    public boolean isEmpty() {
        return elementList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        E object = (E) o;
        List<Integer> output = search(object, null, null);
        return output.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return elementList.iterator();
    }

    @Override
    public Object[] toArray() {
        return elementList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elementList.toArray(a);
    }

    @Override
    public boolean add(E e) {
        insert(e, null, null);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        E object = (E) o;
        int removed = remove(object, null, null);
        return removed != 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elementList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
// TODO: utilizar merge list.
        Iterator iter = c.iterator();
        while(iter.hasNext()){
            E element = (E) iter.next();
            insert(element, null, null);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        Iterator iter = c.iterator();
        int originalSize = elementList.size();
        while(iter.hasNext()){
            E element = (E) iter.next();
            remove(element, null, null);
        }
        return originalSize != elementList.size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        elementList.clear();
    }

    @Override
    public E get(int index) {
        return elementList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        insert(element, null, null);
    }

    @Override
    public E remove(int index) {
        return elementList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        E object = (E) o;
        List<Integer> output = search(object, null, null);
        if(output.isEmpty()) return -1;
        return output.get(0);
    }

    @Override
    public int lastIndexOf(Object o) {
        E object = (E) o;
        List<Integer> output = search(object, null, null);
        if(output.isEmpty()) return -1;
        return output.get(output.size()-1);
    }

    @Override
    public ListIterator<E> listIterator() {
        return elementList.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return elementList.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return elementList.subList(fromIndex, toIndex);
    }


    /**
     * Merge two sorted lists in one single list. Even Java Collections object has merge and sort method,
     * This operation is optimized for sorted list allowing a execution in O(n) time.
     * @param rightList List to merge
     */
    private void  mergeLists(SortList<E> rightList){

        int leftSize  = elementList.size();
        int rightSize = rightList.size();
        int leftIndex = 0, rightIndex = 0;

        if(leftSize  == 0) {
            this.elementList = new ArrayList<E>(rightList);
            return;
        }
        if(rightSize == 0){
            return;
        }

        ArrayList<E> mergedArray = new ArrayList<E>();

        int nElements = leftSize + rightSize;

        elementList.add(elementList.get(leftSize - 1));
        rightList.add(rightList.get(rightSize - 1));

        for( int k = 0; k < nElements; k++){

            if ((rightIndex >= rightSize  || elementList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) &&
                    leftIndex <  leftSize){
                mergedArray.add(elementList.get(leftIndex));
                leftIndex++;
            }
            else{ // rightArray.get(rightIndex) < leftArray.get(leftIndex)
                mergedArray.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }
        this.elementList = mergedArray;
    }


    /**
     * Search elements in the sorted list.
     * Even Java Collections object has merge and sort method,
     * This operation is optimized for sorted list allowing a execution in O(log(n)) time.
     * @return Indexes list with the indexes where all found elements are located in the list
     */
    private List<Integer> search(E element, Integer leftIndex, Integer rightIndex){

        List<Integer> elementsFound = new ArrayList<Integer>();
        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(elementList.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            return elementsFound;
        }
        else if(rightIndex - leftIndex == 0){
            if( elementList.get(leftIndex).compareTo(element) == 0) elementsFound.add(leftIndex);
            return elementsFound;
        }


        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(elementList.get(midElementIndex)))){
            case -1:
                elementsFound = search(element, leftIndex, (midElementIndex -1));
                break;

            case 1:
                elementsFound = search(element, (midElementIndex + 1), rightIndex);
                break;

            case 0: //must locate all the elements around the element found that are equal to element
                elementsFound.add(midElementIndex);

                int index = midElementIndex-1;
                while( index >= leftIndex &&
                        element.equals(elementList.get(index))){
                    elementsFound.add(index);
                    index--;
                }

                index = midElementIndex+1;
                while( index <= rightIndex &&
                        element.equals(elementList.get(index))){
                    elementsFound.add(index);
                    index++;
                }

                break;
            default:
                log.error("Binary Search comparation failure");
                throw new RuntimeException("Binary Search comparation failure");

        }

        return elementsFound;
    }


    /**
     * Removes all the occurrences of one element in a sublist included in a shorted list. This algorithm is based on binary search
     * so it performance is =(log(n))
     * @param element Element to be removed
     * @param leftIndex Sublist left element. 0 if null.
     * @param rightIndex Sublist right element. Last element if null.
     * @return Number of removed elements
     */
    public int remove(E element, Integer leftIndex, Integer rightIndex){

        int elementsFound = 0;
        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(elementList.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            return 0;
        }
        else if(rightIndex - leftIndex == 0){
            if( elementList.get(leftIndex).compareTo(element) == 0) {
                ListIterator iterator = elementList.listIterator(leftIndex);
                iterator.next();
                iterator.remove();
            }
            return 1;
        }


        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(elementList.get(midElementIndex)))){
            case -1:
                elementsFound = remove(element, leftIndex, (midElementIndex -1));
                break;

            case 1:
                elementsFound = remove(element, (midElementIndex + 1), rightIndex);
                break;

            case 0: //must locate all the elements around the element found that are equal to element

                // get an iterator located in midElementIndex
                ListIterator iterator = elementList.listIterator(midElementIndex);

                //remove midElementIndex and next elements equals

                while( iterator.hasNext() &&
                        elementList.get(iterator.nextIndex()).compareTo(element)==0){
                    iterator.next();
                    iterator.remove();
                    elementsFound++;
                }

                while( iterator.hasPrevious() &&
                        elementList.get(iterator.previousIndex()).compareTo(element)==0){
                    iterator.previous();
                    iterator.remove();
                    elementsFound++;
                }

                break;
            default:
                log.error("Binary Search comparation failure");
                throw new RuntimeException("Binary Search comparation failure");
        }

        return elementsFound;
    }

    /**
     * This method clean duplicate elements in the sorted list.
     * @return Number of duplicated elements found
     */
    public int deDupe(){

        int index = 0;
        int locatedDupe = 0;
        int lastIndex = elementList.size()-1;

        if(lastIndex == 0) return 0;

        ListIterator<E> iter = elementList.listIterator(1);

        while (iter.hasNext()){
            E prevElement = elementList.get(iter.previousIndex());
            E element = iter.next();

            if(element.compareTo(prevElement) == 0){
                iter.remove();
                locatedDupe++;
            }
        }
        return locatedDupe;
    }

    /**
     * Inserts an element in a sorted list in the right position
     * @param element Element to be inserted
     * @param leftIndex sublist leftIndex. If null then first list element
     * @param rightIndex sublist rightIndex. If null the last list element
     */
    private void insert(E element, Integer leftIndex, Integer rightIndex){

        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(elementList.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            elementList.add(leftIndex, element);
            return;
        }
        else if(rightIndex - leftIndex == 0){
            if( element.compareTo(elementList.get(leftIndex)) <= 0){
                elementList.add(leftIndex, element); //Insert to the left
            }
            else{
                elementList.add(leftIndex, elementList.get(leftIndex)); //Duplicate element
                elementList.set(leftIndex + 1, element); //Insert the element to the right
            }
            return;
        }

        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(elementList.get(midElementIndex)))){
            case -1:
                insert(element, leftIndex, (midElementIndex -1));
                break;

            case 1:
                insert(element, (midElementIndex + 1), rightIndex);
                break;

            case 0: //Located an element equal to the element to be inserted
                elementList.add(midElementIndex, element);
                break;
            default:
                log.error("Binary Search comparation failure");
                throw new RuntimeException("Binary Search comparation failure");
        }
    }
}
