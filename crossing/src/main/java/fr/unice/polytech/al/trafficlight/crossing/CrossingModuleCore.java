package fr.unice.polytech.al.trafficlight.crossing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nathael on 27/10/16.
 */
@Path("crossingmodule")
public class CrossingModuleCore {
    private final CrossingModuleRunning runnable;
    private final Set<TrafficLight> trafficLightSet;

    public CrossingModuleCore(/*Set<TrafficLight> trafficLightSet*/) {
        // ↓ TODO: this is a bad mock of traffic light disposing
        trafficLightSet = new HashSet<>();
        trafficLightSet.add(new TrafficLight(new TrafficLightId("north")));
        trafficLightSet.add(new TrafficLight(new TrafficLightId("south")));
        trafficLightSet.add(new TrafficLight(new TrafficLightId("east")));
        trafficLightSet.add(new TrafficLight(new TrafficLightId("west")));
        // ↑ TODO: end of mock

        this.runnable = new CrossingModuleRunning(this);
        //this.trafficLightSet = trafficLightSet;
    }

    @PUT
    @Path("/starter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeScenario(Scenario newScenario) {
        Gson gson = new GsonBuilder().create();
        System.out.println("GOOOOOOO !");
        runnable.changeScenario(newScenario);
        return Response.ok().entity(gson.toJson(newScenario)).build();
    }
    public Scenario getActiveScenario() {
        return runnable.getActiveScenario();
    }

    @PUT
    @Path("/stopper")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopTrafficLight() {
        System.out.println("Stoooooop !");
        runnable.stopRunning();
        return Response.ok().build();
    }

    Set<TrafficLight> getTrafficLights() {
        return trafficLightSet;
    }
}
