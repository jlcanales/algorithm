package org.aljuarismi.algorithm.utils;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.GraphNode;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
