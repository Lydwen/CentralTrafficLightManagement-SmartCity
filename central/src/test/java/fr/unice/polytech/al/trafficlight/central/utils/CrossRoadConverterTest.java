package fr.unice.polytech.al.trafficlight.central.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by tom on 03/02/17.
 */
public class CrossRoadConverterTest {
    private CrossRoadConverter crossRoadConverter;

    private GeolocalizedCrossroad crossRoad1;
    private GeolocalizedCrossroad crossRoad2;

    @Before
    public void init(){
        crossRoadConverter = new CrossRoadConverter();

        Properties properties = null;
        try {
            properties= PropertiesLoaderUtils
                    .loadProperties(new ClassPathResource("geoCrossroads.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        crossRoad1 = gson.fromJson(properties.getProperty("carrefour_de_l_INRIA"), GeolocalizedCrossroad.class);
        crossRoad2 = gson.fromJson(properties.getProperty("carrefour_de_CASINO"), GeolocalizedCrossroad.class);
    }

    @Test
    public void geolocalizedCrossroadToCrossRoad() throws Exception {
        CrossRoad crossRoadWanted = new CrossRoad(crossRoad1.getName(), crossRoad1.getUrl(), crossRoad1.getScenario());
        for(TrafficLight light : crossRoad1.getTrafficLights()){
            crossRoadWanted.addTrafficLight(light);
        }

        for(String road : crossRoad1.getRoads()){
            crossRoadWanted.addRoad(road);
        }


        CrossRoad crossRoadConverted = crossRoadConverter.geolocalizedCrossroadToCrossRoad(crossRoad1);
        assertEquals(crossRoadWanted,crossRoadConverted);
    }

    @Test
    public void geolocalizedCrossroadToCrossRoadCore() throws Exception {
        CrossRoadCore crossRoadWanted = new CrossRoadCore(crossRoad1.getName(),
                crossRoad1.getTrafficLights(),crossRoad1.getRoads());

        assertEquals(crossRoadWanted, crossRoadConverter.geolocalizedCrossroadToCrossRoadCore(crossRoad1));
    }

    @Test
    public void crossRoadToCrossRoadCore() throws Exception {
        CrossRoad crossRoadBase = crossRoadConverter.geolocalizedCrossroadToCrossRoad(crossRoad1);
        CrossRoadCore crossRoadWanted = crossRoadConverter.geolocalizedCrossroadToCrossRoadCore(crossRoad1);

        assertEquals(crossRoadWanted, crossRoadConverter.crossRoadToCrossRoadCore(crossRoadBase));
    }

}