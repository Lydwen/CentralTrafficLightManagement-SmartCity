package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.utils.PremiumCarLocation;

/**
 * Premium car service.
 *
 * @author Kevin Buisson
 */
public interface PremiumCarService {
    String declareLocation(PremiumCarLocation premiumCarLocation);
}
