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

    final int id;
    double[] location;
    AABB aabb;
    private SpatialToken spatialToken;

    public SpatialTrafficLight(AABB location) {
        this(-1, location);
    }

    public SpatialTrafficLight(int id, AABB location) {
        this.id = id;
        aabb = location;
        this.location = new double[location.dims()*2];
        for (int i = 0; i < location.dims(); i++) {
            this.location[i * 2] = location.min(i);
            this.location[i * 2 + 1] = location.max(i);
        }
        floatify(this.location);
    }

    public SpatialTrafficLight(double minX, double maxX, double minY, double maxY) {
        this(-1, minX, maxX, minY, maxY);
    }

    public SpatialTrafficLight(int id, double minX, double maxX, double minY, double maxY) {
        this.id = id;
        this.location = new double[4];
        this.location[MIN_X] = minX;
        this.location[MAX_X] = maxX;
        this.location[MIN_Y] = minY;
        this.location[MAX_Y] = maxY;
        aabb = AABB.create(minX,maxX,minY,maxY);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpatialObject@");
        sb.append(id > 0 ? id : Integer.toHexString(System.identityHashCode(this)));
        sb.append("[" + location+"]");
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
