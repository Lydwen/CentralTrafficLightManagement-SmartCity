package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rhoo on 17/11/16.
 */

public class TestCrossRoad {

    CrossRoad crossRoad1;
    CrossRoad crossRoad2;
    String name1 = "Name1";
    String name2 = "Name2";
    String url1 = "url1";
    String url2 = "url2";
    Scenario scenario1 = new Scenario("Scenar1");
    Scenario scenario2 = new Scenario("Scenar2");

    @Before
    public void init() {
        crossRoad1 = new CrossRoad(name1, url1);
        crossRoad2 = new CrossRoad(name2, url2);
    }

    @Test
    public void testEquals() {
        assertNotEquals(crossRoad1, crossRoad2);
        assertEquals(crossRoad1, crossRoad1);
        crossRoad2 = new CrossRoad(name1, url1);
        assertEquals(crossRoad1, crossRoad2);
        crossRoad1 = new CrossRoad(name1, url1, scenario1);
        assertNotEquals(crossRoad1, crossRoad2);
        crossRoad2 = new CrossRoad(name2, url2, scenario2);
        assertNotEquals(crossRoad1, crossRoad2);
        assertEquals(crossRoad1, crossRoad1);
        crossRoad2 = new CrossRoad(name1, url1, scenario2);
        assertNotEquals(crossRoad1, crossRoad2);
    }

    @Test
    public void testHash() {
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        assertEquals(crossRoad1.hashCode(), crossRoad1.hashCode());
        crossRoad2 = new CrossRoad(name1, url1);
        assertEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        crossRoad1 = new CrossRoad(name1, url1, scenario1);
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        crossRoad2 = new CrossRoad(name2, url2, scenario2);
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
        assertEquals(crossRoad1.hashCode(), crossRoad1.hashCode());
        crossRoad2 = new CrossRoad(name1, url1, scenario2);
        assertNotEquals(crossRoad1.hashCode(), crossRoad2.hashCode());
    }
}
