package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
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

    public String receiveCrossroad(@RequestBody CrossRoad crossroad);
}