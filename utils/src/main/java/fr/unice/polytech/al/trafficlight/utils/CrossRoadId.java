package fr.unice.polytech.al.trafficlight.utils;

/**
 * Created by nathael on 27/10/16.
 */
public class CrossRoadId {
    private String id;

    public CrossRoadId(){}

    public CrossRoadId(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String)
            return id.equals((String) obj);
        else if (obj instanceof CrossRoadId) {
            return id.equals(((CrossRoadId) obj).id);
        }
        else return false;
    }

    @Override
    public String toString() {
        return "CR:"+id;
    }
}
