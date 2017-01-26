package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.al.trafficlight.crossroad.UtilsForTests.checkTrafficLightRed;
import static fr.unice.polytech.al.trafficlight.crossroad.UtilsForTests.checkTrafficLightStep;
import static fr.unice.polytech.al.trafficlight.crossroad.UtilsForTests.sleep;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by nathael on 10/11/16.
 */
public class TestEmergencyCall {
    private final static Logger LOG = Logger.getLogger(TestEmergencyCall.class);

    private CrossroadModuleCore module;
    private Scenario scenario = new Scenario("scenario test");
    private RuleGroup group1 = new RuleGroup("group1", 4, 4);
    private RuleGroup group2 = new RuleGroup("group2", 5, 5);

    // Emergency call and the equivalent ruleGroup
    private Emergency emergencyCall = new Emergency();
    private RuleGroup emergencyRule = new RuleGroup("emergency", 3, 3);

    @Before
    public void begin() throws IOException {
        module = new CrossroadModuleCore();

        TrafficLightId id1 = new TrafficLightId("north");
        TrafficLightId id2 = new TrafficLightId("south");
        TrafficLightId id3 = new TrafficLightId("east");
        TrafficLightId id4 = new TrafficLightId("west");
        group1.addTrafficLight(id1);
        group1.addTrafficLight(id2);

        group2.addTrafficLight(id2);
        group2.addTrafficLight(id3);

        scenario.addRuleGroup(group1);
        scenario.addRuleGroup(group2);
        scenario.setTransitionTime(2);

        emergencyCall.setTrafficLightId(id4);
        emergencyRule.addTrafficLight(id4);
        emergencyCall.setDuration(3); // 3seconds green duration
    }

    /**
     * Test that traffic lights are at correct colors
     * at right time (+/- 1s precision) while an emergency
     * call occurs while running on a red step
     * @throws InterruptedException
     */
    @Test
    public void testEmergencyWhileRed() throws InterruptedException {

        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(scenario);
        LOG.debug("TEST>START");

        sleep(1);
        LOG.debug("TEST>Wait 1s (Group1, Red Step, 1s/2s)");
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        // doing green step normally
        sleep(4);
        LOG.debug("TEST>Wait 4s (Group1, Green Step, 3s/4s)");
        checkTrafficLightStep(module.getTrafficLights(), group1);

        // Wait for green to end; traffic lights should be disabled after
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/2s)");
        checkTrafficLightRed(module.getTrafficLights(), group1, group2);

        // Calling to emergency while red
        module.callEmergency(emergencyCall);

        // When called while trafficLights red, emergency call should
        // immediately call the red step of emergency state.
        sleep(1);
        LOG.debug("TEST>Wait 1s (Emergency, Red Step, 1s/2s)");
        checkTrafficLightRed(module.getTrafficLights(), group2, emergencyRule);

        // Should now be emergency step
        sleep(2);
        LOG.debug("TEST>Wait 2s (Emergency, Green Step, 1s/3s)");
        checkTrafficLightStep(module.getTrafficLights(), emergencyRule);

        sleep(1);
        LOG.debug("TEST>Wait 1s (Emergency, Green Step, 2s/3s)");
        checkTrafficLightStep(module.getTrafficLights(), emergencyRule);

        // Emergency finished, return to last running rule = group2
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/2s)");
        checkTrafficLightRed(module.getTrafficLights(), emergencyRule, group2);

        // Group 2 running well ?
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/4s)");
        checkTrafficLightStep(module.getTrafficLights(), group2);

        // Stopping
        module.stopRunning(); // should stop after (2nd) group1 red step
        sleep(5);
        LOG.debug("TEST>Wait 5s (1s after Group2 Green Step)");
        assertFalse(module.getRunnable().isRunning());

        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(module.getTrafficLights()+":"+tl.toString()+" should be disabled", tl.isDisabled());
        }

    }

    /**
     * Test that traffic lights are at correct colors
     * at right time (+/- 1s precision) while an emergency
     * call occurs while running on a rule green step
     * @throws InterruptedException
     */
    @Test
    public void testEmergencyWhileGreen() throws InterruptedException {

        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(scenario);
        LOG.debug("TEST>START");

        sleep(1);
        LOG.debug("TEST>Wait 1s (Group1, Red Step, 1s/2s)");
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        // starting green step
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group1, Green Step, 1s/4s)");
        checkTrafficLightStep(module.getTrafficLights(), group1);

        // Calling to emergency while green
        module.callEmergency(emergencyCall);

        // When called while trafficLights red, emergency call should
        // immediately call the red step of emergency state.
        sleep(1);
        LOG.debug("TEST>Wait 1s (Emergency, Red Step, 1s/2s)");
        checkTrafficLightRed(module.getTrafficLights(), group2, emergencyRule);

        // Should now be emergency step
        sleep(2);
        LOG.debug("TEST>Wait 2s (Emergency, Green Step, 1s/3s)");
        checkTrafficLightStep(module.getTrafficLights(), emergencyRule);

        sleep(1);
        LOG.debug("TEST>Wait 1s (Emergency, Green Step, 2s/3s)");
        checkTrafficLightStep(module.getTrafficLights(), emergencyRule);

        // Emergency finished, return to following step of last running step = group2 red step
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/2s)");
        checkTrafficLightRed(module.getTrafficLights(), emergencyRule, group2);

        // Group 2 running well ?
        sleep(2);
        LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/4s)");
        checkTrafficLightStep(module.getTrafficLights(), group2);

        // Stopping
        module.stopRunning(); // should stop after (2nd) group1 red step
        sleep(5);
        LOG.debug("TEST>Wait 5s (1s after Group2 Green Step)");
        assertFalse(module.getRunnable().isRunning());

        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(module.getTrafficLights()+":"+tl.toString()+" should be disabled", tl.isDisabled());
        }

    }

}
