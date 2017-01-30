package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rhoo on 14/11/16.
 */

@Service
public class ScenarioCheckerImpl implements ScenarioChecker {

    @Autowired
    private WebRequester requester;

    @Autowired
    private DatabaseDao database;

    /**
     * Check the validity of a Scenario against a CrossRoad object
     * @param scenario
     *              the scenario that we want to check the validity
     * @return "OK" if no problem, a String describing the error otherwise
     */
    public String checkScenario(Scenario scenario){
        List<RuleGroup> ruleList = scenario.getRuleGroupList();
        Set<TrafficLightId> trafficLightList = new HashSet<>();

        //retrieve all the traffic lights used in the scenario
        for(RuleGroup r : ruleList) {
            trafficLightList.addAll(r.getTrafficLights());
        }

        //there is an error if they all the traffic lights are green at the same time
        for(RuleGroup r : ruleList) {
            if (trafficLightList.size() == r.getTrafficLights().size()) {
                return "All trafficLight green at same time";
            }
        }

        return "OK";
    }

    /**
     * Check the validity of a Scenario against a CrossRoad object,
     * and if it's valid it will put the scenario into the crossroad object
     * @param scenario
     *              the scenario that we want to check the validity
     * @param crossRoad
     *              the crossroad on which we'll test the scenario
     * @return "OK" if no problem, a String describing the error otherwise
     */
    public String checkAndSetScenario(Scenario scenario, GeolocalizedCrossroad crossRoad){
        String result = checkScenario(scenario);
        //if the Scenario is ok
        if(result.equals("OK")){
            //change the scenario in the db
            crossRoad.setScenario(scenario);
            database.addScenario(scenario);
            //send the change to the crossroad
            requester.put(crossRoad.getName(), "/crossroad/starter", scenario);
        }
        return result;
    }


    /**
     * Check the validity of a Scenario against a crossroad named crossRoadName,
     * and if it's valid it will put the scenario into the crossroad object
     * @param scenario
     *              the scenario that we want to check the validity
     * @param crossRoadName
     *              the name of thencrossroad on which we'll test the scenario
     * @return "OK" if no problem, a String describing the error otherwise
     */
    public String checkAndSetScenario(Scenario scenario, String crossRoadName){
        GeolocalizedCrossroad crossroad = database.getCrossroad(crossRoadName);
        //if the crossroad doesn't exist we can't continue
        if(crossroad==null){
            return "The specified crossroad name doesn't exist : "+crossRoadName;
        }
        return this.checkAndSetScenario(scenario, crossroad);
    }
}
