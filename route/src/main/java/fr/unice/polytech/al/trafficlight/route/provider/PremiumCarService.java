package fr.unice.polytech.al.trafficlight.route.provider;

/**
 * Premium car service.
 *
 * @author Kevin Buisson
 */
public interface PremiumCarService {
    /**
     * Declares a premium car arriving at the specified traffic light of the specified crossroad.
     *
     * @param crossroadName    crossroad's name
     * @param trafficlightName traffic light's name
     * @param vehicleId        vehicle ID
     * @return OK/KO
     */
    String declareLocation(String crossroadName,
                           String trafficlightName,
                           String vehicleId);

    /**
     * Declares a premium car leaving the specified traffic light of the specified crossroad.
     *
     * @param crossroadName    crossroad's name
     * @param trafficlightName traffic light's name
     * @param vehicleId        vehicle ID
     * @return OK/KO
     */
    String removeLocation(String crossroadName,
                          String trafficlightName,
                          String vehicleId);
}
