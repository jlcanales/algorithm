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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class KosarajuTest extends TestCase{


    private static Logger log = LoggerFactory.getLogger(KosarajuTest.class);

    @Test
    public void dfsGrevCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...DFS over Grev. 9 nodes graph");
        log.info("=================================================");

        //Given
        // sccbase3  -> Result 3,3,2,0,0 FALLO
        List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase3.txt", 12);

        Kosaraju<Integer> kosaraju = new Kosaraju<Integer>();

        kosaraju.setFullyExploredVertex(0);
        //When
        Iterator<DirGraphNode<Integer>> iter = inputGraph.iterator();
        while(iter.hasNext()) {
            DirGraphNode<Integer> node = iter.next();
            if(!node.isExplored()){
                kosaraju.DFSGrev(inputGraph, node);
            }
        }
        //Then

        //Log results
        iter = inputGraph.iterator();
        while(iter.hasNext()){
            DirGraphNode<Integer> node = iter.next();
            log.info("Node: {}; order: {}", node.getNodeID(), node.getTopologicalSortNumber());
        }

    }

    @Test
    public void kosarajuCase1Test() throws Exception {

        log.info("=================================================");
        log.info("...kosaraju Alg. 9 nodes graph");
        log.info("=================================================");

        //Given
        List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/SCC.txt", 875714);

        // sccbase3  -> Result 3,3,2,0,0
        //List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase3.txt", 8);

        // sccbase4  -> Result 3,3,1,1,0
        //List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase4.txt", 8);

        // sccbase5  -> Result 7,1,0,0,0
        //List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase5.txt", 8);

        // sccbase6  -> Result 6,3,2,1,0
        //List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase6.txt", 12);

        //When
        log.info("Stating kosaraju SCC");
        Kosaraju<Integer> kosaraju = new Kosaraju<Integer>();
        kosaraju.call(inputGraph);

        //Then

        //Log results
/*        Iterator<DirGraphNode<Integer>> iter = inputGraph.iterator();
        while(iter.hasNext()){
            DirGraphNode<Integer> node = iter.next();

            //log.info("Node: {}; order: {}", node.getNodeID(), node.getTopologicalSortNumber());
            log.debug("Node: {}; order: {}; Leader:" + node.getLeaderSortNumber().intValue() + ";", node.getNodeID(), node.getTopologicalSortNumber());
        }
*/
        //Print Sorted Node volumes
        Map<Integer, Integer> sccMap = DirGraph.getSCCVolumes(inputGraph);

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Integer, Integer>> entryIter = sccMap.entrySet().iterator();
        int elements = 0;
        while (entryIter.hasNext() && elements < 10) {
            Map.Entry<Integer, Integer> entry = entryIter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (entryIter.hasNext()) {
                sb.append(';').append(' ');
            }
            elements++;
        }
        log.info(sb.toString());

    }

}
