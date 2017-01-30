package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a CrossRoad : its name, what are the
 * traffic lights in it, what are the different axes,
 * the address to communicate with it and the current
 * scenario for it. We also have its geographic position
 *
 * Created by tom dall'agnol on 27/10/16.
 */
public class GeolocalizedCrossroad {
    private String name;
    private String url;
    private Scenario scenario;
    private Set<TrafficLight> trafficLights;
    private Set<String> roads;
    private Coordinates coordinates;

    public GeolocalizedCrossroad(String name, String url, Scenario scenario, Coordinates coordinates) {
        this.name = name;
        this.url = url;
        this.scenario = scenario;
        this.trafficLights = new HashSet<>();
        this.roads = new HashSet<>();
        this.coordinates = coordinates;
    }
    public GeolocalizedCrossroad(String name, String url) {
        this.name = name;
        this.url = url;
        this.scenario = null;
        this.trafficLights = new HashSet<>();
        this.roads = new HashSet<>();
        this.coordinates = new Coordinates(0,0);
    }

    public GeolocalizedCrossroad(){}

    public void addTrafficLight(TrafficLight trafficLight){
        this.trafficLights.add(trafficLight);
    }

    public void addRoad(String road){
        this.roads.add(road);
    }

    public void removeTrafficLight(TrafficLight trafficLight){
        this.trafficLights.remove(trafficLight);
    }

    public void removeRoad(String road){
        this.roads.remove(road);
    }

//================ GETTERs & SETTERs ================

    public Set<String> getRoads() {
        return roads;
    }

    public void setRoads(Set<String> roads) {
        this.roads = roads;
    }

    public Set<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public void setTrafficLights(Set<TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    //================ HashCode & Equals ================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeolocalizedCrossroad crossroad = (GeolocalizedCrossroad) o;

        if (!name.equals(crossroad.name)) return false;
        if (url != null ? !url.equals(crossroad.url) : crossroad.url != null) return false;
        if (scenario != null ? !scenario.equals(crossroad.scenario) : crossroad.scenario != null) return false;
        if (trafficLights != null ? !trafficLights.equals(crossroad.trafficLights) : crossroad.trafficLights != null)
            return false;
        if (roads != null ? !roads.equals(crossroad.roads) : crossroad.roads != null) return false;
        return coordinates != null ? coordinates.equals(crossroad.coordinates) : crossroad.coordinates == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (scenario != null ? scenario.hashCode() : 0);
        result = 31 * result + (trafficLights != null ? trafficLights.hashCode() : 0);
        result = 31 * result + (roads != null ? roads.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }
}
