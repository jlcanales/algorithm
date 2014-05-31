package org.aljuarismi.algorithm.graph;

/**
 * Created by javadev on 30/05/14.
 */

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.DirGraph;
import org.aljuarismi.algorithm.graph.node.DirGraphNode;
import org.aljuarismi.algorithm.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class DirGraphAlgTest extends TestCase{


    private static Logger log = LoggerFactory.getLogger(DirGraphAlgTest.class);

    @Test
    public void dfsGrevCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...DFS over Grev. 9 nodes graph");
        log.info("=================================================");

        //Given
        List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase6.txt", 12);
        Integer currentLabel = 0;
        //When
        Iterator<DirGraphNode<Integer>> iter = inputGraph.iterator();
        while(iter.hasNext()) {
            DirGraphNode<Integer> node = iter.next();
            if(!node.isExplored()){
                currentLabel = DirGraph.DFSGrev(inputGraph, node, currentLabel);
            }
        }
        //Then

        //Log results
        iter = inputGraph.iterator();
        while(iter.hasNext()){
            DirGraphNode<Integer> node = iter.next();
            log.debug("Node: {}; order: {}", node.getNodeID(), node.getTopologicalSortNumber());
        }

    }

    @Test
    public void kosarajuCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...kosaraju Alg. 9 nodes graph");
        log.info("=================================================");

        //Given
        List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase3.txt", 12);

        //When
        DirGraph.kosaraju(inputGraph);

        //Then

        //Log results
        Iterator<DirGraphNode<Integer>> iter = inputGraph.iterator();
        while(iter.hasNext()){
            DirGraphNode<Integer> node = iter.next();
            log.debug("Node: {}; order: {}; Leader:" + node.getLeaderSortNumber().intValue() + ";", node.getNodeID(), node.getTopologicalSortNumber());
        }

        //Print Sorted Node volumes
        Map<Integer, Integer> sccMap = DirGraph.getSCCVolumes(inputGraph);

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Integer, Integer>> entryIter = sccMap.entrySet().iterator();
        while (entryIter.hasNext()) {
            Map.Entry<Integer, Integer> entry = entryIter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (entryIter.hasNext()) {
                sb.append(';').append(' ');
            }
        }
        log.info(sb.toString());
    }

    }
