package fr.unice.polytech.al.trafficlight.utils;

import java.util.*;

/**
 * Created by nathael on 26/10/16.
 */
public class RuleGroup {

    private String id;
    private int normalGreenTime; // Green time in s
    private int minimumGreenTime; // Minimum time green (considering there are no electric vehicles waiting)
    private Set<TrafficLightId> trafficLights;

    public RuleGroup(){
        trafficLights = new HashSet<>();
    }

    public RuleGroup(String id, int normalGreenTime, int minimumGreenTime) {
        this.id = id;
        this.normalGreenTime = normalGreenTime;
        this.minimumGreenTime = minimumGreenTime;
        trafficLights = new HashSet<>();
    }


    /** Add a traffic light to the rule group
     *  @param trafficLight the traffic light to add
     *  @return 'true' if trafficLight was successfully added to RuleGroup
     */
    public boolean addTrafficLight(TrafficLightId trafficLight) {
        return trafficLights.add(trafficLight);
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
        return new HashSet<>(trafficLights);
    }

    /**
     * Get how long is this group traffic lights's green time (when scenario is just in time)
     * @return s Normal case Green time
     */
    public int getNormalGreenTime() {
        return normalGreenTime;
    }

    /**
     * Get how long is this group traffic lights's green time if scenario is late
     * @return s minimum Green time allowed
     */
    public int getMinimumGreenTime() {
        return minimumGreenTime;
    }

    //  //  //  //  //   SET   //   //  //  //  //

    public void setId(String id) {
        this.id = id;
    }

    public void setTrafficLights(Set<TrafficLightId> trafficLights) {
        this.trafficLights = trafficLights;
    }

    /**
     * Change this group traffic lights's green time (when scenario isn't late)
     * @param normalGreenTime Normal case green time in s
     */
    public void setNormalGreenTime(int normalGreenTime) {
        this.normalGreenTime = normalGreenTime;
    }

    /**
     * Change this group traffic lights's minimum green time (when there scenario is late)
     * @param minimumGreenTime Minimum green time in s
     */
    public void setMinimumGreenTime(int minimumGreenTime) {
        this.minimumGreenTime = minimumGreenTime;
    }

    @Override
    public String toString() {
        return "RG:"+id+":"+ normalGreenTime +":"+ trafficLights;
    }
}
