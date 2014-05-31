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

public class DirGraphNode<T extends Comparable<T>> implements Comparable<DirGraphNode<T>>{


    /**
     * Logger definition
     */
    private static Logger log = LoggerFactory.getLogger(DirGraphNode.class);

    /**
     * Identifies the node to distinguish from other nodes
     */
    private T nodeID;

    private Integer leaderSortNumber;

    /**
     * Identifies the Directed Node Sort Number in the graph topology. This number is used as base for
     * comparations (specially in the Kosarajou's algorithm)
     */
    private Integer topologicalSortNumber;

    /**
     * Store a sorted list of all nodes that has a forward relation with this node
     */
    private List<DirGraphNode<T>> forwardsNodeEdges;

    /**
     * Store a sorted list of all nodes that has a backwards relation with this node
     */
    private List<DirGraphNode<T>> backwardsNodeEdges;


    /**
     * Explored mark for graph exploring algorithms
     */
    private boolean explored;

    /**
     * Default constructor
     */
    public DirGraphNode(){
        super();
        nodeID = null;
        explored = false;
        topologicalSortNumber = 0;
        forwardsNodeEdges = new ArrayList<DirGraphNode<T>>();
        backwardsNodeEdges = new ArrayList<DirGraphNode<T>>();
    }

    /**
     * Builds a new object given its nodeID and a list of all the related nodes
     * with this instance
     * @param nodeID
     * @param forwardsNodeEdges
     */
    public DirGraphNode(T nodeID, Integer topologicalSortNumber, List<DirGraphNode<T>> forwardsNodeEdges, List<DirGraphNode<T>> backwardsNodeEdges ) throws Exception {
        this.nodeID    = nodeID;
        this.explored = false;
        this.forwardsNodeEdges = forwardsNodeEdges;
        this.backwardsNodeEdges = backwardsNodeEdges;

        QuickSort<DirGraphNode<T>> sort = new QuickSort<DirGraphNode<T>>();

        sort.call(this.forwardsNodeEdges, null, null);
        sort.call(this.backwardsNodeEdges, null, null);

    }

    public void addForwardNode(DirGraphNode<T> graphNode){
        addEdge(graphNode, forwardsNodeEdges);
    }

    public void addBackwardNode(DirGraphNode<T> graphNode){
        addEdge(graphNode, backwardsNodeEdges);
    }


    /**
     * Add an edges list to the existing in this instance
     */
    private void addEdges( List<DirGraphNode<T>> inputNodeEdges, List<DirGraphNode<T>> destinationNodeEdges) throws Exception {
        QuickSort<DirGraphNode<T>> sort = new QuickSort<DirGraphNode<T>>();

        sort.call(inputNodeEdges, null, null);

        destinationNodeEdges = SortedList.mergeLists(destinationNodeEdges, inputNodeEdges);
    }

    /**
     * Add a single edge to the edges list associated with this instance
     * @param graphNode Associated Node
     */
    private void addEdge(DirGraphNode<T> graphNode, List<DirGraphNode<T>> destinationNodeEdges){
        SortedList.insert(graphNode, destinationNodeEdges,null,null);
    }




    @Override
    public int compareTo(DirGraphNode<T> dirGraphNode) {
        return topologicalSortNumber.compareTo(dirGraphNode.getTopologicalSortNumber());
    }

    public Integer getTopologicalSortNumber() {
        return topologicalSortNumber;
    }

    public void setTopologicalSortNumber(Integer topologicalSortNumber) {
        this.topologicalSortNumber = topologicalSortNumber;
    }

    public T getNodeID() {
        return nodeID;
    }

    public void setNodeID(T nodeID) {
        this.nodeID = nodeID;
    }

    public List<DirGraphNode<T>> getForwardsNodeEdges() {
        return forwardsNodeEdges;
    }

    public void setForwardsNodeEdges(List<DirGraphNode<T>> aiForwardsNodeEdges) throws Exception {
        this.forwardsNodeEdges = aiForwardsNodeEdges;
        QuickSort<DirGraphNode<T>> sort = new QuickSort<DirGraphNode<T>>();

        sort.call(this.forwardsNodeEdges, null, null);
    }

    public List<DirGraphNode<T>> getBackwardsNodeEdges() {
        return backwardsNodeEdges;
    }

    public void setBackwardsNodeEdges(List<DirGraphNode<T>> backwardsNodeEdges) throws Exception {
        this.backwardsNodeEdges = backwardsNodeEdges;
        QuickSort<DirGraphNode<T>> sort = new QuickSort<DirGraphNode<T>>();

        sort.call(this.backwardsNodeEdges, null, null);
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
}
