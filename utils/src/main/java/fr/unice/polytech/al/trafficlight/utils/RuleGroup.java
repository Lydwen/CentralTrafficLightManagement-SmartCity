package fr.unice.polytech.al.trafficlight.utils;

import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nathael on 26/10/16.
 */
public class RuleGroup {
    private final String id;
    private long greenTime; // Green time in ms
    private final Set<TrafficLightId> trafficLightList;

    public RuleGroup(String id, long greenTime) {
        this.id = id;
        this.greenTime = greenTime;
        trafficLightList = new HashSet<>();
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
    public Set<TrafficLightId> getTrafficLights() {
        return new HashSet<>(trafficLightList);
    }

    /**
     * Get how long is this group traffic lights's green time
     * @return ms time
     */
    public long getGreenTime() {
        return greenTime;
    }


    //  //  //  //  //   SET   //   //  //  //  //


    /** Add a traffic light to the rule group
     *  @param trafficLight the traffic light to add
     *  @return 'true' if trafficLight was successfully added to RuleGroup
     */
    public boolean addTrafficLight(TrafficLightId trafficLight) {
        return trafficLightList.add(trafficLight);
    }

    /**
     * Change this group traffic lights's green time
     * @param greenTime Green time in ms
     */
    public void setGreenTime(long greenTime) {
        this.greenTime = greenTime;
    }

    @Override
    public String toString() {
        return "RG:"+id+":"+greenTime+":"+trafficLightList;
    }
}
