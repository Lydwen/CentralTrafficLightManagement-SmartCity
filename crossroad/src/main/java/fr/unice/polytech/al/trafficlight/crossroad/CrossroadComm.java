package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.*;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nathael on 16/11/16.
 */
@Path("crossroad")
public class CrossroadComm {
    private final static Logger LOG = Logger.getLogger(CrossroadComm.class);
    private static CrossroadModuleCore CORE;

    static {
        try {
            CORE = new CrossroadModuleCore();
        } catch (IOException e) {
            LOG.fatal("Impossible to initialise CrossroadModuleCore", e);
        }
    }

    @PUT
    @Path("/starter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeScenario(String newScenarioStr) {
        LOG.debug("######## Starter called !");
        Gson gson = new GsonBuilder().create();
        Scenario newScenario = gson.fromJson(newScenarioStr, Scenario.class);
        CORE.changeScenario(newScenario);

        LOG.debug("Starter call finished > Response OK");
        return Response.ok().entity(gson.toJson(newScenario)).build();
    }
    @PUT
    @Path("/stopper")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stopTrafficLight() {
        LOG.debug("######## Stopper called !");

        if(!CORE.isRunning())
            LOG.info("Wasn't running, but calling stop anyway");

        CORE.stopRunning();

        return Response.ok().build();
    }

    @PUT
    @Path("/emergency")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response callEmergency(String emergencyCallStr) {
        LOG.debug("######## Emergency called !");

        Gson gson = new GsonBuilder().create();
        CORE.callEmergency(gson.fromJson(emergencyCallStr, Emergency.class));

        return Response.ok().build();
    }

    @POST
    @Path("{trafficlightId}/{vehicleId}")
    public Response addVehicle(@PathParam("trafficlightId") String trafficlightId, @PathParam("vehicleId") int vehicleId ) {
        LOG.debug("######## Add vehicle called !");

        for(TrafficLight trafficLight: CORE.getTrafficLights()) {
            if(trafficLight.getId().getId().equals(trafficlightId)) {
                LOG.debug("Electric vehicle before add: " + trafficLight.getElectricVehicle());
                trafficLight.addElectricVehicle();
                LOG.debug("Electric vehicle after add: " + trafficLight.getElectricVehicle());
                return Response.ok().build();
            }
        }
        LOG.error("TrafficLightId: " + trafficlightId +" do not exist");
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{trafficlightId}/{vehicleId}")
    public Response removeVehicle(@PathParam("trafficlightId") String trafficlightId, @PathParam("vehicleId") int vehicleId ) {
        LOG.debug("######## Remove vehicle called !");

        for(TrafficLight trafficLight: CORE.getTrafficLights()) {
            if(trafficLight.getId().getId().equals(trafficlightId)) {
                LOG.debug("Electric vehicle before remove: " + trafficLight.getElectricVehicle());
                trafficLight.removeElectricVehicle();
                LOG.debug("Electric vehicle after remove: " + trafficLight.getElectricVehicle());
                return Response.ok().build();
            }
        }
        LOG.error("TrafficLightId: " + trafficlightId +" do not exist");
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        LOG.debug("######## Get status called !");

        String message = "";

        for(TrafficLight trafficLight: CORE.getTrafficLights()) {
            message += ",{\"id\":\""+trafficLight.getId() + "\",\"state\":\""
                    +(trafficLight.isDisabled()?"disabled":trafficLight.isGreen()?"green":"red")
                    +  "\",\"last_state_change\":\""
                    + new SimpleDateFormat("dd MMM HH:mm:ss")
                        .format(new Date(trafficLight.getLastStateChangeDate()))
                    +"\"}";
        }

        message = message.length()>0?"[" + message.substring(1) + "]":"[]";

        JsonElement jsonElement = new JsonParser().parse(message);
        message = new GsonBuilder().setPrettyPrinting().create().toJson(jsonElement);

        return Response.ok().entity(message).build();
    }
}
