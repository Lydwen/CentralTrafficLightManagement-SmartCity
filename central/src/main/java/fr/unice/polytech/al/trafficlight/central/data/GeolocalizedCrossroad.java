package fr.unice.polytech.al.trafficlight.central.data;

import fr.unice.polytech.al.trafficlight.utils.Coordinates;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.HashSet;

/**
 * Represents a CrossRoad : its name, what are the
 * traffic lights in it, what are the different axes,
 * the address to communicate with it and the current
 * scenario for it. We also have its geographic position
 *
 * Created by tom dall'agnol on 27/10/16.
 */
public class GeolocalizedCrossroad extends CrossRoad{
    private Coordinates coordinates;

    public GeolocalizedCrossroad(String name, String url, Scenario scenario, Coordinates coordinates) {
        super(name, url, scenario);

        this.setRoads(new HashSet<>());
        this.setTrafficLights(new HashSet<>());
        this.coordinates = coordinates;
    }
    public GeolocalizedCrossroad(String name, String url) {
        this(name, url, null,new Coordinates(0,0));
    }

//================ GETTERs & SETTERs ================

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
        if (!super.equals(o)) return false;

        GeolocalizedCrossroad crossroad = (GeolocalizedCrossroad) o;

        return coordinates.equals(crossroad.coordinates);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + coordinates.hashCode();
        return result;
    }
}
