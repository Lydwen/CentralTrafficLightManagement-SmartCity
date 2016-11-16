package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.al.trafficlight.crossroad.UtilsForTests.*;
import static junit.framework.TestCase.*;

/**
 * Created by nathael on 09/11/16.
 */
public class TestCrossroadModule {
    private final static Logger LOG = Logger.getLogger(TestCrossroadModule.class);

    /**
     * Much larger => Test for normal scenario more precise but longer too
     * LOOP_NUMBER +1 => test during + ~30s
     */
    private static final int LOOP_NUMBER = 2; // 3 => ~1min

    private CrossroadModuleCore module;
    private Scenario scenario = new Scenario("scenario test");
    private RuleGroup group1 = new RuleGroup("group1", 2);
    private RuleGroup group2 = new RuleGroup("group2", 3);
    private RuleGroup group3 = new RuleGroup("group3", 4);
    private RuleGroup group4 = new RuleGroup("group4", 5);

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

        group3.addTrafficLight(id1);
        group3.addTrafficLight(id4);

        group4.addTrafficLight(id3);
        group4.addTrafficLight(id4);

        scenario.addRuleGroup(group1);
        scenario.addRuleGroup(group2);
        scenario.addRuleGroup(group3);
        scenario.addRuleGroup(group4);
        scenario.setTransitionTime(1);
    }

    /**
     * Test that traffic lights are at correct colors
     * at right time (+/- 1s precision) during a normal scenario
     * @throws InterruptedException
     */
    @Test
    public void testNormal() throws InterruptedException {
        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(new Gson().toJson(scenario));
        LOG.debug("TEST>START");

        LOG.debug("TEST>Wait 0.5s (Group1, Red Step, 0.5s/1s)");
        sleep(.5);
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        for(int i=0; i<LOOP_NUMBER; i++) {
            LOG.debug("TEST>Wait 1s (Group1, Green Step, 0.5s/2s)");
            sleep(1);
            checkTrafficLightStep(module.getTrafficLights(), group1);

            LOG.debug("TEST>Wait 4s (Group1, Green Step, 1.5s/2s)");
            sleep(1);
            checkTrafficLightStep(module.getTrafficLights(), group1);

            LOG.debug("TEST>Wait 1s (Group2, Red Step, 0.5s/1s)");
            sleep(1);
            checkTrafficLightRed(module.getTrafficLights(), group1, group2);

            LOG.debug("TEST>Wait 2s (Group2, Green Step, 0.5s/3s)");
            sleep(1);
            
            LOG.debug("TEST>Wait 2s (Group2, Green Step, 2.5s/3s)");
            sleep(2);
            checkTrafficLightStep(module.getTrafficLights(), group2);

            LOG.debug("TEST>Wait 1s (Group3, Red Step, 0.5s/1s)");
            sleep(1);
            checkTrafficLightRed(module.getTrafficLights(), group2, group3);

            LOG.debug("TEST>Wait 1s (Group3, Green Step, 0.5s/4s)");
            sleep(1);
            checkTrafficLightStep(module.getTrafficLights(), group3);

            LOG.debug("TEST>Wait 3s (Group3, Green Step, 3.5s/4s)");
            sleep(3);
            checkTrafficLightStep(module.getTrafficLights(), group3);

            LOG.debug("TEST>Wait 1s (Group4, Red Step, 0.5s/1s)");
            sleep(1);
            checkTrafficLightRed(module.getTrafficLights(), group3, group4);

            LOG.debug("TEST>Wait 1s (Group4, Green Step, 0.5s/5s)");
            sleep(1);
            checkTrafficLightStep(module.getTrafficLights(), group4);

            LOG.debug("TEST>Wait 4s (Group4, Green Step, 4.5s/5s)");
            sleep(4);
            checkTrafficLightStep(module.getTrafficLights(), group4);

            LOG.debug("TEST>Wait 1s (Group1, Red Step, 0.5s/1s)");
            sleep(1);
            checkTrafficLightRed(module.getTrafficLights(), group4, group1);
        }

        module.stopTrafficLight(); // should stop after group1 green step
        sleep(7); // 7s (sufficient delay for any rule + red step time)

        assertFalse(module.runnable.isRunning());
        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.toString(), tl.isDisabled());
        }
    }

    /**
     * Test that traffic lights scenario stops when called to
     * and don't do crazy things if was already stopped
     * @throws InterruptedException
     */
    @Test
    public void testStopWhileGreen() throws InterruptedException {
        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(new Gson().toJson(scenario));
        LOG.debug("TEST>START");

        LOG.debug("TEST>Wait 1s (Group1, Red Step, 0.5s/1s)");
        sleep(.5);
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        // go to green step normally before force stop
        LOG.debug("TEST>Wait 1.5s (Group1, Green Step, 1s/2s)");
        sleep(1.5);
        checkTrafficLightStep(module.getTrafficLights(), group1);

        // Calling to stop while green
        module.stopTrafficLight(); // should stop after group1 green step

        // Wait for green step to continue normally
        LOG.debug("TEST>Wait 0.5s (Group1, Green Step, 1.5s/2s)");
        sleep(.5);
        checkTrafficLightStep(module.getTrafficLights(), group1);

        // Wait for green to end; traffic lights should be disabled after
        LOG.debug("TEST>Wait 1s (0.5s after Group1 Green Step)");
        sleep(1);
        assertFalse(module.runnable.isRunning());

        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.toString(), tl.isDisabled());
        }
    }

    /**
     * Test that traffic lights scenario stops when called to
     * and don't do crazy things if was already stopped
     * @throws InterruptedException
     */
    @Test
    public void testStopWhileRed() throws InterruptedException {
        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(new Gson().toJson(scenario));
        LOG.debug("TEST>START");

        LOG.debug("TEST>Wait 0.5s (Group1, Red Step, 0.5s/1s)");
        sleep(.5);
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        // doing green step normally
        LOG.debug("TEST>Wait 1s (Group1, Green Step, 0.5s/2s)");
        sleep(1);
        checkTrafficLightStep(module.getTrafficLights(), group1);

        LOG.debug("TEST>Wait 1s (Group1, Green Step, 1.5s/2s)");
        sleep(1);
        checkTrafficLightStep(module.getTrafficLights(), group1);

        // Traffic lights should be disabled while red step
        LOG.debug("TEST>Wait 0.8s (Group2, Red Step, 0.3s/1s)");
        sleep(.8);
        checkTrafficLightRed(module.getTrafficLights(), group1, group2);

        // Calling to stop while red
        module.stopTrafficLight(); // should stop after group2 green step

        // Traffic light should not be stopped now
        LOG.debug("TEST>Wait 0.4s (Group2, Red Step, 0.7s/1s)");
        sleep(.4);
        checkTrafficLightRed(module.getTrafficLights(), group1, group2);

        // Traffic light should not be stopped now
        LOG.debug("TEST>Wait 0.8s (Group2, Green Step, .5s/3s)");
        sleep(.8);
        checkTrafficLightStep(module.getTrafficLights(), group2);

        LOG.debug("TEST>Wait 3s (.5s after Group2 Green Step)");
        sleep(3);
        // Now should be stopped
        assertFalse(module.runnable.isRunning());

        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.toString(), tl.isDisabled());
        }
    }

}
