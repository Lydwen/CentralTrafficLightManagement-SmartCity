package fr.unice.polytech.al.trafficlight.crossroad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

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

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        LOG.debug("######## Get status called !");

        String message = "";

        for(TrafficLight trafficLight: CORE.getTrafficLights()) {
            message += ",{\"id\":\""+trafficLight.getId() + "\",\"state\":\""
                    +(trafficLight.isDisabled()?"disabled":trafficLight.isGreen()?"green":"red")
                    +  "\"}";
        }

        message = message.length()>0?"[" + message.substring(1) + "]":"[]";

        /*for(TrafficLight trafficLight: CORE.getTrafficLights()) {
            message += "<p>"+trafficLight.getId()
                    + " <img  width=\"48\" height=\"48\" src=\""
                    +(trafficLight.isDisabled()?
                        "https://cdn2.iconfinder.com/data/icons/function_icon_set/warning_48.png"
                        :trafficLight.isGreen()?
                        "https://cdn2.iconfinder.com/data/icons/function_icon_set/circle_green.png"
                        :"https://cdn2.iconfinder.com/data/icons/function_icon_set/circle_red.png")
                    +"\"></p>";
        }

        message = "<html><head></head><body>"+message+"</body></html>";*/

        return Response.ok().entity(message).build();
    }
}
