package fr.unice.polytech.al.trafficlight.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by nathael on 04/11/16.
 */
public class TestScenario {

    @Test
    public void testTotalScenarioTime() {
        Scenario s = new Scenario("ScenarioTest");

        assertEquals(0, s.getTotalScenarioTime());

        s.setTransitionTime(4);
        assertEquals(0, s.getTotalScenarioTime());

        s.addRuleGroup(new RuleGroup("RuleGroupTest1", 13, 13));
        assertEquals(17, s.getTotalScenarioTime());

        s.addRuleGroup(new RuleGroup("RuleGroupTest2", 17, 17));
        assertEquals(38, s.getTotalScenarioTime());

        s.addRuleGroup(0, new RuleGroup("RuleGroupTest0", 9, 9));
        assertEquals(51, s.getTotalScenarioTime());
    }
}
