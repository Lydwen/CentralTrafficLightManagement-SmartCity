package fr.unice.polytech.al.trafficlight.utils;

/**
 * Emergency request.
 *
 * @author Kévin Buisson
 */
public class Emergency {
    /**
     * Crossroad.
     */
    private CrossRoadId crossroadId;

    /**
     * Traffic light of the crossroad.
     */
    private TrafficLightId trafficLightId;

    /**
     * Minimum duration to set the traffic light green.
     */
    private int duration;

    //  //  //  //  //   GET   //   //  //  //  //

    /**
     * @return id of the crossroad where the traffic light needed is
     */
    public CrossRoadId getCrossroadId() {
        return crossroadId;
    }

    /**
     * @return id of the traffic light needed to be green when this emergency called
     */
    public TrafficLightId getTrafficLightId() {
        return trafficLightId;
    }

    /**
     * @return minimum green light duration needed to this emergency
     */
    public int getDuration() {
        return duration;
    }

    //  //  //  //  //   SET   //   //  //  //  //

    /**
     * Sets crossroad.
     *
     * @param crossroadId the crossroad id
     */
    public void setCrossroadId(CrossRoadId crossroadId) {
        this.crossroadId = crossroadId;
    }

    /**
     * Sets traffic light.
     *
     * @param trafficLightId the traffic light id
     */
    public void setTrafficLightId(TrafficLightId trafficLightId) {
        this.trafficLightId = trafficLightId;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "("+duration+"s Emergency on "+trafficLightId+" of "+crossroadId+")";
    }
}
