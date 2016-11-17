package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Emergency service interface.
 */
public interface EmergencyService {
    /**
     * Declare a new emergency.
     *
     * @param emergency emergency
     * @param response  HTTP response
     */
    void declareEmergency(@RequestBody Emergency emergency, HttpServletResponse response) throws IOException;
}
