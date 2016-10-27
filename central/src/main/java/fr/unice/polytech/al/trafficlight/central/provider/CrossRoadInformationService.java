package fr.unice.polytech.al.trafficlight.central.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.central.data.*;
import fr.unice.polytech.al.trafficlight.central.data.TrafficLight;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom dall'agnol on 27/10/16.
 */
@Path("crossroad")
public class CrossRoadInformationService {
    private GsonBuilder builder = new GsonBuilder();

    /**
     * Retrieves all the existing crossroads
     * in the db. You can have more informations
     * about the crossroad doing a GET request
     * on the ressource crossroad/[CrossRoad name]
     *
     * @return a Response containing all the crossroads names
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCrossRoads(){
        Gson gson = builder.create();

        List<String> crossRoads = new ArrayList<>();
        crossRoads.add("carrefour_du_casino");
        crossRoads.add("carrefour_des_pins");
        return Response.ok()
                .entity(gson.toJson(crossRoads))
                .build()
                ;
    }

    @GET
    @Path("/{crossRoadName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSpecifcCrossRoad(@PathParam("crossRoadName") String crossRoadName){
        Gson gson = builder.create();
        CrossRoad crossRoad = new CrossRoad("carrefour_du_casino", "url");
        crossRoad.addRoad("avenue du tapis vert");
        crossRoad.addRoad("avenue des orangers");

        TrafficLight trafficLight1 = new TrafficLight("feu de l'avenue des orangers");
        trafficLight1.addRoad("avenue des orangers");
        TrafficLight trafficLight2 = new TrafficLight("feu de l'avenue du tapis vert");
        trafficLight1.addRoad("avenue du tapis vert");

        crossRoad.addTrafficLight(trafficLight1);
        crossRoad.addTrafficLight(trafficLight2);

        Scenario scenar = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 20);
        RuleGroup group2 = new RuleGroup("group2", 40);
        TrafficLightId id1 = new TrafficLightId("feu de l'avenue des orangers");
        TrafficLightId id2 = new TrafficLightId("feu de l'avenue du tapis vert");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);
        group2.addTrafficLight(id2);

        scenar.addRuleGroupList(0, group1);
        scenar.addRuleGroupList(1, group2);
        scenar.setTransitionTime(5);

        crossRoad.setScenario(scenar);
        return Response.ok().entity(gson.toJson(crossRoad)).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveScenario(Scenario scenario) {
        System.out.println("RECEIVE OKI");
        return null;
    }
}
