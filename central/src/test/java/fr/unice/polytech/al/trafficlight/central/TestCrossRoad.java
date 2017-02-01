package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rhoo on 17/11/16.
 */

public class TestCrossRoad {

    protected CrossRoad crossRoad1;
    protected CrossRoad crossRoad2;
    protected String name1 = "Name1";
    protected String name2 = "Name2";
    protected String url1 = "url1";
    protected String url2 = "url2";
    protected Scenario scenario1;
    protected Scenario scenario2;

    @Before
    public void init() {
        crossRoad1 = new CrossRoad(name1, url1);
        crossRoad2 = new CrossRoad(name2, url2);
        scenario1 = new Scenario("Scenar1");
        scenario2 = new Scenario("Scenar2");
    }

    @Test
    public void testGetters(){
        assertEquals(name1, crossRoad1.getName());
        assertEquals(url1, crossRoad1.getUrl());

        crossRoad1.setScenario(scenario1);
        assertEquals(scenario1, crossRoad1.getScenario());
    }

    @Test
    public void testAddAndRemoveTrafficLight(){
        assertEquals(0,crossRoad1.getTrafficLights().size());
        TrafficLight trafficLight = new TrafficLight("myTrafficLight");
        TrafficLight trafficLight2 = new TrafficLight("anotherTrafficLight");

        crossRoad1.addTrafficLight(trafficLight);
        assertEquals(1, crossRoad1.getTrafficLights().size());

        crossRoad1.addTrafficLight(trafficLight);
        assertEquals("Adding the same traffic light will do nothing",
                1, crossRoad1.getTrafficLights().size());

        crossRoad1.addTrafficLight(trafficLight2);
        assertEquals(2, crossRoad1.getTrafficLights().size());

        crossRoad1.removeTrafficLight(trafficLight);
        assertEquals(1, crossRoad1.getTrafficLights().size());

        crossRoad1.removeTrafficLight(trafficLight);
        assertEquals("removing a traffic light that isn't in the object does nothing",
                1, crossRoad1.getTrafficLights().size());
    }

    @Test
    public void testAddAndRemoveRoad(){
        assertEquals(0,crossRoad1.getRoads().size());
        String road = "myRoad";
        String road2 = "anotherRoad";

        crossRoad1.addRoad(road);
        assertEquals(1, crossRoad1.getRoads().size());

        crossRoad1.addRoad(road);
        assertEquals("Adding the same road will do nothing",
                1, crossRoad1.getRoads().size());

        crossRoad1.addRoad(road2);
        assertEquals(2, crossRoad1.getRoads().size());

        crossRoad1.removeRoad(road2);
        assertEquals(1, crossRoad1.getRoads().size());

        crossRoad1.removeRoad(road2);
        assertEquals("removing a road that isn't in the crossroad does nothing",
                1, crossRoad1.getRoads().size());
    }

    @Test
    public void testEquals() {
        assertEquals(crossRoad1, crossRoad1);

        assertNotEquals(crossRoad1, crossRoad2);
        assertEquals(crossRoad1, crossRoad1);
        crossRoad2.setName(name1);
        crossRoad2.setUrl(url1);
        assertEquals(crossRoad1, crossRoad2);
        crossRoad1.setScenario(scenario1);
        assertEquals(crossRoad1, crossRoad2);
        crossRoad2.setName(name2);
        crossRoad2.setUrl(url2);
        crossRoad2.setScenario(scenario2);
        assertNotEquals(crossRoad1, crossRoad2);
        crossRoad2.setName(name1);
        crossRoad2.setUrl(url1);
        crossRoad2.setScenario(scenario2);
        assertEquals(crossRoad1, crossRoad2);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        assertEquals(crossRoad1.hashCode(), crossRoad1.hashCode());
        crossRoad2.setName(name1);
        crossRoad2.setUrl(url1);
        crossRoad2.setScenario(scenario1);
        assertEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        crossRoad2.setScenario(scenario2);
        assertEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        crossRoad2.setName(name2);
        crossRoad2.setUrl(url2);
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        crossRoad2.setName(name1);
        crossRoad2.setUrl(url1);
        crossRoad2.setScenario(scenario2);
        assertEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
    }
}
