package fr.unice.polytech.al.trafficlight.crossroad;

import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

/**
 * Created by nathael on 26/10/16.
 */
class TrafficLight {

    private TrafficLightId id;
    private boolean isGreen = false;

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
        System.out.println("TrafficLight "+id+" is now Green !!");
        return 0;
    }

    /** Send order to put this trafficLight red
     * @return 1 if we have a problem, 0 in other case
     */
    int setRed(){
        //Send order
        //if we have validation
        isGreen = false;
        System.out.println("TrafficLight "+id+" is now Red :(");
        return 0;
    }


    /** Return TrafficLight id
     *  @return TrafficLight id
     */
    TrafficLightId getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+"-"+(isGreen?"green":"red");
    }
}
