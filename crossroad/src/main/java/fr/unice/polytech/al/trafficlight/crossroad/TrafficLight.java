package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;

/**
 * Created by nathael on 26/10/16.
 */
class TrafficLight {
    private final static Logger LOG = Logger.getLogger(TrafficLight.class);

    private final TrafficLightId id;
    private volatile boolean isGreen = false;
    private volatile boolean isDisabled = true;

    TrafficLight(TrafficLightId id) {
        this.id = id;
    }

    /**
     * Send order to put this trafficLight isGreen
     */
    void setGreen(){
        // Do nothing if already green
        if(isDisabled || !isGreen) {
            isGreen = true;
            isDisabled = false;
            LOG.info("TrafficLight " + id + " is now Green :D");
        }
    }

    /**
     * Send order to put this trafficLight red
     */
    void setRed(){
        // Do nothing if already red
        if(isDisabled || isGreen) {
            isGreen = false;
            isDisabled = false;
            LOG.info("TrafficLight " + id + " is now Red :(");
        }
    }

    /**
     * Send order to disable traffic light (set to blinking orange)
     */
    public void setDisabled() {
        if(!isDisabled) {
            isDisabled = true;
            LOG.info("TrafficLight " + id + " is now blinking orange !!");
        }
    }

    /**
     * @return 'true' if trafficLight is Green,
     *         'false' otherwise (disabled or red)
     */
    boolean isGreen() {
        return isGreen;
    }

    /**
     * @return 'true' if trafficLight is disabled (blinking orange),
     *         'false' if trafficLight is working (red or green)
     */
    boolean isDisabled() { return isDisabled; }


    /**
     *  @return TrafficLight id
     */
    TrafficLightId getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+":"+(isDisabled?"bl'orange":(isGreen?"green":"red"));
    }

}
