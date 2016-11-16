package fr.unice.polytech.al.trafficlight.central.provider;

import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.central.provider.utils.WebRequester;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Emergency service.
 *
 * @author Kévin Buisson
 */
@Path("emergency")
public class EmergencyService {
    @Autowired
    @Qualifier("crossroadsRequester")
    private WebRequester crossroadsRequester;

    private GsonBuilder builder = new GsonBuilder();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEmergency(String jsonEmergency) {
        // Deserialize the emergency
        Emergency emergency =
                builder.create().fromJson(jsonEmergency, Emergency.class);

        // Display some logs for the emergency
        System.out.println(
                String.format(
                        "[POST /emergency] PLEASE TURN THE TRAFFIC LIGHT %s " +
                                "ON THE CROSSROAD %s ON FOR %d SECONDS !!!",
                        emergency.getCrossroadId(),
                        emergency.getTrafficLightId(),
                        emergency.getDuration()));

        // Send the emergency
        crossroadsRequester.put(emergency.getCrossroadId().getId(), "/crossroad/emergency", emergency);

        return Response.ok().build();
    }
}
