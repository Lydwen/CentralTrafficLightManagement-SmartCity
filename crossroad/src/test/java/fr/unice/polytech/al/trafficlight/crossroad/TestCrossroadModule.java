package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by nathael on 09/11/16.
 */
public class TestCrossroadModule {
    private final static Logger LOG = Logger.getLogger(TestCrossroadModule.class);

    // Much larger => Test more precise but much longer
    private static final int LOOP_NUMBER = 1;

    private CrossroadModuleCore module = new CrossroadModuleCore();
    private Scenario scenario = new Scenario("scenario test");
    private RuleGroup group1 = new RuleGroup("group1", 5);
    private RuleGroup group2 = new RuleGroup("group2", 7);
    private RuleGroup group3 = new RuleGroup("group3", 5);
    private RuleGroup group4 = new RuleGroup("group4", 6);

    @Before
    public void begin() {
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
        scenario.setTransitionTime(2);
    }

    @Test
    public void test1() throws InterruptedException {
        // Check before scenario state
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.isDisabled());
        }
        assertNull(module.getActiveScenario());

        // Launch scenario
        module.changeScenario(new Gson().toJson(scenario));
        LOG.debug("TEST>START");

        LOG.debug("TEST>Wait 1s (Group1, Red Step, 1s/2s)");
        sleep(1);
        // Check all trafficLights are red and not disabled at start
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());
            assertFalse(tl.isGreen());
        }

        for(int i=0; i<LOOP_NUMBER; i++) {
            LOG.debug("TEST>Wait 2s (Group1, Green Step, 1s/5s)");
            sleep(2);
            checkTrafficLightStep(group1);

            LOG.debug("TEST>Wait 3s (Group1, Green Step, 4s/5s)");
            sleep(3);
            checkTrafficLightStep(group1);

            LOG.debug("TEST>Wait 2s (Group2, Red Step, 1s/2s)");
            sleep(2);
            checkTrafficLightRed(group1, group2);

            LOG.debug("TEST>Wait 2s (Group2, Green Step, 1s/7s)");
            sleep(2);
            checkTrafficLightStep(group2);

            LOG.debug("TEST>Wait 5s (Group2, Green Step, 6s/7s)");
            sleep(5);
            checkTrafficLightStep(group2);

            LOG.debug("TEST>Wait 2s (Group3, Red Step, 1s/2s)");
            sleep(2);
            checkTrafficLightRed(group2, group3);

            LOG.debug("TEST>Wait 2s (Group3, Green Step, 1s/5s)");
            sleep(2);
            checkTrafficLightStep(group3);

            LOG.debug("TEST>Wait 3s (Group3, Green Step, 4s/5s)");
            sleep(3);
            checkTrafficLightStep(group3);

            LOG.debug("TEST>Wait 2s (Group4, Red Step, 1s/2s)");
            sleep(2);
            checkTrafficLightRed(group3, group4);

            LOG.debug("TEST>Wait 2s (Group4, Green Step, 1s/6s)");
            sleep(2);
            checkTrafficLightStep(group4);

            LOG.debug("TEST>Wait 4s (Group4, Green Step, 5s/6s)");
            sleep(4);
            checkTrafficLightStep(group4);

            LOG.debug("TEST>Wait 2s (Group1, Red Step, 1s/2s)");
            sleep(2);
            checkTrafficLightRed(group4, group1);
        }

        module.stopTrafficLight(); // should stop after group1 green step
        sleep(10); // 10s (sufficient delay for any rule + red step time)

        assertFalse(module.runnable.isRunning());
        // Check all trafficLights are disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertTrue(tl.toString(), tl.isDisabled());
        }
    }

    private void checkTrafficLightRed(RuleGroup lastRule, RuleGroup nextRule) {
        // Check all trafficLights are red and not disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());

            // if the trafficlight was green and should be green at next green step
            // so shouldnt be set to green during red step
            if(lastRule.getTrafficLights().contains(tl.getId())
                    && nextRule.getTrafficLights().contains(tl.getId())) {
                assertTrue(tl.toString(), tl.isGreen()); // should be green
            } else {
                assertFalse(tl.toString(), tl.isGreen()); // should be red
            }
        }
    }
    private void checkTrafficLightStep(RuleGroup rule) {
        // Check all trafficLights are red and not disabled
        for(TrafficLight tl : module.getTrafficLights()) {
            assertFalse(tl.isDisabled());

            if(rule.getTrafficLights().contains(tl.getId())) {
                assertTrue(tl.toString(), tl.isGreen()); // should be green
            } else {
                assertFalse(tl.toString(), tl.isGreen()); // should be red
            }
        }
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
