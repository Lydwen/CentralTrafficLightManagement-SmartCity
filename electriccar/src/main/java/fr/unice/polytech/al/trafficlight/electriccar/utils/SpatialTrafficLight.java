package fr.unice.polytech.al.trafficlight.electriccar.utils;

/**
 * Created by rhoo on 01/02/17.
 */

import co.paralleluniverse.spacebase.AABB;
import co.paralleluniverse.spacebase.SpatialToken;
import static co.paralleluniverse.spacebase.AABB.X;
import static co.paralleluniverse.spacebase.AABB.Y;
import static co.paralleluniverse.spacebase.AABB.Z;

public class SpatialTrafficLight {

    private final static int MIN_X = 0;
    private final static int MAX_X = 1;
    private final static int MIN_Y = 2;
    private final static int MAX_Y = 3;

    final private String id;
    final private String crossroadId;
    private double[] location;
    private AABB aabb;
    private SpatialToken spatialToken;

    public SpatialTrafficLight(String id, String crossroad, AABB location) {
        this.id = id;
        this.crossroadId = crossroad;
        aabb = location;
        this.location = new double[location.dims()*2];
        for (int i = 0; i < location.dims(); i++) {
            this.location[i * 2] = location.min(i);
            this.location[i * 2 + 1] = location.max(i);
        }
        floatify(this.location);
    }

    public SpatialTrafficLight(String id, String crossroad, double latitude, double longitude) {
        this.id = id;
        this.crossroadId = crossroad;
        this.location = new double[4];
        this.location[MIN_X] = latitude;
        this.location[MAX_X] = latitude;
        this.location[MIN_Y] = longitude;
        this.location[MAX_Y] = longitude;
        aabb = AABB.create(latitude,latitude,longitude,longitude);
        floatify(location);
    }

    private void floatify(double[] array) {
        for(int i=0; i<array.length; i++)
            array[i] = (float)array[i];
    }

    public AABB getAABB() {
        return aabb;
    }

    public SpatialToken getSpatialToken() {
        return spatialToken;
    }

    public void setSpatialToken(SpatialToken spatialToken) {
        if(this.spatialToken != null)
            throw new RuntimeException("token for " + this + " already set!");
        this.spatialToken = spatialToken;
    }

    public String getCrossroadId() {
        return crossroadId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpatialObject@");
        sb.append(id);
        sb.append(crossroadId);
        sb.append("[" + location+"]");
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
