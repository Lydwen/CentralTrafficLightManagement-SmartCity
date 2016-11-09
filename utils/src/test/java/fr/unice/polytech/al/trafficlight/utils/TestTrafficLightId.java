package fr.unice.polytech.al.trafficlight.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nathael on 09/11/16.
 */
public class TestTrafficLightId {

    @Test
    public void testIdCompare() {
        TrafficLightId id1 = new TrafficLightId("TL1");
        TrafficLightId id2 = new TrafficLightId("TL2");
        TrafficLightId id3 = new TrafficLightId("TL1");
        String id4 = "TL1";
        String id5 = "TL3";

        Assert.assertNotEquals(id1, id2);
        Assert.assertEquals(id1, id3);
        Assert.assertEquals(id1, id4);
        Assert.assertNotEquals(id1, id5);

        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertNotEquals(id2, id4);
        Assert.assertNotEquals(id2, id5);

        Assert.assertEquals(id3, id1);
        Assert.assertNotEquals(id3, id2);
        Assert.assertEquals(id3, id4);
        Assert.assertNotEquals(id3, id5);
    }
}
