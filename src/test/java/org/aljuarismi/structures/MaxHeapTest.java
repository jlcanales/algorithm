package org.aljuarismi.structures;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class MaxHeapTest extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(MaxHeapTest.class);


    @Test
    public void removeTopCase1Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Heap remove Top operation");
        log.info("=================================================");


        //Given
        int i;
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (i=0; i<20; i++)
            A.add(new Integer(i));
        DSutil.permute(A);

        Integer[] iArray = A.toArray(new Integer[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(iArray[i]).append(" ");
        }
        log.info("Input Data: {}", sb.toString());

        //When
        MaxHeap<Integer> AH = new MaxHeap<Integer>(iArray, A.size());

        sb = new StringBuilder();
        for (i=0; i<20; i++) {
            Integer element = AH.removeTop();
            sb.append(element).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals("19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 ", sb.toString());
    }


    @Test
    public void addElementCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...Add element at the end of heap operation");
        log.info("=================================================");

        //Given
        int i;
        Integer[] iArray = new Integer[]{10, 5, 16, 13, 7, 6, 14, 11, 12, 1, 19, 2, 4, 15, 18, 17, 9, 3, 0, 8};
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (i = 0; i < 20; i++)
            A.add(iArray[i]);

        iArray = A.toArray(new Integer[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < 20; i++) {
            sb.append(iArray[i]).append(" ");
        }
        log.info("Input Data: {}", sb.toString());


        //When
        MaxHeap<Integer> AH = new MaxHeap<Integer>(iArray, A.size());
        AH.add(new Integer(20));

        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<21; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals(new Integer(20), iArray2[0]);

    }


    @Test
    public void addElementCase2Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Add element at the top of heap operation");
        log.info("=================================================");


        //Given
        int i;
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (i=0; i<20; i++)
            A.add(new Integer(i + 1));
        DSutil.permute(A);

        Integer[] iArray = A.toArray(new Integer[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(iArray[i]).append(" ");
        }
        log.info("Input Data: {}", sb.toString());

        //When
        MaxHeap<Integer> AH = new MaxHeap<Integer>(iArray, A.size());

        AH.add(new Integer(0));


        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<21; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals(new Integer(0), iArray2[iArray2.length -1]);

    }

}
