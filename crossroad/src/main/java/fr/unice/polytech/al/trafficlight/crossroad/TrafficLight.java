package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;
import org.apache.log4j.Logger;

/**
 * Created by nathael on 26/10/16.
 */
class TrafficLight {
    private final static Logger LOG = Logger.getLogger(TrafficLight.class);

    private final TrafficLightId id;
    private volatile boolean isGreen = false;

    public TrafficLight(TrafficLightId id) {
        this.id = id;
    }

    /** Send order to put this trafficLight isGreen
     * @return 1 if we have a problem, 0 in other case
     */
    int setGreen(){
        //Send order
        //if we have validation
        isGreen = true;
        LOG.info("TrafficLight "+id+" is now Green !!");
        return 0;
    }

    /** Send order to put this trafficLight red
     * @return 1 if we have a problem, 0 in other case
     */
    int setRed(){
        //Send order
        //if we have validation
        isGreen = false;
        LOG.info("TrafficLight "+id+" is now Red :(");
        return 0;
    }

    boolean isGreen() {
        return isGreen;
    }


    /** Return TrafficLight id
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
