package fr.unice.polytech.al.trafficlight.central.provider;

import java.util.ArrayList;

/**
 * Created by rhoo on 26/10/16.
 */
public class Group {

    private int id;
    private ArrayList<TrafficLight> trafficLightList;

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, ArrayList<TrafficLight> trafficLights) {
        this.id = id;
        trafficLightList = trafficLights;
    }

    /** Add a traffic light to the list of traffic light
     *  @param trafficLight the group to add
     *   @return 1 if the trafficLight already exist, 0 in other case
     */
    public int addTrafficLight(TrafficLight trafficLight) {
        if(trafficLightList.contains(trafficLight))
            return 1;
        trafficLightList.add(trafficLight);
        return 0;
    }


    /** Return Group id
     *  @return Group id
     */
    public int getId() {
        return id;
    }

    /** Set a new id for the Group
     *  @param id the new id of the Group
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Return group list of traffic light
     *  @return a list who contains all the group of the group
     */
    public ArrayList<TrafficLight> getTrafficLightList() {
        return trafficLightList;
    }

}
