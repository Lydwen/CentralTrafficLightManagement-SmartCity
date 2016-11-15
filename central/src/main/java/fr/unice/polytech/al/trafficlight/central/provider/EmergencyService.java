package fr.unice.polytech.al.trafficlight.central.provider;

import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.Emergency;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
    private GsonBuilder builder = new GsonBuilder();

    @POST
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

        return Response.ok().build();
    }
}
