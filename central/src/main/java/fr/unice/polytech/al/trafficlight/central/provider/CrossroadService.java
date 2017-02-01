package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by rhoo on 16/11/16.
 */
public interface CrossroadService {

    public List<String> retrieveCrossRoads();

    public @ResponseBody CrossRoad retrieveSpecificCrossRoad(@PathVariable String crossRoadName);

    public String receiveCrossroad(@RequestBody GeolocalizedCrossroad crossroad);

    public Scenario retrieveScenarioCrossroad(@PathVariable String crossRoadName);
}
