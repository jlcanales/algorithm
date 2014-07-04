package org.aljuarismi.algorithm.hashing;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class MediansTest extends TestCase {

    private static Logger log = LoggerFactory.getLogger(MediansTest.class);

    @Test
    public void mediansCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...Basic count testfrom file");
        log.info("=================================================");

        //Given
        ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("hashing/mediansCase1.txt");

        log.info("Readed elements {}", inputArray.size());
        Assert.assertEquals(7, inputArray.size());

        MedianStream mStream = new MedianStream();

        ArrayList<Integer> mediansArray = mStream.call(inputArray);

        StringBuffer sBuffer= new StringBuffer();
        Integer mediansTotal = 0;
        for(Integer key: mediansArray){
            sBuffer.append(key).append(", ");
            mediansTotal += key;
        }
        log.info("Output HashTable Content: {}",sBuffer.toString());
        log.info("Medians Total: {}", mediansTotal);

        Assert.assertEquals(Integer.valueOf(23), mediansTotal);

    }


    @Test
    public void mediansCase2Test() throws Exception {

        log.info("=================================================");
        log.info("...Basic count testfrom file");
        log.info("=================================================");

        //Given
        ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("hashing/mediansCase2.txt");

        log.info("Readed elements {}", inputArray.size());
        Assert.assertEquals(10, inputArray.size());

        MedianStream mStream = new MedianStream();

        ArrayList<Integer> mediansArray = mStream.call(inputArray);

        StringBuffer sBuffer= new StringBuffer();
        Integer mediansTotal = 0;
        for(Integer key: mediansArray){
            sBuffer.append(key).append(", ");
            mediansTotal += key;
        }
        log.info("Output HashTable Content: {}",sBuffer.toString());
        log.info("Medians Total: {}", mediansTotal);

        Assert.assertEquals(Integer.valueOf(54), mediansTotal);

    }

    @Test
    public void mediansFinalTest() throws Exception {

        log.info("=================================================");
        log.info("...Basic count testfrom file");
        log.info("=================================================");

        //Given
        ArrayList<Integer> inputArray = FileUtils.readFileAsIntArray("hashing/Median.txt");

        log.info("Readed elements {}", inputArray.size());
        //Assert.assertEquals(10, inputArray.size());

        MedianStream mStream = new MedianStream();

        ArrayList<Integer> mediansArray = mStream.call(inputArray);

        StringBuffer sBuffer= new StringBuffer();
        Integer mediansTotal = 0;
        for(Integer key: mediansArray){
            sBuffer.append(key).append(", ");
            mediansTotal += key;
        }
        log.info("Output HashTable Content: {}",sBuffer.toString());
        log.info("Medians Size: {}", mediansArray.size());
        log.info("Medians Total: {}", mediansTotal);
        log.info("Medians Result: {}", mediansTotal%10000);

        //Assert.assertEquals(Integer.valueOf(54), mediansTotal);

    }


}