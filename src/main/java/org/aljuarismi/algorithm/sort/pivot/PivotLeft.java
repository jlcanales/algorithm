package org.aljuarismi.algorithm.sort.pivot;

import org.apache.spark.api.java.function.Function2;


/**
 * Function Class to implement Selection of  an Array pivot Element.
 * This is particularly useful for quickSort sorting implementation
 * The pivot element will be located between leftIndex and RightIndex
 * both inclusive.
 * 
 */
public class PivotLeft extends Function2<Integer, Integer, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012397570577439446L;

	/**
	 * Selects leftIndex as pivot Element for quickSort sorting implementation
	 * The pivot element will be located between leftIndex and RightIndex
	 * both inclusive.
	 * @param leftIndex
	 * @param rightIndex
	 * @return Pivot Element Index
	 * @throws IndexOutOfBoundsException if the index is out of range (( leftIndex < 0 || leftIndex >= size()) ||
		   ( rightIndex < 0 || rightIndex >= size()) ||
		   rightIndex < leftIndex) 
	 */
	@Override
	public Integer call(Integer leftIndex, Integer rightIndex) throws Exception {
		return leftIndex;
	}

}
