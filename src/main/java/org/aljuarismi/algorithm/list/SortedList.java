package org.aljuarismi.algorithm.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by javadev on 24/05/14.
 */
public class SortedList {

    private static Logger log = LoggerFactory.getLogger(SortedList.class);

    /**
     * Merge two sorted lists in one single list. Even Java Collections object has merge and sort method,
     * This operation is optimized for sorted list allowing a execution in O(n) time.
     * @param leftList First list to merge.
     * @param rightList Second list to merge
     * @param <E> List elements type. This element implements Comparable interface
     * @return merged list.
     */
    public static <E extends Comparable<E>> List<E> mergeLists(List<E> leftList, List<E> rightList){

        int leftSize  = leftList.size();
        int rightSize = rightList.size();
        int leftIndex = 0, rightIndex = 0;

        if(leftSize  == 0) return new ArrayList<E>(rightList);
        if(rightSize == 0) return new ArrayList<E>(leftList);

        ArrayList<E> mergedArray = new ArrayList<E>();

        int nElements = leftSize + rightSize;

        //Trick
        // Including a last element in each array, I avoid a IndexOutOfBoundsException


        leftList.add(leftList.get(leftSize - 1));
        rightList.add(rightList.get(rightSize - 1));

        for( int k = 0; k < nElements; k++){

            if ((rightIndex >= rightSize  || leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) &&
                leftIndex <  leftSize){
                mergedArray.add(leftList.get(leftIndex));
                leftIndex++;
            }
            else{ // rightArray.get(rightIndex) < leftArray.get(leftIndex)
                mergedArray.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }

        return mergedArray;

    }

    /**
     * Search elements in a list.
     * Even Java Collections object has merge and sort method,
     * This operation is optimized for sorted list allowing a execution in O(log(n)) time.
     * @param list List where elements will be searched
     * @param <E> Element to be searched
     * @return Indexes list with the indexes where all found elements are located in the list
     */
    public static <E extends Comparable<E>> List<Integer> search(E element, List<E> list, Integer leftIndex, Integer rightIndex){

        List<Integer> elementsFound = new ArrayList<Integer>();
        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(list.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            return elementsFound;
        }
        else if(rightIndex - leftIndex == 0){
            if( list.get(leftIndex).compareTo(element) == 0) elementsFound.add(leftIndex);
            return elementsFound;
        }


        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(list.get(midElementIndex)))){
            case -1:
                elementsFound = search(element, list, leftIndex, (midElementIndex -1));
                break;

            case 1:
                elementsFound = search(element, list, (midElementIndex + 1), rightIndex);
                break;

            case 0: //must locate all the elements around the element found that are equal to element
                elementsFound.add(midElementIndex);

                int index = midElementIndex-1;
                while( index >= leftIndex &&
                       element.equals(list.get(index))){
                    elementsFound.add(index);
                    index--;
                }

                index = midElementIndex+1;
                while( index <= rightIndex &&
                       element.equals(list.get(index))){
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

    public static <E extends Comparable<E>> int remove(E element, List<E> list, Integer leftIndex, Integer rightIndex){

        int elementsFound = 0;
        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(list.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            return 0;
        }
        else if(rightIndex - leftIndex == 0){
            if( list.get(leftIndex).compareTo(element) == 0) {
                ListIterator iterator = list.listIterator(leftIndex);
                iterator.next();
                iterator.remove();
            }
            return 1;
        }


        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(list.get(midElementIndex)))){
            case -1:
                elementsFound = remove(element, list, leftIndex, (midElementIndex -1));
                break;

            case 1:
                elementsFound = remove(element, list, (midElementIndex + 1), rightIndex);
                break;

            case 0: //must locate all the elements around the element found that are equal to element

                // get an iterator located in midElementIndex
                ListIterator iterator = list.listIterator(midElementIndex);

                //remove midElementIndex and next elements equals

                while( iterator.hasNext() &&
                       list.get(iterator.nextIndex()).compareTo(element)==0){
                    iterator.next();
                    iterator.remove();
                    elementsFound++;
                }

                while( iterator.hasPrevious() &&
                       list.get(iterator.previousIndex()).compareTo(element)==0){
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
     * @param list List to be cleaned
     * @param <E> List element Type parameter
     * @return Number of duplicated elements found
     */
    public static <E extends Comparable<E>> int deDupe( List<E> list){

        int index = 0;
        int locatedDupe = 0;
        int lastIndex = list.size()-1;

        if(lastIndex == 0) return 0;

        ListIterator<E> iter = list.listIterator(1);

        while (iter.hasNext()){
            E prevElement = list.get(iter.previousIndex());
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
     * @param list Sorted list where the element will be inserted
     * @param leftIndex sublist leftIndex. If null then first list element
     * @param rightIndex sublist rightIndex. If null the last list element
     * @param <E> Type of element
     */
    public static <E extends Comparable<E>> void insert(E element, List<E> list, Integer leftIndex, Integer rightIndex){

        if(leftIndex  == null) leftIndex  = new Integer(0);
        if(rightIndex == null) rightIndex = new Integer(list.size()-1);


        //base case
        if(rightIndex - leftIndex<0){
            list.add(leftIndex, element);
            return;
        }
        else if(rightIndex - leftIndex == 0){
            if( element.compareTo(list.get(leftIndex)) <= 0){
                list.add(leftIndex, element); //Insert to the left
            }
            else{
                list.add(leftIndex, list.get(leftIndex)); //Duplicate element
                list.set(leftIndex + 1, element); //Insert the element to the right
            }
            return;
        }

        int midElementIndex = leftIndex + ((rightIndex-leftIndex)/2); //Integer division

        switch((int)Math.signum(element.compareTo(list.get(midElementIndex)))){
            case -1:
                insert(element, list, leftIndex, (midElementIndex -1));
                break;

            case 1:
                insert(element, list, (midElementIndex + 1), rightIndex);
                break;

            case 0: //Located an element equal to the element to be inserted
                list.add(midElementIndex, element);
                break;
            default:
                log.error("Binary Search comparation failure");
                throw new RuntimeException("Binary Search comparation failure");

        }
    }
}
