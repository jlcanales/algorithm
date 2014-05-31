package org.aljuarismi.algorithm.utils;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.DirGraphNode;
import org.aljuarismi.algorithm.graph.node.GraphNode;
import org.aljuarismi.algorithm.sort.QuickSort;
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
public class utilsTest
    extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(utilsTest.class);


	@Test
    public void readNodesCase1Test() throws Exception
    {

		log.info("=================================================");
		log.info("...Read Nodes from file");
		log.info("=================================================");		
		
		
		//Given
        //List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/kargerMinCut.txt");
        //List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/circle7.txt");
        List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/double8.txt");

        StringBuffer sBuffer= new StringBuffer();

        for(GraphNode<Integer> node: inputGraph){
            sBuffer.append(node.getNodeID()).append("# ");
            for(GraphNode<Integer> edge: node.getNodeEdges()){
                sBuffer.append(edge.getNodeID()).append("; ");
            }
            sBuffer.append("\n");
        }

		log.info("Input Array Size: {};",inputGraph.size());
        log.info("Input Array Content: {}",sBuffer.toString());

		//Then
        Assert.assertEquals(8, inputGraph.size());
    }

    @Test
    public void readDirNodesCase1Test() throws IOException {

        log.info("=================================================");
        log.info("...Read Nodes from file");
        log.info("=================================================");


        //Given
        List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/sccbase1.txt", 9);
        //List<DirGraphNode<Integer>> inputGraph = FileUtils.readFileAsDirectedGraphNode("dirgraph/SCC.txt", 875714);


        log.info("Input Array Size: {};",inputGraph.size());

        //Then
        Assert.assertEquals(9, inputGraph.size());
        Assert.assertEquals(3,inputGraph.get(5).getForwardsNodeEdges().get(0).getNodeID().intValue());
        Assert.assertEquals(8,inputGraph.get(5).getForwardsNodeEdges().get(1).getNodeID().intValue());
        Assert.assertEquals(9,inputGraph.get(5).getBackwardsNodeEdges().get(0).getNodeID().intValue());
    }

}
