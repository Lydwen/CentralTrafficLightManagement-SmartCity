package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * Created by nathael on 26/10/16.
 */
class TrafficLight {
    private final static Logger LOG = Logger.getLogger(TrafficLight.class);

    private final TrafficLightId id;
    private volatile boolean isGreen = false;
    private volatile boolean isDisabled = true;
    private volatile long lastStateChangeDate = Calendar.getInstance().getTimeInMillis();
    private volatile int electricVehicleNumber = 0;

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

        // update
        lastStateChangeDate = Calendar.getInstance().getTimeInMillis();
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

        // update
        lastStateChangeDate = Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Send order to disable traffic light (set to blinking orange)
     */
    void setDisabled() {
        if(!isDisabled) {
            isDisabled = true;
            LOG.info("TrafficLight " + id + " is now blinking orange !!");
        }

        // update
        lastStateChangeDate = Calendar.getInstance().getTimeInMillis();
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

    long getLastStateChangeDate() {
        return lastStateChangeDate;
    }

    public void addElectricVehicle() {
        electricVehicleNumber++;
    }

    public void removeElectricVehicle(){
        electricVehicleNumber--;
    }

    public int getElectricVehicle(){
        return electricVehicleNumber;
    }
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
