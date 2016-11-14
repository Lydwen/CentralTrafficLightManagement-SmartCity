package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.RuleGroup;

import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by nathael on 10/11/16.
 */
public class UtilsForTests {

    static void checkTrafficLightRed(Set<TrafficLight> trafficLightSet, RuleGroup lastRule, RuleGroup nextRule) {
        // Check all trafficLights are red and not disabled
        for(TrafficLight tl : trafficLightSet) {
            assertFalse(tl.isDisabled());

            // if the trafficLight was green and should be green at next green step
            // so shouldn't be set to green during red step
            if(lastRule.getTrafficLights().contains(tl.getId())
                    && nextRule.getTrafficLights().contains(tl.getId())) {
                assertTrue(trafficLightSet+":"+tl+" should be green", tl.isGreen()); // should be green
            } else {
                assertFalse(trafficLightSet+":"+tl+" should be red", tl.isGreen()); // should be red
            }
        }
    }
    static void checkTrafficLightStep(Set<TrafficLight> trafficLightSet, RuleGroup rule) {
        // Check all trafficLights are red and not disabled
        for(TrafficLight tl : trafficLightSet) {
            assertFalse(tl.isDisabled());

            if(rule.getTrafficLights().contains(tl.getId())) {
                assertTrue(trafficLightSet+":"+tl+" should be green", tl.isGreen()); // should be green
            } else {
                assertFalse(trafficLightSet+":"+tl+" should be red", tl.isGreen()); // should be red
            }
        }
    }

    static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
