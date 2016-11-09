package fr.unice.polytech.al.trafficlight.utils;

/**
 * Created by nathael on 27/10/16.
 */
public class TrafficLightId {
    private String id;

    public TrafficLightId(){}

    public TrafficLightId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "TL:"+id;
    }
}
