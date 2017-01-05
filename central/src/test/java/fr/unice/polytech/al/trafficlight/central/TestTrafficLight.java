package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rhoo on 17/11/16.
 */
public class TestTrafficLight {
    TrafficLight trafficLight1;
    TrafficLight trafficLight2;
    String name1 = "Name1";
    String name2 = "Name2";
    String road1 = "road1";
    String road2 = "road2";

    @Before
    public void init() {
        trafficLight1 = new TrafficLight(name1);
        trafficLight2 = new TrafficLight(name2);
    }

    @Test
    public void testEquals() {
        assertNotEquals(trafficLight1, trafficLight2);
        assertEquals(trafficLight1, trafficLight1);
        trafficLight2 = new TrafficLight(name1);
        assertEquals(trafficLight1, trafficLight2);
        trafficLight1 = new TrafficLight(name1);
        trafficLight1.addRoad(road1);
        assertNotEquals(trafficLight1, trafficLight2);
        trafficLight2 = new TrafficLight(name2);
        trafficLight2.addRoad(road2);
        assertNotEquals(trafficLight1, trafficLight2);
        assertEquals(trafficLight1, trafficLight1);
    }

    @Test
    public void testHash() {
        assertNotEquals(trafficLight1.hashCode(), trafficLight2.hashCode());
        assertEquals(trafficLight1.hashCode(), trafficLight1.hashCode());
        trafficLight2 = new TrafficLight(name1);
        assertEquals(trafficLight1.hashCode(), trafficLight2.hashCode());
        trafficLight1 = new TrafficLight(name1);
        trafficLight1.addRoad(road1);
        assertNotEquals(trafficLight1.hashCode(), trafficLight2.hashCode());
        trafficLight2 = new TrafficLight(name2);
        trafficLight2.addRoad(road2);
        assertNotEquals(trafficLight1.hashCode(), trafficLight2.hashCode());
        assertEquals(trafficLight1.hashCode(), trafficLight1.hashCode());
    }
}
