package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.utils.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tom on 01/02/17.
 */
public class CoordinatesTest {
    private Coordinates coordinates;

    @Before
    public void begin(){
        coordinates=new Coordinates(42.42,13.37);
    }

    @Test
    public void testSetCoordinates(){
        coordinates.setLatitude(12.12);
        assertTrue(12.12 == coordinates.getLatitude());

        coordinates.setLongitude(13.13);
        assertTrue(13.13 == coordinates.getLongitude());
    }

    @Test
    public void testEquals(){
        Coordinates coordinates2 = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude());
        assertEquals(coordinates,coordinates2);

        coordinates2.setLatitude(21.21);
        assertNotEquals(coordinates,coordinates2);

        coordinates2.setLatitude(coordinates.getLatitude());
        coordinates2.setLongitude(12.12);
        assertNotEquals(coordinates,coordinates2);
    }
}