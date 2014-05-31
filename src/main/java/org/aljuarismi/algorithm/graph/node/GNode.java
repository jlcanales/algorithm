package org.aljuarismi.algorithm.graph.node;

import org.aljuarismi.algorithm.list.SortedList;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javadev on 30/05/14.
 */

public class GNode<T> implements Comparable<GNode<T>>{


    /**
     * Logger definition
     */
    private static Logger log = LoggerFactory.getLogger(GNode.class);

    /**
     * Identifies the Directed Node Sort Number in the graph topology. This number is used as base for
     * comparations (specially in the Kosarajou's algorithm)
     */
    private Integer nodeID;

    /**
     * Identify the leader of this node in a SCC.
     * To compute it its necessary to use the Kosaraju function
     */
    private Integer leaderSortNumber;

    /**
     * Store the object that is transported by the node. PayLoad is information apart from
     * the node managing information
     */
    private T payLoad;

    /**
     * Store a sorted list of all nodes that has a forward relation with this node
     */
    private List<GNode<T>> forwardsNodeEdges;

    /**
     * Store a sorted list of all nodes that has a backwards relation with this node
     */
    private List<GNode<T>> backwardsNodeEdges;


    /**
     * Explored mark for graph exploring algorithms
     */
    private boolean explored;

    /**
     * Default constructor
     */
    public GNode(){
        super();
        nodeID = null;
        explored = false;
        forwardsNodeEdges = new ArrayList<GNode<T>>();
        backwardsNodeEdges = new ArrayList<GNode<T>>();
    }

    /**
     * Builds a new object given its nodeID and a list of all the related nodes
     * with this instance
     * @param nodeID
     * @param forwardsNodeEdges
     */
    public GNode(T payLoad, Integer nodeID, List<GNode<T>> forwardsNodeEdges, List<GNode<T>> backwardsNodeEdges) throws Exception {
        this.nodeID    = nodeID;
        this.explored = false;
        this.forwardsNodeEdges = forwardsNodeEdges;
        this.backwardsNodeEdges = backwardsNodeEdges;

        QuickSort<GNode<T>> sort = new QuickSort<GNode<T>>();

        sort.call(this.forwardsNodeEdges, null, null);
        sort.call(this.backwardsNodeEdges, null, null);

    }

    /**
     * Adds a Forward Edge to a node.
     * Technically this is implemented adding the input node to de
     * forwardsNodeEdges
     * @param graphNode Destination node of the forward edge
     */
    public void addForwardNode(GNode<T> graphNode){
        addEdge(graphNode, forwardsNodeEdges);
    }

    /**
     * Adds a Backward Edge from a node. This is, the other node has a forward edge
     * to this node.
     * Technically this is implemented adding the input node to de
     * backwardsNodeEdges
     * @param graphNode Origin node of the backward edge
     */
    public void addBackwardNode(GNode<T> graphNode){
        addEdge(graphNode, backwardsNodeEdges);
    }


    /**
     * Add an edges list to the existing in this instance
     */
    private void addEdges( List<GNode<T>> inputNodeEdges, List<GNode<T>> destinationNodeEdges) throws Exception {
        QuickSort<GNode<T>> sort = new QuickSort<GNode<T>>();

        sort.call(inputNodeEdges, null, null);

        destinationNodeEdges = SortedList.mergeLists(destinationNodeEdges, inputNodeEdges);
    }

    /**
     * Add a single edge to the edges list associated with this instance
     * @param graphNode Associated Node
     */
    private void addEdge(GNode<T> graphNode, List<GNode<T>> destinationNodeEdges){
        SortedList.insert(graphNode, destinationNodeEdges,null,null);
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
    public List<GNode<T>> getForwardsNodeEdges() {
        return forwardsNodeEdges;
    }


    public void setForwardsNodeEdges(List<GNode<T>> aiForwardsNodeEdges) throws Exception {
        this.forwardsNodeEdges = aiForwardsNodeEdges;
        QuickSort<GNode<T>> sort = new QuickSort<GNode<T>>();

        sort.call(this.forwardsNodeEdges, null, null);
    }

    /**
     * Gets the list of the origin nodes of all the backward
     * edges of this instance
     * @return Backward node List
     */
    public List<GNode<T>> getBackwardsNodeEdges() {
        return backwardsNodeEdges;
    }

    public void setBackwardsNodeEdges(List<GNode<T>> backwardsNodeEdges) throws Exception {
        this.backwardsNodeEdges = backwardsNodeEdges;
        QuickSort<GNode<T>> sort = new QuickSort<GNode<T>>();

        sort.call(this.backwardsNodeEdges, null, null);
    }

    public T getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public Integer getLeaderSortNumber() {
        return leaderSortNumber;
    }

    public void setLeaderSortNumber(Integer leaderSortNumber) {
        this.leaderSortNumber = leaderSortNumber;
    }

    @Override
    public int compareTo(GNode<T> gNode) {
        return nodeID.compareTo(gNode.getNodeID());
    }
}
