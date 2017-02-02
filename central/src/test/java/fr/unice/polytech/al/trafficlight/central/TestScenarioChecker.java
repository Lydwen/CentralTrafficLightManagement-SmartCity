package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.central.business.ScenarioChecker;
import fr.unice.polytech.al.trafficlight.central.data.GeolocalizedCrossroad;
import fr.unice.polytech.al.trafficlight.utils.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rhoo on 14/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class TestScenarioChecker {

    @Autowired
    private ScenarioChecker checker;

    private Scenario ScenarioTrue;
    private Scenario ScenarioFalse;
    private Scenario ScenarioFalseTrafficLight;
    private GeolocalizedCrossroad crossroad;
    private String falseCrossroadName = "00000";
    @Before
    public void init() {
        ScenarioTrue = new Scenario("basicScenario");
        RuleGroup group1 = new RuleGroup("group1", 20,20);
        RuleGroup group2 = new RuleGroup("group2", 40,40);
        TrafficLightId id1 = new TrafficLightId("feu de l'avenue des orangers");
        TrafficLightId id2 = new TrafficLightId("feu de l'avenue du tapis vert");
        group1.addTrafficLight(id1);
        group2.addTrafficLight(id2);

        ScenarioTrue.addRuleGroup(0, group1);
        ScenarioTrue.addRuleGroup(1, group2);
        ScenarioTrue.setTransitionTime(5);

        ScenarioFalse = new Scenario("basicScenario");
        group1 = new RuleGroup("group1", 20,20);
        group2 = new RuleGroup("group2", 40,20);
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);
        group2.addTrafficLight(id2);

        ScenarioFalse.addRuleGroup(0, group1);
        ScenarioFalse.addRuleGroup(1, group2);
        ScenarioFalse.setTransitionTime(5);

        ScenarioFalseTrafficLight = new Scenario("basicScenario");
        group1 = new RuleGroup("group1", 20,20);
        group2 = new RuleGroup("group2", 40,40);
        id1 = new TrafficLightId("feu de l'avenue des orangers");
        id2 = new TrafficLightId("feu de l'avenue du tapis vert");
        group1.addTrafficLight(id1);
        group2.addTrafficLight(id2);
        group2.addTrafficLight(new TrafficLightId("an id that doesnt exist"));

        ScenarioFalseTrafficLight.addRuleGroup(0, group1);
        ScenarioFalseTrafficLight.addRuleGroup(1, group2);
        ScenarioFalseTrafficLight.setTransitionTime(5);

        crossroad = new GeolocalizedCrossroad("crossroad_test", "url",ScenarioTrue,new Coordinates(0,0));
        crossroad.addTrafficLight(new TrafficLight(id1.getId()));
        crossroad.addTrafficLight(new TrafficLight(id2.getId()));
    }
    @Test
    public void testScenario() {
        assertEquals("OK",checker.checkScenario(ScenarioTrue));
        assertEquals("All trafficLight green at same time",checker.checkScenario(ScenarioFalse));
    }

    @Test
    public void testCheckAndSet() {
        assertEquals("The specified crossroad name doesn't exist : " + falseCrossroadName, checker.checkAndSetScenario(ScenarioTrue,falseCrossroadName));
    }

    @Test
    public void testCheckWithATrafficLightThatDoesntExist() {
        assertEquals("traffic lights in the scenario not ok", checker.checkScenario(ScenarioFalseTrafficLight, crossroad));
    }

    @Test
    public void testCheckOkWithACrossroad() {
        for(RuleGroup group : ScenarioTrue.getRuleGroupList()){
            for(TrafficLightId light : group.getTrafficLights()){
                System.out.println("id : "+light.getId());
            }
        }
        System.out.printf("====================");
        for(TrafficLight light : crossroad.getTrafficLights()){
            System.out.println("id : "+light.getName());
        }
        System.out.println(ScenarioTrue.getRuleGroupList().get(0).getTrafficLights());
        assertEquals("OK", checker.checkScenario(ScenarioTrue, crossroad));
    }

}
