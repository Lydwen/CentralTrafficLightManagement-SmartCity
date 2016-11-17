package fr.unice.polytech.al.trafficlight.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Emergency controller.
 */
@Controller
public class EmergencyController {
    @RequestMapping(value = "/emergency", method = RequestMethod.GET)
    public ModelAndView emergency() {
        return new ModelAndView("emergency");
    }
}