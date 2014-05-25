package org.aljuarismi.algorithm.graph;

import junit.framework.TestCase;
import org.aljuarismi.algorithm.graph.node.Graph;
import org.aljuarismi.algorithm.graph.node.GraphNode;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.aljuarismi.algorithm.utils.FileUtils;
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
public class GraphAlgTest
    extends TestCase
{

	private static Logger log = LoggerFactory.getLogger(GraphAlgTest.class);

    @Test
    public void fuseNodeCase1Test() throws Exception
    {

        log.info("=================================================");
        log.info("...Fuse node. 2 relationships");
        log.info("=================================================");


        //Given
        List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/double8.txt");
        log.info("Input graph Size: {};", inputGraph.size());



        //When
       Graph.fuseNodes(inputGraph.get(0), inputGraph.get(1));


        //Then
        StringBuffer sBuffer= new StringBuffer();

        for(GraphNode<Integer> node: inputGraph){
            sBuffer.append(node.getNodeID()).append("# ");
            for(GraphNode<Integer> edge: node.getNodeEdges()){
                sBuffer.append(edge.getNodeID()).append("; ");
            }
            sBuffer.append("\n");
        }
        log.info("Input Array Content: {}",sBuffer.toString());

    }



    @Test
    public void fuseNodeCaseDebugTest() throws Exception
    {

        log.info("=================================================");
        log.info("...Fuse node. 2 relationships");
        log.info("=================================================");


        //Given
        List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/double8.txt");
        log.info("Input graph Size: {};", inputGraph.size());



        //When
        Graph.fuseNodes(inputGraph.get(7), inputGraph.get(6));


        //Then
        StringBuffer sBuffer= new StringBuffer();

        for(GraphNode<Integer> node: inputGraph){
            sBuffer.append(node.getNodeID()).append("# ");
            for(GraphNode<Integer> edge: node.getNodeEdges()){
                sBuffer.append(edge.getNodeID()).append("; ");
            }
            sBuffer.append("\n");
        }
        log.info("Input Array Content: {}",sBuffer.toString());

    }


    @Test
    public void graphMinCutCase1Test() throws Exception
    {

		log.info("=================================================");
		log.info("...MinCut karger ALG. Big Array");
		log.info("=================================================");		
		
		
		//Given
        List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/kargerMinCut.txt");
        log.info("Input graph Size: {};", inputGraph.size());

		//When
        Integer mincutResult = Graph.minCuts(inputGraph);
		log.info("Number of Edges: {}",mincutResult);
		
		//Then
    }


    @Test
    public void graphMinCutCase2Test() throws Exception
    {

        log.info("=================================================");
        log.info("...MinCut karger ALG. Big Array with n iterations");
        log.info("=================================================");


        //Given

        Integer minCuts = Integer.MAX_VALUE;
        //When
        for( int i = 0; i < 50; i++) {
            log.info("Starting Iteration: {};", i);
            List<GraphNode<Integer>> inputGraph = FileUtils.readFileAsGraphNode("graph/kargerMinCut.txt");
            log.info("Input graph Size: {};", inputGraph.size());

            Integer mincutResult = Graph.minCuts(inputGraph);
            if( mincutResult < minCuts){ minCuts = mincutResult;}
            log.info("Number of Edges: {}", mincutResult);
        }
        //Then
        log.info("Final Result: {}", minCuts);

    }
}
