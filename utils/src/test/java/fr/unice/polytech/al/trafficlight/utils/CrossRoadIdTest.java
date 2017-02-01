package fr.unice.polytech.al.trafficlight.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tom on 01/02/17.
 */
public class CrossRoadIdTest {
    @Test
    public void setId() throws Exception {
        CrossRoadId id = new CrossRoadId("osef");

        id.setId("newId");
        assertEquals("newId", id.getId());
    }

    @Test
    public void getId() throws Exception {
        CrossRoadId id = new CrossRoadId("myId");
        assertEquals("myId", id.getId());
    }

    @Test
    public void equals() throws Exception {
        CrossRoadId id1 = new CrossRoadId("id1");
        CrossRoadId id2 = new CrossRoadId("id2");

        assertNotEquals(id1, id2);

        id2.setId("id1");
        assertEquals(id1,id2);

        assertEquals(id1, "id1");
        assertNotEquals(id1, "id2");
    }

}