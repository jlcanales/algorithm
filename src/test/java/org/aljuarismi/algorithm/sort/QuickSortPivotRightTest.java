package org.aljuarismi.algorithm.sort;

import java.util.ArrayList;

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
public class QuickSortPivotRightTest
    extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(QuickSortPivotRightTest.class);

    /**
     * Rigourous Test :-)
     * @throws Exception 
     */
	@Test
    public void quickSortCase1Test() throws Exception
    {

		log.info("=================================================");
		log.info("...QuickSort. Small Array totally sorted");
		log.info("=================================================");		
		
		
		//Given
		ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("quicksort/quickSortTest1.txt");
		ArrayList<Integer> sortArray = new ArrayList<Integer>();
		sortArray.addAll(inputArray);

		QuickSort<Integer> sort = new QuickSort<Integer>();
		
		log.info("Input Array Size: {};",sortArray.size());

		//When
		long result = sort.call(sortArray, null, null);

		log.info("Sorted Array: {}", sortArray);
		log.info("Number of Comparations: {}",result);
		
		//Then
		Assert.assertEquals(inputArray, sortArray); //still totally ordered
		Assert.assertEquals(45, result); //n(n-1)/2  for n = 10

    }
	
	@Test
    public void quickSortCase2Test() throws Exception
    {

		log.info("=================================================");
		log.info("...QuickSort. Small Array totally reverse sorted");
		log.info("=================================================");		
		
		
		//Given
		ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("quicksort/quickSortTest2.txt");
		ArrayList<Integer> resultArray = FileUtils.readFileAsIntArray("quicksort/quickSortTest1.txt");
		ArrayList<Integer> sortArray = new ArrayList<Integer>();
		sortArray.addAll(inputArray);

		QuickSort<Integer> sort = new QuickSort<Integer>();
		
		log.info("Input Array Size: {};",sortArray.size());

		//When
		long result = sort.call(sortArray, null, null);

		log.info("Sorted Array: {}", sortArray);
		log.info("Number of Comparations: {}",result);
		
		//Then
		Assert.assertEquals(resultArray, sortArray); //still totally ordered
		Assert.assertEquals(45, result); //n(n-1)/2  for n = 10

    }

	@Test
    public void quickSortCase3Test() throws Exception
    {

		log.info("=================================================");
		log.info("...QuickSort. Small Array unsorted");
		log.info("=================================================");		
		
		
		//Given
		ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("quicksort/quickSortTest3.txt");
		ArrayList<Integer> resultArray = FileUtils.readFileAsIntArray("quicksort/quickSortTest3Sorted.txt");
		ArrayList<Integer> sortArray = new ArrayList<Integer>();
		sortArray.addAll(inputArray);

		QuickSort<Integer> sort = new QuickSort<Integer>();
		
		log.info("Input Array Size: {};",sortArray.size());

		//When
		long result = sort.call(sortArray, null, null);

		log.info("Sorted Array: {}", sortArray);
		log.info("Number of Comparations: {}",result);
		
		//Then
		Assert.assertEquals(resultArray, sortArray); //still totally ordered
		Assert.assertEquals(16, result);

    }

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

		QuickSort<Integer> sort = new QuickSort<Integer>();
		
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
