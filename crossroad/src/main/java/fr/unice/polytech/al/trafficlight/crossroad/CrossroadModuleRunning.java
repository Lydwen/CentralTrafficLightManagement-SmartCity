package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;

/**
 * Created by nathael on 27/10/16.
 */
class CrossroadModuleRunning implements Runnable {
    private final static Logger LOG = Logger.getLogger(CrossroadModuleRunning.class);
    private final CrossroadModuleCore crossModuleCore;
    private volatile Scenario activeScenario;
    private volatile boolean isRunning = false;

    CrossroadModuleRunning(final CrossroadModuleCore crossroadModuleCore) {
        this.crossModuleCore = crossroadModuleCore;
    }


    Scenario getActiveScenario() {
        return activeScenario;
    }

    void changeScenario(final Scenario newScenario) {
        if(!isRunning) {
            this.activeScenario = newScenario;
            LOG.debug("Scenario set to "+ newScenario);
            startRunning();
        }
        else {
            this.activeScenario = newScenario;
            LOG.debug("Scenario will be set to "+ newScenario+" after current running ruleGroup finished running");
            // Will be used after the current running rule finished running
        }
    }

    void stopRunning() {
        isRunning = false;
        LOG.debug("Running set to FALSE");
    }

    private void startRunning() {
        if(isRunning)
            throw new RuntimeException("Already running !");

        LOG.info("New CrossRoadModuleRunning started");
        isRunning = true;
        new Thread(this).start();
    }
    boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        try {
            int runningRuleIndex = 0;
            Scenario runningScenario = activeScenario;
            while(isRunning()) {
                // computing next rule to follow
                if(!runningScenario.equals(activeScenario)) {
                    // the scenario has changed !!
                    runningScenario = activeScenario;
                    runningRuleIndex = 0; // start at the beginning of the new scenario
                    LOG.debug("Scenario changed to "+ runningScenario);
                } else {
                    // continue the same scenario at next step
                    runningRuleIndex = (runningRuleIndex+1)%activeScenario.getRuleGroupList().size();
                }
                // obtaining rule to use
                RuleGroup runningRule = activeScenario.getRuleGroup(runningRuleIndex);
                LOG.debug("RuleGroup changed to "+runningRule);

                // passing traffic lights to red and wait
                redStep(activeScenario.getTransitionTime(), runningRule);

                // passing some traffic lights to green and wait
                greenStep(runningRule);
            }
        } catch(IndexOutOfBoundsException ioobe) {
            // activeScenario has no groupRules
            stopRunning();
            return; // so die.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stopRunning();
        }

        LOG.info("CrossRoadModule stopped running, thread die.");
    }

    private void greenStep(final RuleGroup currentRunningRule) throws InterruptedException {
        // set to green all traffic lights specified in rule
        crossModuleCore.getTrafficLights().forEach(trafficLight -> {
            if(currentRunningRule.getTrafficLightList().contains(trafficLight.getId()))
                trafficLight.setGreen();
        });

        // wait for step time seconds
        LOG.debug("Wait "+currentRunningRule.getGreenTime()+"s green step...");
        Thread.sleep(currentRunningRule.getGreenTime()*1000);
    }

    private void redStep(long transitionTime, RuleGroup nextRunningRule) throws InterruptedException {
        // set all traffic lights to red
        crossModuleCore.getTrafficLights().forEach(TrafficLight::setRed);

        // set to red all traffic lights not specified in the next rule to follow
        crossModuleCore.getTrafficLights().forEach(trafficLight -> {
            if(!nextRunningRule.getTrafficLightList().contains(trafficLight.getId()))
                trafficLight.setRed();
        });

        LOG.debug("Wait "+transitionTime+"s red step...");
        // wait for transition
        Thread.sleep(transitionTime*1000);
    }

    void callEmergency(TrafficLightId crossroadId, int duration) {
        LOG.debug("Call Emergency not already implemented");
        LOG.debug("(called to set "+crossroadId+" green during "+duration+"s)");

        // TODO: Stop run -NOW-

        // uncomment following when todo done
        /*
            crossModuleCore.getTrafficLightList().forEach(trafficLight -> {
                if(trafficLight.getId().equals(crossroadId)) {
                    trafficLight.setGreen();
                } else {
                    trafficLight.setRed();
                }
            });
            Thread.sleep(duration);
        */

        // TODO: Relaunch run at a red step
    }
}
