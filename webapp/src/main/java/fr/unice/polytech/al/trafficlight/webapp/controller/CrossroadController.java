package fr.unice.polytech.al.trafficlight.webapp.controller;

/**
 * Created by nasri on 09/11/16.
 */
import com.google.gson.reflect.TypeToken;
import fr.unice.polytech.al.trafficlight.webapp.provider.CrossroadProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class CrossroadController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView hello() {

            List<String> crossroads = new CrossroadProvider().getCrossroads();

            ModelAndView model = new ModelAndView();
            model.setViewName("test");
            model.addObject("crossroads", crossroads);

            return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable String id) {

        String crossroad = new CrossroadProvider().getCrossroadByName();
        ModelAndView model = hello();
        model.addObject("crossroad", id);

        return model;
    }
}