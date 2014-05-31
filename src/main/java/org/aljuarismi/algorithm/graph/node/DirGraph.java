package org.aljuarismi.algorithm.graph.node;

import org.aljuarismi.algorithm.list.SortedList;
import org.aljuarismi.algorithm.sort.QuickSort;
import org.aljuarismi.algorithm.sort.RevQuickSort;

import java.util.*;

/**
 * Created by javadev on 30/05/14.
 */
public class DirGraph {



    public static<T extends Comparable<T>> void kosaraju(List<DirGraphNode<T>> inputGraph) throws Exception {

        Integer currentLabel = 0;

        Iterator<DirGraphNode<T>> iter = inputGraph.iterator();
        while(iter.hasNext()) {
            DirGraphNode<T> node = iter.next();
            if(!node.isExplored()){
                currentLabel = DirGraph.DFSGrev(inputGraph, node, currentLabel);
            }
        }

        RevQuickSort<DirGraphNode<T>> sort = new RevQuickSort<DirGraphNode<T>>();
        sort.call(inputGraph, null, null);

        // forward dfs
        // use negative logic for isExplored flag to avoid reseting all the
        // nodes in the graph
        Iterator<DirGraphNode<T>> fEdgeIter = inputGraph.iterator();
        while(fEdgeIter.hasNext()){
            DirGraphNode<T> node = fEdgeIter.next();
            if(node.isExplored()){ //if is NOT explored
                Integer leaderSortNumber = node.getTopologicalSortNumber();
                DirGraph.kosarajuDFS(inputGraph, node, leaderSortNumber);
            }

        }
    }

    public static<T extends Comparable<T>> void  kosarajuDFS(List<DirGraphNode<T>> graph, DirGraphNode<T> startVertex, Integer leaderSortNumber){

        startVertex.setExplored(false);
        startVertex.setLeaderSortNumber(leaderSortNumber);

        Iterator<DirGraphNode<T>> fEdgeIter = startVertex.getForwardsNodeEdges().iterator();

        while( fEdgeIter.hasNext()){
            DirGraphNode<T> nodeV = fEdgeIter.next();
            if(nodeV.isExplored()){
                DirGraph.kosarajuDFS(graph, nodeV, leaderSortNumber);
            }
        }

    }


    public static<T extends Comparable<T>> Integer  DFS(List<DirGraphNode<T>> graph, DirGraphNode<T> startVertex, Integer aioCurrentLabel){

        Iterator<DirGraphNode<T>> fEdgeIter = startVertex.getForwardsNodeEdges().iterator();

        while( fEdgeIter.hasNext()){
            DirGraphNode<T> nodeV = fEdgeIter.next();
            if(!nodeV.isExplored()){
                nodeV.setExplored(true);
                DirGraph.DFS(graph, nodeV, aioCurrentLabel);
            }
        }
        startVertex.setTopologicalSortNumber(aioCurrentLabel);
        aioCurrentLabel--;
        return aioCurrentLabel;
    }

    public static<T extends Comparable<T>> Integer DFSGrev(List<DirGraphNode<T>> graph, DirGraphNode<T> startVertex, Integer aioCurrentLabel){

        startVertex.setExplored(true);

        Iterator<DirGraphNode<T>> fEdgeIter = startVertex.getBackwardsNodeEdges().iterator();

        while( fEdgeIter.hasNext()){
            DirGraphNode<T> nodeV = fEdgeIter.next();
            if(!nodeV.isExplored()){
                aioCurrentLabel = DirGraph.DFSGrev(graph, nodeV, aioCurrentLabel);
            }
        }
        startVertex.setTopologicalSortNumber(aioCurrentLabel);
        aioCurrentLabel++;
        return aioCurrentLabel;

    }



    public static<T extends Comparable<T>> Map<Integer, Integer> getSCCVolumes(List<DirGraphNode<T>> graph){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<Integer,Integer> sorted_map = new TreeMap<Integer,Integer>( bvc);

        Iterator<DirGraphNode<T>> nodeIter = graph.iterator();

        while( nodeIter.hasNext()){
            DirGraphNode<T> nodeV = nodeIter.next();
            if(map.get(nodeV.getLeaderSortNumber().intValue())==null){
                map.put(nodeV.getLeaderSortNumber().intValue(),1);
            }
            else{
                map.put(nodeV.getLeaderSortNumber().intValue(), map.get(nodeV.getLeaderSortNumber().intValue())+1);
            }
        }

        //Sort the map
        sorted_map.putAll(map);
        //return new HashMap<Integer, Integer>(sorted_map);
        return sorted_map;
    }


    static class ValueComparator implements Comparator<Integer> {

        Map<Integer, Integer> base;

        ValueComparator(HashMap<Integer, Integer> base) {
            this.base = base;
        }

        @Override
        public int compare(Integer a, Integer b) {
            if (a.equals(b)) return 0;
            if (base.get(a) >= base.get(b)) {
                return 1;
            } else return -1;
        }
    }

}
