package fr.unice.polytech.al.trafficlight.central.data.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 12/01/17.
 */
public class WeightedDirectedGraphTest {

    @Test
    public void testAddNode() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        assertEquals(0, graph.getNumberOfNode());

        graph.addNode("firstNode");
        assertEquals(1, graph.getNumberOfNode());
        assertEquals(0, graph.getNumberOfNeighbours("firstNode"));
        assertEquals(0, graph.getNumberOfNeighbours("firstNode"));
        assertTrue(graph.isPresent("firstNode"));
    }

    @Test
    public void testRemoveNode() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        graph.addNode("firstNode");

        graph.removeNode("firstNode");
        assertFalse(graph.isPresent("firstNode"));
        assertEquals(0, graph.getNumberOfNode());
    }

    @Test
    public void testAddEdge() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        graph.addNode("firstNode");
        graph.addNode("endNode");
        graph.addEdge("firstNode","endNode",12);

        assertEquals(1, graph.getNumberOfNeighbours("firstNode"));
    }

    @Test
    public void testAddEdgeWithoutNode() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        graph.addEdge("nonExistingNode1","nonExistingNode2", 42);

        assertEquals(2, graph.getNumberOfNode());
        assertEquals(1, graph.getNumberOfNeighbours("nonExistingNode1"));
        assertTrue(graph.isPresent("nonExistingNode1"));
        assertTrue(graph.isPresent("nonExistingNode2"));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        graph.addNode("firstNode");
        graph.addNode("endNode");
        graph.addEdge("firstNode","endNode",12);
        //TODO remove edge
    }

    @Test
    public void testRemoveAllEdgesOfNode() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        //add 20 edges
        for(int i = 0; i<20; i++){
            graph.addEdge("firstNode","endNode",i);
        }

        assertEquals(20, graph.getNumberOfNeighbours("firstNode"));

        graph.removeAllEdgesOfNode("firstNode");
        assertEquals(0, graph.getNumberOfNeighbours("firstNode"));
        assertTrue(graph.isPresent("firstNode"));
        assertTrue(graph.isPresent("endNode"));
    }

    @Test
    public void testGetNeighbours() throws Exception {
        WeightedDirectedGraph<String> graph = new WeightedDirectedGraph<>();
        graph.addEdge("firstNode","endNode",12);

        assertEquals("endNode", graph.getNeighbours("firstNode").get(0));
        assertEquals(0, graph.getNeighbours("endNode").size());
    }
}