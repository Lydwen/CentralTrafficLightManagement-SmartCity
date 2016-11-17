package fr.unice.polytech.al.trafficlight.webapp.controller;

import fr.unice.polytech.al.trafficlight.utils.CrossRoadId;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView emergencySubmit(HttpServletRequest request) {
        // Build emergency object
        Emergency emergency = new Emergency();
        emergency.setCrossroadId(
                new CrossRoadId(request.getParameter("crossroad")));
        emergency.setTrafficLightId(
                new TrafficLightId(request.getParameter("traffic_light")));
        emergency.setDuration(
                Integer.parseInt(request.getParameter("duration")));

        // Build model&view
        ModelAndView modelAndView = new ModelAndView("emergency");

        try {
            // Send the emergency
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(CENTRAL_EMERGENCY_URI, emergency);

            modelAndView.addObject("success", true);
        } catch (RestClientException e) {
            e.printStackTrace();
            modelAndView.addObject("error", e.getMessage());
        }

        return modelAndView;
    }
}