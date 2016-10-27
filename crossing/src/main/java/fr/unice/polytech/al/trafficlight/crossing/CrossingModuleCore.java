package fr.unice.polytech.al.trafficlight.crossing;

import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
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
    public void changeScenario(Scenario newScenario) {
        System.out.println("GOOOOOOO !");
        runnable.changeScenario(newScenario);
    }
    public Scenario getActiveScenario() {
        return runnable.getActiveScenario();
    }

    @PUT
    @Path("/stopper")
    public void stopTrafficLight() {
        System.out.println("Stoooooop !");
        runnable.stopRunning();
    }

    Set<TrafficLight> getTrafficLights() {
        return trafficLightSet;
    }
}
