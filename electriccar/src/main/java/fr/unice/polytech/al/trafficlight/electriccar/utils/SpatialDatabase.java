package fr.unice.polytech.al.trafficlight.electriccar.utils;

import co.paralleluniverse.spacebase.*;
import co.paralleluniverse.spacebase.queries.RangeQuery;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;

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

    public String[] getNextTrafficLight(double longitude, double latitude, double range) {
        String[] result = new String[2];
        AABB pos = AABB.create(latitude,latitude,longitude,longitude);
        RangeQuery query = new RangeQuery(AABB.create(longitude,longitude,latitude,latitude),range );
        spatialDatabase.query(query,    new SpatialSetVisitor<SpatialTrafficLight>() {
            public void visit(Set<SpatialTrafficLight> result, Set<ElementUpdater<SpatialTrafficLight>> resForUpdate) {
                queryResult = result;
            }
        });
        SpatialTrafficLight res = null;
        for (SpatialTrafficLight t : queryResult) {
            if (res == null) {
                res = t;
            } else if (pos.distanceSquared(t.getAABB()) < pos.distanceSquared(res.getAABB())) {
                res = t;
            }
        }
        if(res == null) {
            result[0] = "None";
            result[1] = "None";
            return result;
        }
        result[0] = res.getId();
        result[1] = res.getCrossroadId();
        return result;
    }

    public void updateTrafficLight(Set<SpatialTrafficLight> trafficLights) {
        for(SpatialTrafficLight stl : trafficLights) {
            spatialDatabase.insert(stl, stl.getAABB());
        }
    }

}
