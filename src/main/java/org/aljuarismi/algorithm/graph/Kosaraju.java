package org.aljuarismi.algorithm.graph;

import org.aljuarismi.algorithm.graph.node.GNode;
import org.aljuarismi.algorithm.sort.RevQuickSort;
import org.apache.spark.api.java.function.*;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by javadev on 31/05/14.
 */
public class Kosaraju<T> extends VoidFunction<List<GNode<T>>> {

    /**
     * Stack to store in process graph nodes
     */
    private Stack<GNode<T>> stack;

    /**
     * This is an internal variable used to identify the sort vertex order
     * in calling process of DFSGrev method
     */
    private Integer fullyExploredVertex;

    /**
     * This is an internal variable used to identify the current node leader
     * in calling process of DFSG method
     * (Second DFS_Loop)
     */
    private Integer currentLeader;



    /**
     * Creates Kosaraju's function
     */
    public Kosaraju() {
        stack = new Stack<GNode<T>>();
    }

    @Override
    public void call(List<GNode<T>> dirGraph) throws Exception {

        fullyExploredVertex = 0;

        Iterator<GNode<T>> iter = dirGraph.iterator();
        while(iter.hasNext()) {
            GNode<T> node = iter.next();
            if(!node.isExplored()){
                DFSGrev(dirGraph, node);

            }
        }

        RevQuickSort<GNode<T>> sort = new RevQuickSort<GNode<T>>();
        sort.call(dirGraph, null, null);

        // forward dfs
        // use negative logic for isExplored flag to avoid reseting all the
        // nodes in the graph
        Iterator<GNode<T>> fEdgeIter = dirGraph.iterator();
        while(fEdgeIter.hasNext()){
            GNode<T> node = fEdgeIter.next();
            if(node.isExplored()){ //if is NOT explored
                currentLeader = node.getNodeID();
                DFSG(dirGraph, node);
            }

        }

    }

    /**
     * Execute Deep First Search over the graph reverting the directions of all
     * edges to identify topological order in the First DFS_Loop defined in the
     * Kosaraju's algorithm
     * @param graph Graph explored
     * @param startVertex Vertex where start the exploration
     */
    public void DFSGrev(List<GNode<T>> graph, GNode<T> startVertex){

        stack.push(startVertex);

        //Start processing nodes from startVertex
        while(!stack.empty()){

            GNode<?> nodeI = stack.peek(); //Get the node but dont remove it from the stack
            boolean hasNonProcesedEdges = true;

            nodeI.setExplored(true);

            Iterator<? extends GNode<?>> fEdgeIter = nodeI.getBackwardsNodeEdges().iterator();
            while( fEdgeIter.hasNext()){
                GNode<T> nodeV = (GNode<T>)fEdgeIter.next();
                if(!nodeV.isExplored()){
                    hasNonProcesedEdges = false;
                    stack.push(nodeV);
                }
            }

            if(hasNonProcesedEdges){
                //there are no more edges to follow from nodeI
                fullyExploredVertex++;
                nodeI.setNodeID(fullyExploredVertex);
                stack.pop(); //Extract vertexI from the stack
            }
        }
    }

    /**
     * Execute Deep First Search over the graph mantaining the directions of all
     * edges to identify node leaders in the Second DFS_Loop defined in the
     * Kosaraju's algorithm
     * @param graph Graph explored
     * @param startVertex Vertex where start the exploration
     */
    public void DFSG(List<GNode<T>> graph, GNode<T> startVertex){

        stack.push(startVertex);

        //Start processing nodes from startVertex
        while(!stack.empty()){

            GNode<?> nodeI = stack.peek(); //Get the node but dont remove it from the stack
            boolean hasNonProcesedEdges = true;

            //Because this method is invoqued in the sSecon DFS loop. All the explored flags in graph
            //are set to true. To avoid reset all the flags before calling this method, in DFSG is assumed
            //a inverted logic, so if Explored Flag == true -> node has not been explored
            nodeI.setExplored(false);
            nodeI.setLeaderSortNumber(currentLeader);

            Iterator<? extends GNode<?>> fEdgeIter = nodeI.getForwardsNodeEdges().iterator();
            while( fEdgeIter.hasNext()){
                GNode<T> nodeV = (GNode<T> )fEdgeIter.next();
                if(nodeV.isExplored()){
                    hasNonProcesedEdges = false;
                    stack.push(nodeV);
                }
            }

            if(hasNonProcesedEdges){
                stack.pop(); //Extract vertexI from the stack
            }
        }
    }




    // only for testing purposes
    public void setFullyExploredVertex(Integer fullyExploredVertex) {
        this.fullyExploredVertex = fullyExploredVertex;
    }
}
