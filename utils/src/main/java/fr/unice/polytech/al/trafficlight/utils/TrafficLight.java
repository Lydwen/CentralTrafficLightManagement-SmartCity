package fr.unice.polytech.al.trafficlight.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a TrafficLight :
 * which roads does it permits to access if it
 * is green.
 *
 * Created by tom dall'agnol on 27/10/16.
 */
public class TrafficLight {
    private String name;
    private Set<String> accessibleRoads;

    public TrafficLight(String name){
        this.name = name;
        this.accessibleRoads = new HashSet<>();
    }

    public void addRoad(String road){
        this.accessibleRoads.add(road);
    }

    public void removeRoad(String road){
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

        return accessibleRoads != null ? accessibleRoads.equals(that.accessibleRoads) : that.accessibleRoads == null;

    }

    @Override
    public int hashCode() {
        return accessibleRoads != null ? accessibleRoads.hashCode() : 0;
    }
}
