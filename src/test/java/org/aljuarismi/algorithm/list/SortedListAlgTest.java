package org.aljuarismi.algorithm.list;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SortedListAlgTest
    extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(SortedListAlgTest.class);


	@Test
    public void mergeListsCase1Test() throws Exception
    {

		log.info("=================================================");
		log.info("...Merge sorted lists. Big Array unsorted");
		log.info("=================================================");		
		
		
		//Given
		List<Integer> leftInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest11.txt");
        List<Integer> rightInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest12.txt");
		List<Integer> sortArray;

		
		log.info("Input Array Size: {} {};",leftInputArray.size(), rightInputArray.size());

		//When
        sortArray = SortedList.mergeLists(leftInputArray, rightInputArray);

		log.info("Sorted Array: {}", sortArray);
		
		//Then

		for( int i = 1; i < sortArray.size(); i++){
			Assert.assertEquals(i, sortArray.get(i - 1).intValue());			
		}

    }

    @Test
    public void mergeListsCase2Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Merge sorted lists. Inverted list form case 1");
        log.info("=================================================");


        //Given
        List<Integer> leftInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest11.txt");
        List<Integer> rightInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest12.txt");
        List<Integer> sortArray;


        log.info("Input Array Size: {} {};",leftInputArray.size(), rightInputArray.size());

        //When
        sortArray = SortedList.mergeLists( rightInputArray, leftInputArray);

        log.info("Sorted Array: {}", sortArray);

        //Then

        for( int i = 1; i < sortArray.size(); i++){
            Assert.assertEquals(i, sortArray.get(i - 1).intValue());
        }

    }
    @Test
    public void mergeListsCase3Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Merge sorted lists. Mixed Elements");
        log.info("=================================================");


        //Given
        List<Integer> leftInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest31.txt");
        List<Integer> rightInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest32.txt");
        List<Integer> sortArray;


        log.info("Input Array Size: {} {};",leftInputArray.size(), rightInputArray.size());

        //When
        sortArray = SortedList.mergeLists( rightInputArray, leftInputArray);

        log.info("Sorted Array: {}", sortArray);

        //Then

        for( int i = 1; i < sortArray.size(); i++){
            Assert.assertEquals(i, sortArray.get(i - 1).intValue());
        }

    }
    @Test
    public void mergeListsCase4Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Merge sorted lists. Mixed Elements and duplicates");
        log.info("=================================================");


        //Given
        List<Integer> leftInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest31.txt");
        List<Integer> rightInputArray = FileUtils.readFileAsIntArray("sortedlist/MergeListTest31.txt");
        List<Integer> sortArray;


        log.info("Input Array Size: {} {};",leftInputArray.size(), rightInputArray.size());

        //When
        sortArray = SortedList.mergeLists( rightInputArray, leftInputArray);

        log.info("Sorted Array: {}", sortArray);

        //Then

        for( int i = 0; i < sortArray.size(); i++){
            if(i%2 == 0)
                Assert.assertEquals(i+1, sortArray.get(i).intValue());
            else
                Assert.assertEquals(i, sortArray.get(i).intValue());
        }

    }


    @Test
    public void searchCase1Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Search in sorted lists. simple search");
        log.info("=================================================");


        //Given
        List<Integer> inputList = FileUtils.readFileAsIntArray("sortedlist/SearchListTest1.txt");
        List<Integer> result;


        log.info("Input Array Size: {};",inputList.size());

        //When
        result = SortedList.search(15,inputList,null,null);

        log.info("Result Indexes Array: {}", result);

        //Then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(15, inputList.get(result.get(0).intValue()).intValue()); // result is a indexes list (not a element list)

    }


    @Test
    public void searchCase2Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Search in sorted lists. Sorted list with duplicates");
        log.info("=================================================");


        //Given
        List<Integer> inputList = FileUtils.readFileAsIntArray("sortedlist/SearchListTest2.txt");
        List<Integer> result;


        log.info("Input Array Size: {};",inputList.size());

        //When
        result = SortedList.search(13,inputList,null,null);

        log.info("Result Indexes Array: {}", result);

        //Then
        Assert.assertEquals(3,result.size());
        Assert.assertEquals(13, inputList.get(result.get(0).intValue()).intValue()); // result is a indexes list (not a element list)
        Assert.assertEquals(13, inputList.get(result.get(1).intValue()).intValue()); // result is a indexes list (not a element list)
        Assert.assertEquals(13, inputList.get(result.get(2).intValue()).intValue()); // result is a indexes list (not a element list)
    }


    @Test
    public void searchCase3Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Search in sorted lists. search fisrt element");
        log.info("=================================================");


        //Given
        List<Integer> inputList = FileUtils.readFileAsIntArray("sortedlist/SearchListTest1.txt");
        List<Integer> result;


        log.info("Input Array Size: {};",inputList.size());

        //When
        result = SortedList.search(1,inputList,null,null);

        log.info("Result Indexes Array: {}", result);

        //Then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(1, inputList.get(result.get(0).intValue()).intValue()); // result is a indexes list (not a element list)

    }

    @Test
    public void searchCase4Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Search in sorted lists. search last element");
        log.info("=================================================");


        //Given
        List<Integer> inputList = FileUtils.readFileAsIntArray("sortedlist/SearchListTest1.txt");
        List<Integer> result;


        log.info("Input Array Size: {};",inputList.size());

        //When
        result = SortedList.search(20,inputList,null,null);

        log.info("Result Indexes Array: {}", result);

        //Then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(20, inputList.get(result.get(0).intValue()).intValue()); // result is a indexes list (not a element list)

    }
    @Test
    public void deDupeCase1Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Clean Duplicates in sorted lists. Sorted list with duplicates");
        log.info("=================================================");


        //Given
        List<Integer> inputList = FileUtils.readFileAsIntArray("sortedlist/SearchListTest2.txt");
        int locatedDupe;


        log.info("Input Array Size: {};",inputList.size());

        //When
        locatedDupe = SortedList.deDupe(inputList);

        log.info("Duplicated located: {}", locatedDupe);
        log.info("Result List: {}", inputList);

        //Then
        Assert.assertEquals(4,locatedDupe);
        for( int i = 1; i < inputList.size(); i++){
            Assert.assertEquals(i, inputList.get(i - 1).intValue());
        }
    }

}
