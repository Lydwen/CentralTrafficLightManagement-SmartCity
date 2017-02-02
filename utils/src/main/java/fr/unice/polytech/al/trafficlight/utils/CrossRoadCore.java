package fr.unice.polytech.al.trafficlight.utils;

import java.util.Set;

/**
 * Class that represents an abridged crossroad,
 * only containing the basic info about it.
 *
 * Created by tom on 30/01/17.
 */
public class CrossRoadCore {
    private String name;
    private Set<TrafficLight> trafficLights;
    private Set<String> roads;

    public CrossRoadCore(String name, Set<TrafficLight> trafficLights, Set<String> roads) {
        this.name = name;
        this.trafficLights = trafficLights;
        this.roads = roads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public void setTrafficLights(Set<TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
    }

    public Set<String> getRoads() {
        return roads;
    }

    public void setRoads(Set<String> roads) {
        this.roads = roads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrossRoadCore that = (CrossRoadCore) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (trafficLights != null ? !trafficLights.equals(that.trafficLights) : that.trafficLights != null)
            return false;
        return roads != null ? roads.equals(that.roads) : that.roads == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (trafficLights != null ? trafficLights.hashCode() : 0);
        result = 31 * result + (roads != null ? roads.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
