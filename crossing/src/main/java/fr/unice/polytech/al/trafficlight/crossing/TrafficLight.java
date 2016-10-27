package fr.unice.polytech.al.trafficlight.crossing;

import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

import java.util.ArrayList;

/**
 * Created by rhoo on 26/10/16.
 */
public class TrafficLight {

    private TrafficLightId id;
    private boolean isGreen;
    private ArrayList<Integer> wayList;

    public TrafficLight(TrafficLightId id) {
        this.id = id;
        isGreen = false;
    }

    public TrafficLight(TrafficLightId id, ArrayList<Integer> wayList) {
        this.id = id;
        this.wayList = wayList;
        isGreen = false;
    }

    /** Send order to put this trafficLight isGreen
     * @return 1 if we have a problem, 0 in other case
     */
    public int setGreen(){
        //Send order
        //if we have validation
        isGreen = true;
        return 0;
    }

    /** Send order to put this trafficLight red
     * @return 1 if we have a problem, 0 in other case
     */
    public int setRed(){
        //Send order
        //if we have validation
        isGreen = false;
        return 0;
    }

    /** Add a way to the list of way
     *  @param way the way to add
     *   @return 1 if the way already exist, 0 in other case
     */
    public int addWay(int way) {
        if(wayList.contains(way))
            return 1;
        wayList.add(way);
        return 0;
    }


    /** Return TrafficLight id
     *  @return TrafficLight id
     */
    public TrafficLightId getId() {
        return id;
    }

    /** Set a new id for the TrafficLight
     *  @param id the new id of the TrafficLight
     */
    public void setId(TrafficLightId id) {
        this.id = id;
    }

    /** Return TrafficLight list of way
     *  @return a list who contains all the group of the group
     */
    public ArrayList<Integer> getways() {
        return wayList;
    }

}
