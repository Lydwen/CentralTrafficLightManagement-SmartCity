package fr.unice.polytech.al.trafficlight.webapp.controller;

import fr.unice.polytech.al.trafficlight.utils.CrossRoadId;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Emergency controller.
 */
@Controller
public class EmergencyController {
    private static final String CENTRAL_EMERGENCY_URI = "https://central-traffic-light.herokuapp.com/emergency";

    @RequestMapping(value = "/emergency", method = RequestMethod.GET)
    public ModelAndView emergency() {
        return new ModelAndView("emergency");
    }

    @RequestMapping(value = "/emergency", method = RequestMethod.POST)
    public ModelAndView emergencySubmit(HttpServletRequest request, HttpServletResponse response) {
        // Build emergency object
        Emergency emergency = new Emergency();
        emergency.setCrossroadId(
                new CrossRoadId(request.getParameter("crossroad")));
        emergency.setTrafficLightId(
                new TrafficLightId(request.getParameter("traffic_light")));

        // Send the emergency
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(CENTRAL_EMERGENCY_URI, emergency);

        // Set response
        response.setStatus(HttpServletResponse.SC_ACCEPTED);

        // Build model&view
        ModelAndView modelAndView = new ModelAndView("emergency");
        modelAndView.addObject("success", true);
        return modelAndView;
    }
}