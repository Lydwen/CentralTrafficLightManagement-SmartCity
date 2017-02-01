package fr.unice.polytech.al.trafficlight.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a TrafficLight :
 * which roads does it permits to access if it
 * is green.
 * <p>
 * Created by tom dall'agnol on 27/10/16.
 */
public class TrafficLight {
    private String name;
    private Set<String> accessibleRoads;
    private Coordinates coordinates;

    public TrafficLight(String name) {
        this(name, new Coordinates(0,0));
    }

    public TrafficLight(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.accessibleRoads = new HashSet<>();
    }

    public void addRoad(String road) {
        this.accessibleRoads.add(road);
    }

    public void removeRoad(String road) {
        this.accessibleRoads.remove(road);
    }

    //================ GETTERs & SETTERs ================

    public Set<String> getAccessibleRoads() {
        return accessibleRoads;
    }

    public void setAccessibleRoads(Set<String> accessibleRoads) {
        this.accessibleRoads = accessibleRoads;
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

        TrafficLight that = (TrafficLight) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (accessibleRoads != null ? !accessibleRoads.equals(that.accessibleRoads) : that.accessibleRoads != null)
            return false;
        return coordinates != null ? coordinates.equals(that.coordinates) : that.coordinates == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (accessibleRoads != null ? accessibleRoads.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }
}
