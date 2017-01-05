package fr.unice.polytech.al.trafficlight.utils;

/**
 * Premium car location.
 *
 * @author Kevin Buisson
 */
public class PremiumCarLocation {
    /**
     * Crossroad.
     */
    private CrossRoadId crossroadId;

    /**
     * Traffic light of the crossroad.
     */
    private TrafficLightId trafficLightId;

    /**
     * Car.
     */
    private String carId;

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
     * @return id of the car
     */
    public String getCarId() {
        return carId;
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
     * Sets car.
     *
     * @param carId the car id
     */
    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "(Car " + carId + " arriving on " + trafficLightId + " of " + crossroadId + ")";
    }
}
