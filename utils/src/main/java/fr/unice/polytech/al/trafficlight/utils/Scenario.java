package fr.unice.polytech.al.trafficlight.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathael on 27/10/16.
 */
public class Scenario {
    private final String id;
    private final List<RuleGroup> ruleGroupList;
    private int transitionTime; // time in s between each group green time

    public Scenario(String id) {
        this.id = id;
        this.ruleGroupList = new ArrayList<>();
        this.transitionTime = 0;
    }

    //  //  //  //  //   GET   //   //  //  //  //

    /**
     * @return Transition time in ms
     */
    public int getTransitionTime() {
        return transitionTime;
    }

    /**
     * @return the list of RulesGroup of this scenario
     */
    public List<RuleGroup> getRuleGroupList() {
        return new ArrayList<>(ruleGroupList);
    }

    /**
     * @param ruleGroup The ruleGroup to get the step index
     * @return The step index of the ruleGroup send into parameters
     *          Return -1 if ruleGroup not in this scenario
     */
    public int getRuleGroupStepNumber(RuleGroup ruleGroup) {
        return this.ruleGroupList.indexOf(ruleGroup);
    }

    /**
     * @param stepNumber The step number for the needed rule group
     * @return The ruleGroup that was at the specified step
     *          Return null if there is no step of the given number
     * @throws IndexOutOfBoundsException if stepNumber < 0 or if this scenario has no rules
     */
    public RuleGroup getRuleGroup(int stepNumber) throws IndexOutOfBoundsException {
        if(stepNumber > ruleGroupList.size() -1 && !ruleGroupList.isEmpty())
            return null;
        return this.ruleGroupList.get(stepNumber);
    }

    /**
     * @return Id of the Scenario
     */
    public String getId() {
        return id;
    }

    /**
     * @return The time to do a complete scenario loop in s (including transition times)
     */
    public int getTotalScenarioTime() {
        int time = 0;
        for(RuleGroup rg : ruleGroupList) {
            time += rg.getGreenTime() + transitionTime;
        }

        return time;
    }

    //  //  //  //  //   SET   //   //  //  //  //


    public void setTransitionTime(int transitionTime) {
        this.transitionTime = transitionTime;
    }

    public void addRuleGroup(int stepNumber, RuleGroup ruleGroup) {
        this.ruleGroupList.add(stepNumber, ruleGroup);
    }
    public void addRuleGroup(RuleGroup ruleGroup) {
        this.ruleGroupList.add(ruleGroup);
    }

    @Override
    public String toString() {
        return "Sc:"+id+":"+transitionTime+":"+ruleGroupList;
    }
}
