package fr.unice.polytech.al.trafficlight.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nathael on 26/10/16.
 */
public class RuleGroup {

    private String id;
    private int greenTime; // Green time in s
    private Set<TrafficLightId> trafficLightList;

    public RuleGroup(){}

    public RuleGroup(String id, int greenTime) {
        this.id = id;
        this.greenTime = greenTime;
        trafficLightList = new HashSet<>();
    }

    /** Add a traffic light to the rule group
     *  @param trafficLight the traffic light to add
     *  @return 'true' if trafficLight was successfully added to RuleGroup
     */
    public boolean addTrafficLight(TrafficLightId trafficLight) {
        return trafficLightList.add(trafficLight);
    }

    //  //  //  //  //   GET   //   //  //  //  //

    /**
     *  @return RuleGroup id
     */
    public String getId() {
        return id;
    }


    /** Return group list of traffic light
     *  @return a list who contains all the group of the group
     */
    public Set<TrafficLightId> getTrafficLightList() {
        return new HashSet<>(trafficLightList);
    }

    /**
     * Get how long is this group traffic lights's green time
     * @return s time
     */
    public int getGreenTime() {
        return greenTime;
    }


    //  //  //  //  //   SET   //   //  //  //  //

    public void setId(String id) {
        this.id = id;
    }

    public void setTrafficLightList(Set<TrafficLightId> trafficLightList) {
        this.trafficLightList = trafficLightList;
    }

    /**
     * Change this group traffic lights's green time
     * @param greenTime Green time in s
     */
    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    @Override
    public String toString() {
        return "RG:"+id+":"+greenTime+":"+trafficLightList;
    }
}
