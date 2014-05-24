package org.aljuarismi.algorithm.graph.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by javadev on 24/05/14.
 */
public class GraphNode <T extends Comparable<T>> implements Comparable<GraphNode<T>>{

    /**
     * Logger definition
     */
    private static Logger log = LoggerFactory.getLogger(GraphNode.class);

    /**
     * Identifies the node to distinguish from other nodes
     */
    private T nodeID;

    /**
     * Store a sorted list of all the GraphNode object that are related with this instance
     */
    private List<GraphNode<T>> nodeEdges;

    /**
     * Store a sorted list of all nodes that has been fused with this instance when a fuse
     * operation was applied over this node
     */
    private List<GraphNode<T>> fuseNodes;

    /**
     * Default constructor
     */
    public GraphNode(){}

    /**
     * Builds a new object given its nodeID and a list of all the related nodes
     * with this instance
     * @param nodeID
     * @param nodeEdges
     */
    public GraphNode(T nodeID, List<GraphNode<T>> nodeEdges ){}

    /**
     * Add an edges list to the existing in this instance
     * @param nodeEdges
     */
    public void addEdges( List<GraphNode<T>> nodeEdges){
        throw new UnsupportedOperationException();
    }

    /**
     * Add a single edge to the edges list associated with this instance
     * @param graphNode Associated Node
     */
    public void addEdge(GraphNode<T> graphNode){
        throw new UnsupportedOperationException();
    }

    /**
     * Add a fused node to the fused node list associated with this instance
     * @param graphNode
     */
    public void addFuseNode(GraphNode<T> graphNode){
        throw new UnsupportedOperationException();
    }


    public static <T extends Comparable<T>> GraphNode<T> fuseNodes(GraphNode<T> nodeA, GraphNode<T> nodeB){

        return nodeA;
    }


    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. (This implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws an exception.)
     * The implementor must also ensure that the relation is transitive: (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0.
     * Finally, the implementor must ensure that x.compareTo(y)==0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
     * It is strongly recommended, but not strictly required that (x.compareTo(y)==0) == (x.equals(y)). Generally speaking, any class that implements the Comparable interface and violates this condition should clearly indicate this fact. The recommended language is "Note: this class has a natural ordering that is inconsistent with equals."
     * In the foregoing description, the notation sgn(expression) designates the mathematical signum function, which is defined to return one of -1, 0, or 1 according to whether the value of expression is negative, zero or positive.
     * @param graphNode the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @throws NullPointerException - if the specified object is null
     * @throws ClassCastException - if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(GraphNode<T> graphNode) {
        return nodeID.compareTo(graphNode.getNodeID());
    }

    public T getNodeID() {
        return nodeID;
    }

    public void setNodeID(T nodeID) {
        this.nodeID = nodeID;
    }

    public List<GraphNode<T>> getNodeEdges() {
        return nodeEdges;
    }

    public void setNodeEdges(List<GraphNode<T>> nodeEdges) {
        this.nodeEdges = nodeEdges;
    }

    public List<GraphNode<T>> getFuseNodes() {
        return fuseNodes;
    }

    public void setFuseNodes(List<GraphNode<T>> fuseNodes) {
        this.fuseNodes = fuseNodes;
    }
}
