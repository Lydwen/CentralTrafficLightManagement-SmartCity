package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.central.TestCrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Coordinates;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tom on 01/02/17.
 */
public class GeolocalizedCrossroadTest extends TestCrossRoad{
    private GeolocalizedCrossroad crossroad;
    private GeolocalizedCrossroad crossroadClone;

    @Before
    @Override
    public void init(){
        this.crossRoad1 = new GeolocalizedCrossroad(name1,url1);
        this.crossRoad2 = new GeolocalizedCrossroad(name2,url2);

        crossroad = new GeolocalizedCrossroad("myGeoCrossRoad", "url");
        crossroadClone = new GeolocalizedCrossroad("myGeoCrossRoad", "url");
    }

    @Test
    public void getCoordinates() throws Exception {
        assertEquals(new Coordinates(0,0), crossroad.getCoordinates());

        Coordinates coo2 = new Coordinates(21,14);
        crossroad = new GeolocalizedCrossroad("myGeoCrossRoad","url",new Scenario(), coo2);

        assertEquals(coo2, crossroad.getCoordinates());
    }

    @Test
    public void testEquals() {
        super.testEquals();

        assertEquals(crossroad,crossroad);

        Coordinates coo2 = new Coordinates(21,14);
        crossroadClone.setCoordinates(coo2);
        assertNotEquals(crossroad,crossroadClone);
    }

    @Test
    public void testHashCode() {
        super.testHashCode();

        crossroadClone.setCoordinates(new Coordinates(12,12));
        assertNotEquals(crossroad, crossroadClone);
        crossroad.setCoordinates(
                new Coordinates(
                        crossroadClone.getCoordinates().getLatitude(),
                        crossroadClone.getCoordinates().getLongitude()));

        assertEquals(crossroad, crossroadClone);
    }

}