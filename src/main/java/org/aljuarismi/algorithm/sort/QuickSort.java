package org.aljuarismi.algorithm.sort;

import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.spark.api.java.function.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort<T extends Comparable<T>> extends Function3<ArrayList<T>, Integer, Integer, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956990140739613816L;

	private static Logger log = LoggerFactory.getLogger(QuickSort.class);
	
	/**
	 * Selects a pivot Element for quickSort sorting implementation
	 * The pivot element will be located between leftIndex and RightIndex
	 * both inclusive.
	 * @param leftIndex
	 * @param rightIndex
	 * @return Pivot Element Index
	 * @throws IndexOutOfBoundsException if the index is out of range (( leftIndex < 0 || leftIndex >= size()) ||
		   ( rightIndex < 0 || rightIndex >= size()) ||
		   rightIndex < leftIndex) 
	 */
	public  int getPivot(int leftIndex, int rightIndex) throws IndexOutOfBoundsException{
		return rightIndex;
		
	}
	
	/**
	 * Swap two elements in the list.
	 * @param indexElementA First Element index
	 * @param indexElementB Second Element index
	 */
	public void swapElements(ArrayList<T> Array, Integer indexElementA, Integer indexElementB){
	
		if( indexElementA == indexElementB) return;
		
		T auxElement = Array.get(indexElementA);
		
		Array.set(indexElementA, Array.get(indexElementB));
		Array.set(indexElementB, auxElement);
	}
	
	
	
	@Override
	public Long call(ArrayList<T> Array, Integer leftIndex, Integer rightIndex)
			throws Exception {
		
		if(leftIndex  == null) leftIndex  = new Integer(0);
		if(rightIndex == null) rightIndex = new Integer(Array.size() - 1);
		
		if(( leftIndex < 0) || ( rightIndex >= Array.size())){
			throw new IndexOutOfBoundsException();
		}
				
		// base case (empty array case or one element array case)
		// one element is always sorted
		if(( rightIndex - leftIndex) <= 0){
			return new Long(0);
		}
				
				
		int  pivotIndex    = getPivot( leftIndex, rightIndex);
		T	 pivotElement  = Array.get(pivotIndex);
		Long compareNumber = new Long(rightIndex - leftIndex);

		log.debug("Selected pivot index: {}; Value: {};", pivotIndex, pivotElement);
				
		// Swap pivot to the first element
		swapElements(Array, leftIndex, pivotIndex);
				
		// Initialize iterators
		ListIterator<T> iteratorI = Array.listIterator(leftIndex + 1);
		ListIterator<T> iteratorJ = Array.listIterator(leftIndex + 1);
				
				
		while( iteratorJ.nextIndex() <= rightIndex){
			T jElement = iteratorJ.next();
			
			if( jElement.compareTo(pivotElement) < 0 ){
				swapElements(Array, iteratorI.nextIndex(), iteratorJ.previousIndex());
				iteratorI.next(); // iteratorI++
			}			
		}
								
		// Restore the pivot position
		swapElements(Array, leftIndex, iteratorI.previousIndex());
				
		// Iterate over both sides of pivot Element
		// Pivot element is sited in iteratorI.previousIndex()
		Long leftSideCompCounter  = call(Array, leftIndex, iteratorI.previousIndex()-1);
		Long rightSideCompCounter = call(Array, iteratorI.nextIndex(), rightIndex);
				
				
		return compareNumber + leftSideCompCounter + rightSideCompCounter;
	}

}
