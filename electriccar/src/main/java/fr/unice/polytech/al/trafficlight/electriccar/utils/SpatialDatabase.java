package fr.unice.polytech.al.trafficlight.electriccar.utils;

import co.paralleluniverse.spacebase.*;
import co.paralleluniverse.spacebase.queries.RangeQuery;

import java.util.Set;

/**
 * Created by rhoo on 27/01/17.
 */
public class SpatialDatabase {

    SpaceBase<SpatialTrafficLight> spatialDatabase;
    static Set<SpatialTrafficLight> queryResult;

    public SpatialDatabase() {
        spatialDatabase = new SpaceBaseBuilder().setDimensions(2).build("Town-1");
    }

    public Set<SpatialTrafficLight> getTrafficLightAround(double longitude, double latitude, double range) {
        RangeQuery query = new RangeQuery(AABB.create(longitude,longitude,latitude,latitude),range );
        spatialDatabase.query(query,    new SpatialSetVisitor<SpatialTrafficLight>() {
            public void visit(Set<SpatialTrafficLight> result, Set<ElementUpdater<SpatialTrafficLight>> resForUpdate) {
                queryResult = result;
            }
        });
        return queryResult;
    }
}
