package fr.unice.polytech.al.trafficlight.webapp.servlets;

import fr.unice.polytech.al.trafficlight.utils.CrossRoadId;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Emergency servlet.
 */
public class EmergencyServlet extends HttpServlet {
    private static final String EMERGENCY_URI = "https://central-traffic-light.herokuapp.com/emergency";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Build emergency object
        Emergency emergency = new Emergency();
        emergency.setCrossroadId(
                new CrossRoadId(request.getParameter("crossroad")));
        emergency.setTrafficLightId(
                new TrafficLightId(request.getParameter("traffic_light")));

        // Send the emergency
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(EMERGENCY_URI, emergency);

        // Set response
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
}
