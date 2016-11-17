package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Emergency service interface.
 */
public interface EmergencyService {
    /**
     * Declare a new emergency.
     *
     * @param emergency emergency
     * @return status
     */
    String declareEmergency(@RequestBody Emergency emergency);
}
