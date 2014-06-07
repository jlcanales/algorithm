package org.aljuarismi.structures;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.DirGraphNode;
import org.aljuarismi.algorithm.graph.node.GraphNode;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class MinHeapTest extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(MinHeapTest.class);


	@Test
    public void loadMinHepCase1Test() throws Exception
    {

		log.info("=================================================");
		log.info("...Heap initial load");
		log.info("=================================================");		
		
		
		//Given
        int i;
        Integer[] iArray = new Integer[]{10,5,16,13,7,6,14,11,12,1,19,2,4,15,18,17,9,3,0,8};
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (i=0; i<20; i++)
            A.add(iArray[i]);

        iArray = A.toArray(new Integer[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(iArray[i]).append(" ");
        }
        log.info("Input Data: {}", sb.toString());

        //When
        MinHeap<Integer> AH = new MinHeap<Integer>(iArray, A.size());

        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

		//Then
        Assert.assertEquals("0 1 2 3 7 4 14 11 5 8 19 16 6 15 18 17 13 12 9 10 ", sb.toString());
    }

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
        MinHeap<Integer> AH = new MinHeap<Integer>(iArray, A.size());

        sb = new StringBuilder();
        for (i=0; i<20; i++) {
            Integer element = AH.removeTop();
            sb.append(element).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ", sb.toString());
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
        MinHeap<Integer> AH = new MinHeap<Integer>(iArray, A.size());
        AH.add(new Integer(20));

        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<21; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals(new Integer(20), iArray2[iArray2.length -1]);

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
        MinHeap<Integer> AH = new MinHeap<Integer>(iArray, A.size());

        AH.add(new Integer(0));


        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<21; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //Then
        Assert.assertEquals(new Integer(0), iArray2[0]);

    }

    @Test
    public void removePosCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...Remove element at pos");
        log.info("=================================================");


        //Given
        int i;
        Integer[] iArray = new Integer[]{10, 5, 16, 13, 7, 6, 14, 11, 12, 1, 19, 2, 4, 15, 18, 17, 9, 3, 0, 8};
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (i = 0; i < 20; i++)
            A.add(iArray[i]);

        iArray = A.toArray(new Integer[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < iArray.length; i++) {
            sb.append(iArray[i]).append(" ");
        }
        log.info("Input Data: {}", sb.toString());


        MinHeap<Integer> AH = new MinHeap<Integer>(iArray, A.size());

        //********************************************
        // Print internal structure
        //********************************************
        Integer[] iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<iArray2.length; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Internal Structure: {}", sb.toString());
        //********************************************
        // Print internal structure
        //********************************************

        //When
        AH.remove(5);

        //********************************************
        // Print internal structure
        //********************************************

       iArray2 = AH.toArray(new Integer[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<iArray2.length; i++) {
            sb.append(iArray2[i]).append(" ");
        }
        log.info("Result: {}", sb.toString());

        //********************************************
        // Print internal structure
        //********************************************



        //Then
        Assert.assertEquals("0 1 2 3 7 6 14 11 5 8 19 16 10 15 18 17 13 12 9 ", sb.toString());

    }

    @Test
    public void heapNotificationCase1Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Heap notification interface test");
        log.info("=================================================");


        //Given
        int i;
        Integer[] iArray = new Integer[]{10,5,16,13,7,6,14,11,12,1,19,2,4,15,18,17,9,3,0,8};
        ArrayList<HeapInteger> A = new ArrayList<HeapInteger>();
        for (i=0; i<20; i++)
            A.add(new HeapInteger(iArray[i]));

        HeapInteger[] heapArray = A.toArray(new HeapInteger[A.size()]);

        StringBuilder sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(heapArray[i].getHeapPosition()).append(" ");
        }
        log.info("Input HeapPosition Data: {}", sb.toString());

        //When
        MinHeap<HeapInteger> AH = new MinHeap<HeapInteger>(heapArray, A.size());

        HeapInteger[] heapArray2 = AH.toArray(new HeapInteger[AH.size()]);
        sb = new StringBuilder();
        for (i=0; i<20; i++) {
            sb.append(heapArray2[i].getHeapPosition()).append(" ");
        }
        log.info("Result HeapPosition: {}", sb.toString());

        //Then
        Assert.assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ", sb.toString());
    }
}
