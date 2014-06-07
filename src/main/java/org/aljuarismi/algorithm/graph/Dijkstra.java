package org.aljuarismi.algorithm.graph;

import org.aljuarismi.algorithm.graph.node.DNode;
import org.aljuarismi.algorithm.graph.node.Edge;
import org.aljuarismi.structures.MinHeap;
import org.apache.spark.api.java.function.Function2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by javadev on 06/06/14.
 */
public class Dijkstra<T extends Comparable<T>> extends Function2<List<DNode<T>>, DNode<T>,Long[]> {


    /**
     * Logger definition
     */
    private static Logger log = LoggerFactory.getLogger(Dijkstra.class);

    /**
     * Heap used to implement the Dijkstra algorithm
     */
    private MinHeap<DNode<T>> heap;



    public Dijkstra() {
        super();
        this.heap = new MinHeap<DNode<T>>();
    }


    @Override
    public Long[] call(List<DNode<T>> nodeList, DNode<T> sNode) throws Exception {

        log.debug("Initializing heap and origing node");

        heap.addAll(nodeList);
        Long[] distancesList = new Long[nodeList.size()];

        //First Node Initialization
        heap.remove(sNode.getHeapPosition());
        sNode.setDijkstraDistance(Long.valueOf(0));
        distancesList[(sNode.getNodeID()-1)] = Long.valueOf(0);

        //Prepare sNode edges distances
        for(Edge<T> edge: sNode.getForwardsNodeEdges()){
            heap.remove(edge.destination.getHeapPosition());
            edge.destination.setDijkstraDistance(edge.distance);
            heap.add(edge.destination);
        }

        if(log.isDebugEnabled()){
            //********************************************
            // Print internal structure
            //********************************************
            DNode<T>[] iArray2 = heap.toArray( (DNode<T>[])  Array.newInstance(sNode.getClass(),heap.size()));
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<iArray2.length; i++) {
                sb.append(iArray2[i].getHeapPosition()).append(",")
                        .append(iArray2[i].getNodeID()).append(",")
                        .append(iArray2[i].getDijkstraDistance()).append(" ");

            }
            log.info("Internal Structure: {}", sb.toString());
            //********************************************
            // Print internal structure
            //********************************************
        }

        //Main Loop
        while(heap.size() > 0){
            DNode<T> vNode = heap.removeTop();

            distancesList[(vNode.getNodeID()-1)] = vNode.getDijkstraDistance();

            for(Edge<T> edge: vNode.getForwardsNodeEdges()){

                //all those edge destinations whose HeadPosition is higher than
                //the heap size, are nodes that has been processed yet. So, it cannot
                // be processed again because they dont stay in the heap.
                if(edge.destination.getHeapPosition() < heap.size()) {
                    heap.remove(edge.destination.getHeapPosition());

                    if ( vNode.getDijkstraDistance() < Long.MAX_VALUE &&
                        (edge.destination.getDijkstraDistance() > vNode.getDijkstraDistance() + edge.distance)) {
                        edge.destination.setDijkstraDistance(vNode.getDijkstraDistance() + edge.distance);
                    }
                    heap.add(edge.destination);
                }
            }
        }


        return distancesList;
    }
}

