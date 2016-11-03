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

    TrafficLight(TrafficLightId id) {
        this.id = id;
    }

    /**
     * Send order to put this trafficLight isGreen
     */
    void setGreen(){
        // Do nothing if already green
        if(!isGreen) {
            isGreen = true;
            LOG.info("TrafficLight " + id + " is now Green :D");
        }
    }

    /**
     * Send order to put this trafficLight red
     */
    void setRed(){
        // Do nothing if already red
        if(isGreen) {
            isGreen = false;
            LOG.info("TrafficLight " + id + " is now Red :(");
        }
    }

    /**
     * @return 'true' if trafficLight is Green, 'false' otherwise (yellow or red)
     */
    boolean isGreen() {
        return isGreen;
    }


    /**
     *  @return TrafficLight id
     */
    TrafficLightId getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+":"+(isGreen?"green":"red");
    }
}
