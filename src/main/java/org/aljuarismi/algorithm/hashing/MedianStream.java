package org.aljuarismi.algorithm.hashing;

import org.aljuarismi.structures.MaxHeap;
import org.aljuarismi.structures.MinHeap;
import org.apache.spark.api.java.function.Function;

import java.util.ArrayList;

/**
 * Created by mldev on 12/06/14.
 */
public class MedianStream extends Function<ArrayList<Integer>, ArrayList<Integer>> {
    @Override
    public ArrayList<Integer> call(ArrayList<Integer> integers) throws Exception {

        MaxHeap<Integer> heapLow  = new MaxHeap<Integer>();
        MinHeap<Integer> heapHigh = new MinHeap<Integer>();

        ArrayList<Integer> medians = new ArrayList<Integer>();

        //First Element
        heapLow.add(integers.get(0));
        medians.add(integers.get(0));
        integers.size();

        for( int kIndex = 1; kIndex <  integers.size(); kIndex++ ){

            if(kIndex%2 != 0){// hacer crecer heapHigh
                if(integers.get(kIndex)>heapLow.peekTop()){
                    heapHigh.add(integers.get(kIndex));
                }
                else{
                    Integer topLow = heapLow.removeTop();
                    heapHigh.add(topLow);
                    heapLow.add(integers.get(kIndex));
                }
            }
            else { //hacer crecer heapLow
                if (integers.get(kIndex) < heapHigh.peekTop()) {
                    heapLow.add(integers.get(kIndex));
                } else {
                    Integer topHigh = heapHigh.removeTop();
                    heapLow.add(topHigh);
                    heapHigh.add(integers.get(kIndex));
                }
            }
            medians.add(heapLow.peekTop());
        }

        return medians;
    }
}
