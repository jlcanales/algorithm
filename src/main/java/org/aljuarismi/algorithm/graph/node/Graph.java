package org.aljuarismi.algorithm.graph.node;

import org.aljuarismi.algorithm.list.SortedList;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by javadev on 24/05/14.
 */
public class Graph {

    private static Logger log = LoggerFactory.getLogger(Graph.class);

    public static <T extends Comparable<T>> Integer minCuts(List<GraphNode<T>> nodeList) throws Exception {

        // Base Case
        if(nodeList.size() < 2){
            return 0;
        }
        if(nodeList.size() == 2){
            return nodeList.get(0).getNodeEdges().size();
        }


        //more than two nodes
        // Step 1: Get an edge randomly
        ArrayList<GraphNode<T>> edgeNodes = new ArrayList<GraphNode<T>>();

        Random rndGenerator = new Random();
        int nodeIndex = rndGenerator.nextInt(nodeList.size());
        int secNodeIndex;
        GraphNode<T> firstNode = nodeList.get(nodeIndex);

        secNodeIndex=firstNode.getNodeEdges().size()>1?rndGenerator.nextInt(firstNode.getNodeEdges().size()):0;

        edgeNodes.add(firstNode);
        edgeNodes.add(firstNode.getNodeEdges().get(secNodeIndex));
        // End Step 1.


        // Step 2 Fuse Nodes:
        log.debug("Merging nodes {} - {}",edgeNodes.get(0).getNodeID(), edgeNodes.get(1).getNodeID() );

        fuseNodes(edgeNodes.get(0), edgeNodes.get(1));


        // Step 3: remove second node from the list
        SortedList.remove(edgeNodes.get(1), nodeList, null, null); //O(log( n))

        if(log.isDebugEnabled()){
            //volcado lista de nodos
            StringBuffer sBuffer= new StringBuffer();

            sBuffer.append("MINCUTS iteration Array Content:\n");
            for(GraphNode<T> node: nodeList){
                sBuffer.append(node.getNodeID()).append("# ");
                for(GraphNode<T> edge: node.getNodeEdges()){
                    sBuffer.append(edge.getNodeID()).append("; ");
                }
                sBuffer.append("\n");
            }
            sBuffer.append("MINCUTS END\n");
            log.debug(sBuffer.toString());

        }

        return minCuts(nodeList);
    }

    /**
     * Merge two nodes and store the result node in the first parameter
     * @param nodeA First node that will be the result node of node fusion.
     * @param nodeB Second node.
     * @param <T> Node element type.
     */
    public static <T extends Comparable<T>> void fuseNodes(GraphNode<T> nodeA, GraphNode<T>nodeB) throws Exception {

         //long startTime = System.currentTimeMillis();

        // Step 1: add nodeB to the fuseNodes list in nodeA
        nodeA.addFuseNode(nodeB);

        // Step 2: add nodeB edges to nodeA
        List<GraphNode<T>> nodeBEdges = new ArrayList<GraphNode<T>>(nodeB.getNodeEdges()); //Make a list copy to not modify original list.
        List<GraphNode<T>> nodeAEdges = new ArrayList<GraphNode<T>>(nodeA.getNodeEdges());

        List<GraphNode<T>> mergedEdges = SortedList.mergeLists(nodeAEdges, nodeBEdges); // O(n + m)

        //Step 2.1 Remove loops
        SortedList.remove(nodeA, mergedEdges, null, null); //O(log( n))
        SortedList.remove(nodeB, mergedEdges, null, null); //O(log( n))

        nodeA.setNodeEdges(mergedEdges);


        // Step 3: Reordering associations in all the nodeB edges elements
        for( GraphNode<T> bEdgeNode: nodeBEdges){

            // Step 3.1 get indexes of NodeB references y this node
            List<Integer> nodeBRefs = SortedList.search(nodeB, bEdgeNode.getNodeEdges(), null, null);


            // Step 3.2 substituted by nodeA
            SortedList.remove(nodeB,bEdgeNode.getNodeEdges(),null,null);//O(log(n))
            for( int i = 0; i<nodeBRefs.size();i++){
                SortedList.insert(nodeA,bEdgeNode.getNodeEdges(),null,null);//O(log(n))
            }


        }

        //Finished
    }
}
