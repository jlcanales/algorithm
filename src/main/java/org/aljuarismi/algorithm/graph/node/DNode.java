package org.aljuarismi.algorithm.graph.node;

import org.aljuarismi.algorithm.list.SortedList;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.aljuarismi.structures.heap.HeapNotificable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javadev on 30/05/14.
 */

public class DNode<T> implements Comparable<DNode<T>>,HeapNotificable{


    /**
     * Logger definition
     */
    private static Logger log = LoggerFactory.getLogger(DNode.class);

    /**
     * Identifies the Directed Node Sort Number in the graph topology. This number is used as base for
     * comparations (specially in the Kosarajou's algorithm)
     */
    private Integer nodeID;

    /**
     * Store the object that is transported by the node. PayLoad is information apart from
     * the node managing information
     */
    private T payLoad;

    /**
     * Store a sorted list of all nodes that has a forward relation with this node
     */
    private List<Edge> forwardsNodeEdges;

    /**
     * Store a sorted list of all nodes that has a backwards relation with this node
     */
    private List<Edge> backwardsNodeEdges;

    /**
     * Computed Dijkstra Distance for this node
     */
    private Long dijkstraDistance;

    /**
     * heapPosition to be used when the node is in a heap
     */
    private int heapPosition;


    /**
     * Default constructor
     */
    public DNode(){
        super();
        this.nodeID = null;
        this.dijkstraDistance = new Long(Long.MAX_VALUE);
        this.forwardsNodeEdges = new ArrayList<Edge>();
        this.backwardsNodeEdges = new ArrayList<Edge>();
    }

    /**
     * Builds a new object given its nodeID and a list of all the related nodes
     * with this instance
     * @param nodeID
     */
    public DNode(T payLoad, Integer nodeID) throws Exception {
        super();
        this.nodeID    = nodeID;
        this.dijkstraDistance = new Long(Long.MAX_VALUE);
        this.forwardsNodeEdges = new ArrayList<Edge>();
        this.backwardsNodeEdges = new ArrayList<Edge>();


    }

    /**
     * Adds a Forward Edge to a node.
     * Technically this is implemented adding the input node to de
     * forwardsNodeEdges
     * @param graphNode Destination node of the forward edge
     */
    public void addForwardNode(Edge graphNode){
        addEdge(graphNode, forwardsNodeEdges);
    }

    /**
     * Adds a Backward Edge from a node. This is, the other node has a forward edge
     * to this node.
     * Technically this is implemented adding the input node to de
     * backwardsNodeEdges
     * @param graphNode Origin node of the backward edge
     */
    public void addBackwardNode(Edge graphNode){
        addEdge(graphNode, backwardsNodeEdges);
    }


    /**
     * Add an edges list to the existing in this instance
     */
    private void addEdges( List<Edge> inputNodeEdges, List<Edge> destinationNodeEdges) throws Exception {
        destinationNodeEdges.addAll(inputNodeEdges);
    }

    /**
     * Add a single edge to the edges list associated with this instance
     * @param graphNode Associated Node
     */
    private void addEdge(Edge graphNode, List<Edge> destinationNodeEdges){
        destinationNodeEdges.add(graphNode);
    }


    /**
     * Gets the nodeID of this instance
     * @return nodeId
     */
    public Integer getNodeID() {
        return nodeID;
    }

    /**
     * Sets the nodeID for this instance
     * @param nodeID
     */
    public void setNodeID(Integer nodeID) {
        this.nodeID = nodeID;
    }

    /**
     * Gets the list of the destination nodes of all the forward
     * edges of this instance
     * @return Forward node List
     */
    public List<Edge> getForwardsNodeEdges() {
        return forwardsNodeEdges;
    }


    public void setForwardsNodeEdges(List<Edge> aiForwardsNodeEdges) throws Exception {
        this.forwardsNodeEdges = aiForwardsNodeEdges;
    }

    /**
     * Gets the list of the origin nodes of all the backward
     * edges of this instance
     * @return Backward node List
     */
    public List<Edge> getBackwardsNodeEdges() {
        return backwardsNodeEdges;
    }

    public void setBackwardsNodeEdges(List<Edge> backwardsNodeEdges) throws Exception {
        this.backwardsNodeEdges = backwardsNodeEdges;
    }

    public T getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }

    public Long getDijkstraDistance() {
        return dijkstraDistance;
    }

    public void setDijkstraDistance(Long dijkstraDistance) {
        this.dijkstraDistance = dijkstraDistance;
    }


    @Override
    public int compareTo(DNode<T> gNode) {
        return dijkstraDistance.compareTo(gNode.getDijkstraDistance());
    }

    @Override
    public void setHeapPosition(int heapPosition) {
        this.heapPosition = heapPosition;
    }

    @Override
    public int getHeapPosition() {
        return this.heapPosition;
    }
}




