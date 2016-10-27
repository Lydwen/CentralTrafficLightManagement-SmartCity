package fr.unice.polytech.al.trafficlight.crossing;

import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.Set;

/**
 * Created by nathael on 27/10/16.
 */
public class CrossingModuleCore {
    private final CrossingModuleRunning runnable;
    private final Set<TrafficLight> trafficLightSet;

    public CrossingModuleCore(Set<TrafficLight> trafficLightSet) {
        this.runnable = new CrossingModuleRunning(this);
        this.trafficLightSet = trafficLightSet;
    }

    public void changeScenario(Scenario newScenario) {
        runnable.changeScenario(newScenario);
    }
    public Scenario getActiveScenario() {
        return runnable.getActiveScenario();
    }
    public void stopTrafficLight() {
        runnable.stopRunning();
    }

    public Set<TrafficLight> getTrafficLights() {
        return trafficLightSet;
    }
}
