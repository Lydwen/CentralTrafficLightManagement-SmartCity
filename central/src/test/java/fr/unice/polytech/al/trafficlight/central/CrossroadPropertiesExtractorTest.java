package fr.unice.polytech.al.trafficlight.central;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by tom on 02/02/17.
 */
public class CrossroadPropertiesExtractorTest {
    private CrossRoad crossRoad1;
    private CrossRoad crossRoad2;
    private CrossRoad crossRoad3;


    @Before
    public void init(){
        Properties properties = null;
        try {
            properties= PropertiesLoaderUtils
                    .loadProperties(new ClassPathResource("crossroadsJSON.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        crossRoad1 = gson.fromJson(properties.getProperty("crossroad1"), CrossRoad.class);
        crossRoad2 = gson.fromJson(properties.getProperty("crossroad2"), CrossRoad.class);
        crossRoad3 = gson.fromJson(properties.getProperty("crossroad3"), CrossRoad.class);
    }

    @Test
    public void basicTest(){
        assertEquals("carrefour_de_l_INRIA", crossRoad1.getName());
        assertEquals(4, crossRoad1.getRoads().size());
        assertEquals(4, crossRoad1.getTrafficLights().size());
        assertEquals("carrefour_de_CASINO", crossRoad2.getName());
        assertEquals("carrefour_de_CARREFOUR", crossRoad3.getName());
    }
}

