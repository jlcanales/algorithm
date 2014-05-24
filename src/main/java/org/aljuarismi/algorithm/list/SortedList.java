package org.aljuarismi.algorithm.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javadev on 24/05/14.
 */
public class SortedList {

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


        ArrayList<E> mergedArray = new ArrayList<E>();

        int nElements = leftSize + rightSize;

        //Trick
        // Including a last element in each array = 'infinitum', I avoid a IndexOutOfBoundsException
        // When and array is completely processed and I don't need to include an
        // if statement in the indexes in each iteration

        leftList.add(Integer.MAX_VALUE);
        rightList.add(Integer.MAX_VALUE);

        for( int k = 0; k < nElements; k++){

            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0){
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
    public static <E extends Comparable<E>> List<Integer> search(E element, List<E> list){

        return null;
    }

    /**
     * This method clean duplicate elements in the sorted list.
     * @param list List to be cleaned
     * @param <E> List element Type parameter
     * @return Number of duplicated elements found
     */
    public static <E extends Comparable<E>> int deDupe( List<E> list){

        return 0;
    }

}
