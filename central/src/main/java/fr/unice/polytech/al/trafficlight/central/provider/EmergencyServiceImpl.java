package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * Emergency service implementation.
 *
 * @author Kévin Buisson
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/emergency")
public class EmergencyServiceImpl implements EmergencyService {
    @Autowired
    @Qualifier("crossroadsRequester")
    private WebRequester crossroadsRequester;

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    public String declareEmergency(@RequestBody Emergency emergency) {
//        // Display some logs for the emergency
//        System.out.println(
//                String.format(
//                        "[PUT /emergency] PLEASE TURN THE TRAFFIC LIGHT %s " +
//                                "ON THE CROSSROAD %s ON FOR %d SECONDS !!!",
//                        emergency.getCrossroadId(),
//                        emergency.getTrafficLightId(),
//                        emergency.getDuration()));

        // Send the emergency
        crossroadsRequester.put(emergency.getCrossroadId().getId(), "/crossroad/emergency", emergency);
        return "OK";
    }
}
