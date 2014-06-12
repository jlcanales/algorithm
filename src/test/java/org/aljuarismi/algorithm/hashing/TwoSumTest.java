package org.aljuarismi.algorithm.hashing;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TwoSumTest extends TestCase {

    private static Logger log = LoggerFactory.getLogger(TwoSumTest.class);

    @Test
    public void twoSumCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...Basic count testfrom file");
        log.info("=================================================");

        //Given
        Hashtable<Long, Integer> inputTable = FileUtils.readFileAsHashTable("hashing/twosumCase1.txt", 11);

        log.info("Readed elements {}", inputTable.keySet().size());
        Assert.assertEquals(3, inputTable.keySet().size());

        TwoSum twoSummatory = new TwoSum();

        Hashtable<Long, Integer> resultTable = twoSummatory.call(inputTable);

        StringBuffer sBuffer= new StringBuffer();
        for(Long key: resultTable.keySet()){
            sBuffer.append(key).append(", ");
        }
        log.info("Output HashTable Content: {}",sBuffer.toString());

        Assert.assertEquals(3, resultTable.keySet().size());

    }


    @Test
    public void twoSumCase2Test() throws Exception {

        log.info("=================================================");
        log.info("...Basic count 2 file");
        log.info("=================================================");

        //Given
        Hashtable<Long, Integer> inputTable = FileUtils.readFileAsHashTable("hashing/twosumCase2.txt", 11);

        log.info("Readed elements {}", inputTable.keySet().size());
        Assert.assertEquals(4, inputTable.keySet().size());

        TwoSum twoSummatory = new TwoSum();

        Hashtable<Long, Integer> resultTable = twoSummatory.call(inputTable);

        StringBuffer sBuffer= new StringBuffer();
        for(Long key: resultTable.keySet()){
            sBuffer.append(key).append(", ");
        }
        log.info("Output HashTable Content: {}",sBuffer.toString());

        Assert.assertEquals(5, resultTable.keySet().size());

    }

    @Test
    public void twoSumCaseFinalTest() throws Exception {

        log.info("=================================================");
        log.info("...Final case result");
        log.info("=================================================");

        //Given
        Hashtable<Long, Integer> inputTable = FileUtils.readFileAsHashTable("hashing/algo1_programming_prob_2sum.txt", 1000001);

        log.info("Readed elements {}", inputTable.keySet().size());
        Assert.assertEquals(999752, inputTable.keySet().size());

        TwoSum twoSummatory = new TwoSum();

        Hashtable<Long, Integer> resultTable = twoSummatory.call(inputTable);
/*
        StringBuffer sBuffer= new StringBuffer();
        for(Integer key: resultTable.keySet()){
            sBuffer.append(key).append(", ");
        }
*/
        log.info("Output HashTable Size: {}",resultTable.size());


    }

}