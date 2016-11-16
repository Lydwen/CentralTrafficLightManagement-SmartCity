package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.central.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.central.utils.WebRequesterImpl;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rhoo on 14/11/16.
 */

@Service ("ScenarioChecker")
public class ScenarioCheckerImpl implements ScenarioChecker {

    @Autowired
    private WebRequesterImpl requester;

    @Autowired
    private DatabaseDao database;

    public String checkScenario(Scenario scenario) {

        List<RuleGroup> ruleList = scenario.getRuleGroupList();
        Set<TrafficLightId> trafficLightList = new HashSet<>();

        for(RuleGroup r : ruleList) {
            trafficLightList.addAll(r.getTrafficLights());
        }

        for(RuleGroup r : ruleList) {
            if (trafficLightList.size() == r.getTrafficLights().size()) {
                return "All trafficLight green at same time";
            }
        }

        database.addScenario(scenario);
        String URI = requester.target("crossroads", "/crossroad", "INRIA","/starter");
        requester.put(URI, scenario);
        return "OK";
    }
}
