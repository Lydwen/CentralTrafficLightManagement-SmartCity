package fr.unice.polytech.al.trafficlight.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 12/01/17.
 */
public class EdgeTest {
    @Test
    public void testGetters(){
        Edge<String> edge = new Edge<>("edge","beginNode","endNode", 42);

        assertEquals("beginNode", edge.getBegin());
        assertEquals("endNode", edge.getEnd());
        assertEquals("edge", edge.getName());
        assertEquals(42, edge.getWeight());
    }

    @Test
    public void testSetters(){
        Edge<String> edge = new Edge<>("osef edge","no one care", "no one care", -12);

        edge.setBegin("new begin");
        assertEquals("new begin", edge.getBegin());

        edge.setEnd("new end");
        assertEquals("new end", edge.getEnd());

        edge.setName("not osef");
        assertEquals("not osef", edge.getName());

        edge.setWeight(23);
        assertEquals(23, edge.getWeight());
    }

    @Test
    public void testEquals(){
        Edge<String> edge = new Edge<>("edge","beginNode","endNode", 42);
        Edge<String> edgeClone = new Edge<>("edge","beginNode","endNode", 42);

        assertEquals("the edge must be equals to himself",
                edge, edge);
        assertEquals("two edges are equals if they have the same begin node, end node, and weight",
                edge, edgeClone);
    }

    @Test
    public void testNotEquals(){
        Edge<String> edge = new Edge<>("edge","beginNode","endNode", 42);
        Edge<String> edgeClone = new Edge<>("edge","beginNode","endNode", 42);

        edge.setBegin("new node");
        assertNotEquals(edgeClone, edge);

        edge.setBegin("beginNode");
        assertEquals(edgeClone, edge);

        edge.setEnd("new node");
        assertNotEquals(edgeClone, edge);

        edge.setEnd("endNode");
        assertEquals(edgeClone, edge);

        edge.setWeight(12);
        assertNotEquals(edgeClone, edge);

        edge.setWeight(42);
        assertEquals(edgeClone, edge);

        edge.setName("not edge name");
        assertNotEquals(edgeClone, edge);
    }

}