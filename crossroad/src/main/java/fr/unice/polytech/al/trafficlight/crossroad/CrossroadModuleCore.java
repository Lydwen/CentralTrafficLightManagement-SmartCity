package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void stopRunning() {
        runnable.stopRunning();
    }

    public void callEmergency(Emergency emergency) {
        runnable.callEmergency(emergency);
    }
}
