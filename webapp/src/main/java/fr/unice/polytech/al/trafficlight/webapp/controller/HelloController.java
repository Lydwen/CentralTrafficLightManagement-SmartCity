package fr.unice.polytech.al.trafficlight.webapp.controller;

/**
 * Created by nasri on 09/11/16.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("msg", "achraf");

        return model;

    }

}
