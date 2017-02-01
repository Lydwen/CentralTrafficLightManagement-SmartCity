package fr.unice.polytech.al.trafficlight.central.utils;

import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;

/**
 * Created by tom on 29/01/17.
 */
public class CrossRoadConverter {
    /**
     * Convert a geolocalized crossroad to a lightweight version "crossroad"
     * @param crossroad
     *          the geolocalized crossroad we want to minify
     * @return the CrossRoad corresponding to the geolocalized crossroad in parameter
     */
    public CrossRoad geolocalizedCrossroadToCrossRoad(GeolocalizedCrossroad crossroad){
        CrossRoad crossRoad = new CrossRoad(crossroad.getName(), crossroad.getUrl(), crossroad.getScenario());

        for(TrafficLight light : crossroad.getTrafficLights()){
            crossRoad.addTrafficLight(light);
        }

        for(String road : crossroad.getRoads()){
            crossRoad.addRoad(road);
        }

        return crossRoad;
    }

    /**
     * Convert a crossroad to a lightweight version "crossroad core"
     * @param crossRoad
     *          the crossroad we want to minify
     * @return the CrossRoadCore corresponding to the crossroad in parameter
     */
    public CrossRoadCore crossRoadToCrossRoadCore(CrossRoad crossRoad){
        return new CrossRoadCore(crossRoad.getName(), crossRoad.getTrafficLights(), crossRoad.getRoads());
    }

    public CrossRoadCore geolocalizedCrossroadToCrossRoadCore(GeolocalizedCrossroad geo){
        return new CrossRoadCore(geo.getName(), geo.getTrafficLights(), geo.getRoads());
    }
}
