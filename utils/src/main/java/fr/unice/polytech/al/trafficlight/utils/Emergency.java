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
    private String crossroadId;

    /**
     * Traffic light in the carrefour.
     */
    private String trafficLightId;

    /**
     * Duration to let on the traffic light.
     */
    private int duration;

    //  //  //  //  //   GET   //   //  //  //  //

    /**
     * Gets crossroad.
     *
     * @return the crossroad id
     */
    public String getCrossroadId() {
        return crossroadId;
    }

    /**
     * Gets traffic light.
     *
     * @return the traffic light id
     */
    public String getTrafficLightId() {
        return trafficLightId;
    }

    /**
     * Gets duration.
     *
     * @return the duration
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
    public void setCrossroadId(String crossroadId) {
        this.crossroadId = crossroadId;
    }

    /**
     * Sets traffic light.
     *
     * @param trafficLightId the traffic light id
     */
    public void setTrafficLightId(String trafficLightId) {
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
}
