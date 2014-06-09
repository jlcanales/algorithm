package org.aljuarismi.algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.aljuarismi.algorithm.sort.pivot.PivotRandom;
import org.apache.spark.api.java.function.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort<T extends Comparable<? super T>> extends Function3<List<T>, Integer, Integer, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956990140739613816L;

	private static Logger log = LoggerFactory.getLogger(QuickSort.class);
	
	
	Function2<Integer, Integer, Integer> pivotSelector;
		
	
	public QuickSort() {
		super();
		pivotSelector = new PivotRandom();
	}

	
	public QuickSort(Function2<Integer, Integer, Integer> aiPivotSelector) {
		super();
		pivotSelector = aiPivotSelector;
	}	
	
	/**
	 * Swap two elements in the list.
	 * @param indexElementA First Element index
	 * @param indexElementB Second Element index
	 */
	public void swapElements(List<T> Array, Integer indexElementA, Integer indexElementB){
	
		if( indexElementA == indexElementB) return;
		
		T auxElement = Array.get(indexElementA);
		
		Array.set(indexElementA, Array.get(indexElementB));
		Array.set(indexElementB, auxElement);
	}
	
	
	
	@Override
	public Long call(List<T> Array, Integer leftIndex, Integer rightIndex)
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
				
				
		int  pivotIndex    = pivotSelector.call( leftIndex, rightIndex);
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
