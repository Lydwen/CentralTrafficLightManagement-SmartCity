package fr.unice.polytech.al.trafficlight.central.data.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representation of a weighted directed graph.
 * e.g. :
 *          A -> B -> C <- D
 *            3    2     7
 *
 * Each edge of the graph has a direction and a weight associated.
 * The node type is T : we can use anything as a node.
 *
 * Created by tom dall'agnol on 25/12/16.
 */
public class WeightedDirectedGraph<T> {
    //for each node T, we can have many different edges that exit from it
    //e.g. : A -> B we'll have for the node A the edge that go to B
    private Map<T, List<Edge<T>>> outGraph;
    //for each node T, we can have many different edges that enters in it
    //e.g. : A -> B we'll have for the node B the edge that enter in it
    private Map<T, List<Edge<T>>> inGraph;

    public WeightedDirectedGraph(){
        this.outGraph = new HashMap<>();
        this.inGraph = new HashMap<>();
    }

    /**
     * Add a node into the graph
     * @param node
     *          the node we want to add
     */
    public void addNode(T node){
        if(!isPresent(node)){
            this.outGraph.put(node, new ArrayList<>());
            this.inGraph.put(node, new ArrayList<>());
        }
    }

    /**
     * Remove a node from the graph
     * @param node
     *              the node we want to remove from the graph
     */
    public void removeNode(T node){
        //remove all edges that have a link with this node
        this.removeAllEdgesOfNode(node);

        this.outGraph.remove(node);
        this.inGraph.remove(node);
    }

    /**
     * Check if the node is present in the graph
     * @param node
     *          the node we want to check if it's in the graph
     * @return true if the node is already in the graph, false otherwise
     */
    public boolean isPresent(T node){
        return this.outGraph.containsKey(node);
    }

    /**
     * Add an edge into the graph betwen the nodes begin
     * and end, with a specific weight.
     *
     * If the two nodes are not already in the graph,
     * they are created.
     *
     * @param begin
     *          the begin node
     * @param end
     *          the end node
     * @param weight
     *          the weight we want to put for this specific edge
     */
    public void addEdge(T begin, T end, int weight){
        addNode(begin);
        addNode(end);

        Edge<T> edge = new Edge<>(begin, end, weight);

        this.outGraph.get(begin).add(edge);
        this.inGraph.get(end).add(edge);
    }

    /**
     * Remove an edge from the graph
     *
     * @param edge
     *          the edge we want to remove
     */
    public void removeEdge(Edge<T> edge){
        T begin = edge.getBegin();
        T end = edge.getEnd();
        if(isPresent(begin) && isPresent(end)){
            this.outGraph.get(begin).remove(edge);
            this.inGraph.get(end).remove(edge);
        }
    }

    /**
     * Remove all edges that are linked to a specific node
     * @param node
     *          the node that we want to remove all the edges
     */
    public void removeAllEdgesOfNode(T node){
        this.outGraph.get(node).forEach(this::removeEdge);

        this.inGraph.get(node).forEach(this::removeEdge);
    }

    /**
     * Retrieve all the nodes that we can access
     * from the specific node
     *
     * @param node
     *          the node that we want the neighbours
     * @return a List of all the nodes that are accessible by the entry node
     */
    public List<T> getNeighbours(T node){
        return this.outGraph.get(node).stream().map(Edge<T>::getEnd).collect(Collectors.toList());
    }
}
