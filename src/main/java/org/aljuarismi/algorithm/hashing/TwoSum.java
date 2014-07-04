package org.aljuarismi.algorithm.hashing;

import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by mldev on 12/06/14.
 */
public class TwoSum extends Function<Hashtable<Long, Integer>, Hashtable<Long, Integer>> {


    private static Logger log = LoggerFactory.getLogger(TwoSum.class);

    @Override
    public Hashtable<Long, Integer> call(Hashtable<Long, Integer> inputHashtable) throws Exception {

        Hashtable<Long, Integer> resultTable = new Hashtable<Long, Integer>(20001);

        //Building a SearchTree form Hastable keys
        TreeMap<Long, Integer> tree = new TreeMap<Long, Integer>();

        for(Long key: inputHashtable.keySet()){
            tree.put(key, inputHashtable.get(key));
        }
        log.info("Created tree map");
        //  End of tree map generation

        for(Long key: inputHashtable.keySet()){
            NavigableMap<Long, Integer> keysRange = tree.subMap((-1) * (10000 + key), true, 10000 - key, true);

            for(Long rangeKey: keysRange.keySet()){
                if(rangeKey != key)
                    resultTable.put(rangeKey+key, resultTable.containsKey(rangeKey+key)?resultTable.get(rangeKey+key)+1:1);
            }
        }


        return resultTable;
    }

/*
    @Override
    public Hashtable<Integer, Integer> call(Hashtable<Long, Integer> inputHashtable) throws Exception {

        Hashtable<Integer, Integer> resultTable = new Hashtable<Integer, Integer>(20001);
        for(Long key: inputHashtable.keySet()){

            for(int t = -10000; t <= 10000; t++){
                if(( t-key != key) &&
                    inputHashtable.containsKey(t-key)){
                    resultTable.put(t, resultTable.containsKey(t)?resultTable.get(t)+1:1);
                }
            }
        }
        return resultTable;
    }
*/


}
