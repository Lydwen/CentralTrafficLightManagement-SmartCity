package fr.unice.polytech.al.trafficlight.crossing;

import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

/**
 * Created by nathael on 27/10/16.
 */
class CrossingModuleRunning implements Runnable {
    private final CrossingModuleCore crossModuleCore;
    private Scenario activeScenario;
    private boolean isRunning = false;

    CrossingModuleRunning(final CrossingModuleCore crossingModuleCore) {
        this.crossModuleCore = crossingModuleCore;
    }


    Scenario getActiveScenario() {
        return activeScenario;
    }

    void changeScenario(final Scenario newScenario) {
        if(isRunning == false) {
            this.activeScenario = newScenario;
            new Thread(this).start();
        }
        else {
            this.activeScenario = newScenario;
            // TODO : should be adapted to active scenario to not make people crazy
        }
    }

    void stopRunning() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            int currentRule = 0;
            while(isRunning) {
                currentRule++;
                RuleGroup activeRule;
                try {
                    activeRule = activeScenario.getRuleGroup(currentRule);
                    if (activeRule == null) {
                        currentRule = 0;
                        activeRule = activeScenario.getRuleGroup(0);
                    }
                } catch(IndexOutOfBoundsException ioobe) {
                    // activeScenario has no groupRules
                    return; // so die.
                }

                // set all traffic lights to red
                crossModuleCore.getTrafficLights().forEach(TrafficLight::setRed);

                // wait for transition
                Thread.sleep(activeScenario.getTransitionTime());

                // set to green all traffic lights specified in rule
                final RuleGroup finalActiveRule = activeRule;
                crossModuleCore.getTrafficLights().forEach(trafficLight -> {
                    if(finalActiveRule.getTrafficLights().contains(trafficLight.getId()))
                        trafficLight.setGreen();
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}
