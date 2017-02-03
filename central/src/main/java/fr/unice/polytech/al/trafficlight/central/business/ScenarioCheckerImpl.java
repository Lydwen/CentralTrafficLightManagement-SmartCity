package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.graph.Edge;
import fr.unice.polytech.al.trafficlight.utils.*;
import fr.unice.polytech.al.trafficlight.central.utils.WebRequester;
import org.apache.log4j.Logger;
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

    private final static Logger LOG = Logger.getLogger(ScenarioCheckerImpl.class);

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

    public String checkScenario(Scenario scenario, GeolocalizedCrossroad crossroad){
        String result = checkScenario(scenario);

        if(result.equals("OK")){
            //check if there isn't any wrong traffic light in a scenario
            if(!checkTrafficLightsOfScenario(crossroad, scenario)){
                return "traffic lights in the scenario not ok";
            }
        }
        return result;
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
        String result = checkScenario(scenario, crossRoad);

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

    @Override
    public String checkAndSetScenarioAndSpread(Scenario scenario, String idCrossRoad, String road) {
        //retrieve the path to go from the begin to the last crossroad on a road
        List<Edge<GeolocalizedCrossroad>> crossroads = database.getAllCrossroadLinkedWithRoadFrom(idCrossRoad, road);
        if(crossroads.isEmpty()) {
            return "The specified crossroad name doesn't exist : " + idCrossRoad;
        }

        this.propagateScenario(crossroads, scenario);

        return "OK";
    }

    private void propagateScenario(List<Edge<GeolocalizedCrossroad>> crossroads, Scenario scenario){
        //if the first scenario is ok, we continue
        if(this.checkAndSetScenario(scenario, crossroads.get(0).getBegin()).equals("OK")) {
            for (Edge<GeolocalizedCrossroad> edge : crossroads) {
                //set each end crossroad
                if (this.checkAndSetScenario(scenario, edge.getEnd()).equals("OK")) {
                    //say synchronize from the edge value
                    int value = edge.getWeight();
                }
            }
        }
    }


    /**
     * Check if there is a traffic light in the new scenario
     * that doesn't exist in the real crossroad
     * @param crossRoad
     *          the crossroad that has the real trafficlights
     * @param scenario
     *          the scenario that we want to test
     * @return true if all the traffic lights are in the crossroad, false otherwise
     */
    private boolean checkTrafficLightsOfScenario(GeolocalizedCrossroad crossRoad, Scenario scenario) {
        Set<TrafficLight> trafficLights = crossRoad.getTrafficLights();
        Set<TrafficLightId> trafficLightIds = new HashSet<>();
        for(RuleGroup ruleGroup: scenario.getRuleGroupList()){
            trafficLightIds.addAll(ruleGroup.getTrafficLights());
        }
        for(TrafficLight trafficLight : trafficLights){
            if(!trafficLightIds.contains(new TrafficLightId(trafficLight.getName()))){
                return false;
            }
        }

        return trafficLightIds.size() == trafficLights.size();
    }
}
