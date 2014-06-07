package org.aljuarismi.algorithm.graph.node;

public class Edge<T>{
    public DNode<T> destination;
    public long distance;

    public Edge(DNode<T> destination, long distance) {
        this.destination = destination;
        this.distance = distance;
    }
}
