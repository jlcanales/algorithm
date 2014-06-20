package org.aljuarismi.algorithm.graph;

/**
 * Created by javadev on 30/05/14.
 */

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.DNode;
import org.aljuarismi.algorithm.graph.node.GNode;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class DijkstraTest extends TestCase{


    private static Logger log = LoggerFactory.getLogger(DijkstraTest.class);

    @Test
    public void dijkstraCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...Dijkstra graph");
        log.info("=================================================");

        //Given
        //
        List<DNode<Integer>> inputGraph = FileUtils.readFileAsDNodeList("dijkstra/dijkstraData.txt");
        //List<DNode<Integer>> inputGraph = FileUtils.readFileAsDNodeList("dijkstra/mediumCase.txt");


        Dijkstra<Integer> dijkstra = new Dijkstra<Integer>();

        Long[] distances = dijkstra.call(inputGraph, inputGraph.get(0));

        //Then

        //Log results
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < distances.length; i++){
            sb.append(i+1).append("->").append(distances[i]).append("; ");
        }
        log.info(sb.toString());

    }

}
