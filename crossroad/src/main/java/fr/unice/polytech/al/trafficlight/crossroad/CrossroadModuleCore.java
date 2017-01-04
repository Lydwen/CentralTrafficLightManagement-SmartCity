package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import fr.unice.polytech.al.trafficlight.utils.WaitingVehiclesMap;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nathael on 27/10/16.
 */
public class CrossroadModuleCore {
    private final static Logger LOG = Logger.getLogger(CrossroadModuleCore.class);

    final CrossroadModuleRunning runnable;
    private final Set<TrafficLight> trafficLightSet;

    public CrossroadModuleCore() throws IOException {
        LOG.debug("new CrossRoadModuleCore created");

        List<String> lines = Files.readAllLines(new File(
                getClass().getClassLoader().getResource("config/trafficLight.config").getFile()).toPath());

        String json = "";
        for(String line : lines)
            json += line;

        LOG.debug("Json= "+json);

        trafficLightSet = new HashSet<>();
        Gson gson = new Gson();
        Set trafficLightIds = gson.fromJson(json, Set.class);
        for(Object trafficLightId : trafficLightIds) {
            trafficLightSet.add(new TrafficLight(gson.fromJson(trafficLightId.toString(), TrafficLightId.class)));
        }
        LOG.debug("TrafficLightSet: "+trafficLightSet);

        this.runnable = new CrossroadModuleRunning(this);
    }


    Scenario getActiveScenario() {
        return runnable.getActiveScenario();
    }

    Set<TrafficLight> getTrafficLights() {
        return trafficLightSet;
    }

    void changeScenario(Scenario newScenario) {
        runnable.changeScenario(newScenario);
    }

    boolean isRunning() {
        return runnable.isRunning();
    }

    void stopRunning() {
        runnable.stopRunning();
    }

    void callEmergency(Emergency emergency) {
        if(emergency.getDuration() <= 0) {
            LOG.error("Illegal Emergency: "+emergency);
            LOG.error("Refuse to execute emergency with time null or negative");
        }
        else {
            runnable.callEmergency(emergency);
        }
    }

    void setNbWaitingCars(WaitingVehiclesMap map) {
        map.entrySet().stream()
                .filter(entry -> getActiveScenario().usesTrafficLight(entry.getKey()))
                .forEach(entry -> runnable.setNbWaitingCars(entry.getKey(), entry.getValue()));
    }
}
