package org.aljuarismi.algorithm.sort;

import java.util.ArrayList;

import org.aljuarismi.algorithm.sort.pivot.PivotRandom;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Assert;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class QuickSortPivotRandomTest
    extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(QuickSortPivotRandomTest.class);


	@Test
    public void quickSortCase4Test() throws Exception
    {

		log.info("=================================================");
		log.info("...QuickSort. Big Array unsorted");
		log.info("=================================================");		
		
		
		//Given
		ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("quicksort/QuickSort.txt");
		ArrayList<Integer> sortArray = new ArrayList<Integer>();
		sortArray.addAll(inputArray);

		QuickSort<Integer> sort = new QuickSort<Integer>(new PivotRandom());
		
		log.info("Input Array Size: {};",sortArray.size());

		//When
		long result = sort.call(sortArray, null, null);

		log.info("Sorted Array: {}", sortArray);
		log.info("Number of Comparations: {}",result);
		
		//Then

		for( int i = 1; i < sortArray.size(); i++){
			Assert.assertEquals(i, sortArray.get(i - 1).intValue());			
		}

    }

}
