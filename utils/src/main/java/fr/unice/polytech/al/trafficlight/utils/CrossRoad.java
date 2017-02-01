package fr.unice.polytech.al.trafficlight.utils;

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
public class CrossRoad extends CrossRoadCore{
    private String url;
    private Scenario scenario;


    public CrossRoad(String name, String url, Scenario scenario) {
        super(name, new HashSet<>(), new HashSet<>());
        this.url = url;
        this.scenario = scenario;
    }
    public CrossRoad(String name, String url) {
        this(name, url, null);
    }

    public void addTrafficLight(TrafficLight trafficLight){
        this.getTrafficLights().add(trafficLight);
    }

    public void addRoad(String road){
        this.getRoads().add(road);
    }

    public void removeTrafficLight(TrafficLight trafficLight){
        this.getTrafficLights().remove(trafficLight);
    }

    public void removeRoad(String road){
        this.getRoads().remove(road);
    }

//================ GETTERs & SETTERs ================

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

//================ HashCode & Equals ================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CrossRoad crossRoad = (CrossRoad) o;

        return url != null ? url.equals(crossRoad.url) : crossRoad.url == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
