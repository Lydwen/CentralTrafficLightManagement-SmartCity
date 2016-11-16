package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a CrossRoad : its name, what are the
 * traffic lights in it, what are the different axes,
 * the address to communicate with it and the current
 * scenario for it
 *
 * Created by tom dall'agnol on 27/10/16.
 */
public class CrossRoad {
    private String name;
    private String url;
    private Scenario scenario;
    private Set<TrafficLight> trafficLights;
    private Set<String> roads;

    public CrossRoad(String name, String url, Scenario scenario) {
        this.name = name;
        this.url = url;
        this.scenario = scenario;
        this.trafficLights = new HashSet<>();
        this.roads = new HashSet<>();
    }
    public CrossRoad(String name, String url) {
        this.name = name;
        this.url = url;
        this.scenario = null;
        this.trafficLights = new HashSet<>();
        this.roads = new HashSet<>();
    }

    public CrossRoad(){}

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

//================ HashCode & Equals ================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrossRoad crossRoad = (CrossRoad) o;

        if (!name.equals(crossRoad.name)) return false;
        if (!url.equals(crossRoad.url)) return false;
        if (scenario != null ? !scenario.equals(crossRoad.scenario) : crossRoad.scenario != null) return false;
        if (trafficLights != null ? !trafficLights.equals(crossRoad.trafficLights) : crossRoad.trafficLights != null)
            return false;
        return roads != null ? roads.equals(crossRoad.roads) : crossRoad.roads == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + (scenario != null ? scenario.hashCode() : 0);
        result = 31 * result + (trafficLights != null ? trafficLights.hashCode() : 0);
        result = 31 * result + (roads != null ? roads.hashCode() : 0);
        return result;
    }
}
