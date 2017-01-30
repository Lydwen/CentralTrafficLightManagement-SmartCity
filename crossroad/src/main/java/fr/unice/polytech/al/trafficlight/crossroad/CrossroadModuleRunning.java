package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.SynchronizeMessage;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

/**
 * Created by nathael on 27/10/16.
 */
class CrossroadModuleRunning implements Runnable {
    private final static Logger LOG = Logger.getLogger(CrossroadModuleRunning.class);
    private final CrossroadModuleCore crossModuleCore;
    private volatile Scenario activeScenario;
    private volatile SynchronizeMessage synchronizedTime = null;

    private volatile boolean isRunning = false;

    private final Stack<Emergency> emergenciesStack = new Stack<>();

    CrossroadModuleRunning(final CrossroadModuleCore crossroadModuleCore) {
        this.crossModuleCore = crossroadModuleCore;
    }

    Scenario getActiveScenario() {
        return activeScenario;
    }

    void changeScenario(final Scenario newScenario) {
        if(!isRunning) {
            this.activeScenario = newScenario;
            LOG.info("Scenario set to "+ newScenario);
            startRunning();
        }
        else {
            this.activeScenario = newScenario;
            LOG.info("Scenario will be set to "+ newScenario+" after current running ruleGroup finished running");
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

        LOG.debug("CrossRoadModuleRunning started");
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
            Scenario runningScenario = null;
            while(isRunning()) {
                // computing next rule to follow
                if(!activeScenario.equals(runningScenario)) {
                    // the scenario has changed !!
                    runningScenario = activeScenario;
                    runningRuleIndex = 0; // start at the beginning of the new scenario
                    LOG.info("Scenario changed to "+ runningScenario);
                } else {
                    // continue the same scenario at next step
                    runningRuleIndex = (runningRuleIndex+1)%activeScenario.getRuleGroupList().size();
                }
                // obtaining rule to use
                RuleGroup runningRule = activeScenario.getRuleGroup(runningRuleIndex);
                LOG.debug("RuleGroup changed to "+runningRule);


                // /// RED STEP /// //

                // passing traffic lights to red and wait
                redStep(activeScenario.getTransitionTime(), runningRule);


                // /// GREEN STEP /// //

                // count how many electric cars are waiting at green states traffic lights
                int nbElecCar = 0;
                for(TrafficLight tl : crossModuleCore.getTrafficLights()) {
                    nbElecCar += tl.getElectricVehicle();
                    LOG.debug("TrafficLight: " + tl.getId().getId() + " have " + tl.getElectricVehicle() + " electric vehicle");
                }

                // count how many we can reduce scenario late at maximum
                int maxModulation = (runningRule.getNormalGreenTime()-runningRule.getMinimumGreenTime())/(nbElecCar+1);

                // count current scenario late
                int currentLate = getCurrentLate(runningScenario, runningRuleIndex);

                // we can't reduce greenTime more than maxModulation
                if(currentLate > maxModulation)
                    currentLate = maxModulation;

                // passing some traffic lights to green and wait
                greenStep(runningRule.getNormalGreenTime()-currentLate, runningRule);
            }
        } catch(IndexOutOfBoundsException ignored) {
            // activeScenario has no groupRules
            LOG.error("Active Scenario has no group Rules to use.", ignored);
        } catch (InterruptedException e) {
            LOG.error("CrossroadModule was interrupted", e);
        }

        stopRunning();
        disableTrafficLights();
        LOG.info("CrossRoadModule stopped running, thread die.");
    }

    /**
     * @param runningScenario the Running scenario
     * @param runningRuleIndex the index of current active rule of the scenario
     * @return Current scenario late time in seconds
     */
    private int getCurrentLate(Scenario runningScenario, int runningRuleIndex) {
        int late = 0;
        int scenarTime = runningScenario.getTotalScenarioTime();
        if(synchronizedTime != null) {
            int now = (int)(Calendar.getInstance().getTimeInMillis()/1000)+runningScenario.getTotalScenarioTimeAfterRule(runningRuleIndex);
            int synchTime = (int)(synchronizedTime.getDate().getTime()/1000);

            // have the date just before the current scenario loop
            int toAdd = ((now - synchTime)/scenarTime);
            late = -(synchTime + scenarTime*toAdd);
            LOG.debug("Computed late: "+late);
        }

        return late==0?scenarTime:late;
    }

    private void greenStep(int greenTime, final RuleGroup currentRunningRule) throws InterruptedException {
        // set to green all traffic lights specified in rule
        crossModuleCore.getTrafficLights().forEach(trafficLight -> {
            if(currentRunningRule.getTrafficLights().contains(trafficLight.getId()))
                trafficLight.setGreen();
        });

        // wait for step time seconds
        LOG.debug("Wait "+greenTime+"s green step...");
        try {
            synchronized (emergenciesStack) {
                if(greenTime > 0) {
                    emergenciesStack.wait(currentRunningRule.getNormalGreenTime() * 1000L);
                }
            }

            // Now is when waited transitionTime seconds
            // OR when a notify was done on emergenciesStack
            // We can't know what of these two was the reason
            // so we try every time to solve emergency (will do nothing if no emergency called)

            // If an emergency call occurs while waiting, solve it now
            solveEmergency();
        } catch (InterruptedException interrupted) {
            LOG.error("Thread was interrupted for unknown reason");
        }
    }

    private void redStep(int transitionTime, RuleGroup nextRunningRule) throws InterruptedException {
        // set to red all traffic lights not specified in the next rule to follow
        crossModuleCore.getTrafficLights().forEach(trafficLight -> {
            if(!nextRunningRule.getTrafficLights().contains(trafficLight.getId())
                    || trafficLight.isDisabled())
                trafficLight.setRed();
        });

        LOG.debug("Wait "+transitionTime+"s red step...");
        // wait for transition
        try {
            synchronized (emergenciesStack) {
                if(transitionTime > 0) {
                    emergenciesStack.wait(transitionTime * 1000L);
                }
            }

            // Now is when waited transitionTime seconds
            // OR when a notify was done on emergenciesStack
            // We can't know what of these two was the reason
            // so we try every time to solve emergency (will do nothing if no emergency called)

            // If an emergency call occurs while waiting, solve it now
            if(solveEmergency()) {
                // Redo redStep (solving emergency end by a green state)
                redStep(transitionTime, nextRunningRule);
            }
        } catch (InterruptedException interrupted) {
            LOG.error("Thread was interrupted for unknown reason");
        }
    }

    private void disableTrafficLights() {
        crossModuleCore.getTrafficLights().forEach(TrafficLight::setDisabled);
    }

    void callEmergency(Emergency emergency) {
        synchronized (emergenciesStack) {
            emergenciesStack.push(emergency);
            emergenciesStack.notify(); // Force the current step to stop waiting
        }
    }

    /**
     * Check for emergencies in stack, and solve them if there are
     * @return 'true' if solved one or more emergencies
     *         'false' otherwise
     */
    private boolean solveEmergency() throws InterruptedException {
        boolean hasSolvedEmergency = false;

        while(!emergenciesStack.isEmpty()) {
            hasSolvedEmergency = true;
            Emergency currentEmergency = emergenciesStack.pop();


            // creating temporary rulegroup with minimum time = normal time (cannot be reduce)
            RuleGroup runningRule = new RuleGroup(currentEmergency.toString(), currentEmergency.getDuration(), currentEmergency.getDuration());
            runningRule.addTrafficLight(currentEmergency.getTrafficLightId());
            LOG.warn("EMERGENCY: RuleGroup changed to " + runningRule);

            // passing all traffic lights to red and wait before passing emergency to green
            redStep(activeScenario.getTransitionTime(), runningRule);

            // passing the traffic light to green and wait needed duration
            greenStep(runningRule.getNormalGreenTime(), runningRule);

            // end of emergency step, remove emergenciesStack
            LOG.warn("Emergency call normally ended");
        }

        return hasSolvedEmergency;
    }

    void synchronize(SynchronizeMessage synchronizeMessage) {
        this.synchronizedTime = synchronizeMessage;
    }
}
