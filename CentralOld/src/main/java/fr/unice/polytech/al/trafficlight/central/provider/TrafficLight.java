package fr.unice.polytech.al.trafficlight.central.provider;

import java.util.ArrayList;

/**
 * Created by rhoo on 26/10/16.
 */
public class TrafficLight {

    private int id;
    private boolean green;
    private ArrayList<Integer> wayList;

    public TrafficLight(int id) {
        this.id = id;
        green = false;
    }

    public TrafficLight(int id, ArrayList<Integer> wayList) {
        this.id = id;
        this.wayList = wayList;
        green = false;
    }

    /** Send order to put this trafficLight green
     * @return 1 if we have a problem, 0 in other case
     */
    public int goGreen(){
        //Send order
        //if we have validation
        green = true;
        return 0;
    }

    /** Send order to put this trafficLight red
     * @return 1 if we have a problem, 0 in other case
     */
    public int goRed(){
        //Send order
        //if we have validation
        green = false;
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
    public int getId() {
        return id;
    }

    /** Set a new id for the TrafficLight
     *  @param id the new id of the TrafficLight
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Return TrafficLight list of way
     *  @return a list who contains all the group of the group
     */
    public ArrayList<Integer> getways() {
        return wayList;
    }

}
