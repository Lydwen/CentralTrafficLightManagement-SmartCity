package fr.unice.polytech.al.trafficlight.utils.enums;

/**
 * Created by nathael on 27/10/16.
 */
public class TrafficLightId {
    private final String id;

    public TrafficLightId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String)
            return id.equals((String) obj);
        else if (obj instanceof TrafficLightId) {
            return id.equals(((TrafficLightId) obj).id);
        }
        else return false;
    }
}
