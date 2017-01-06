package fr.unice.polytech.al.trafficlight.webapp.controller;

/**
 * Created by nasri on 09/11/16.
 */
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import fr.unice.polytech.al.trafficlight.webapp.provider.CrossroadProvider;
import fr.unice.polytech.al.trafficlight.webapp.provider.ScenarioProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CrossroadController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCrossroads() {

            List<String> crossroads = new CrossroadProvider().getCrossroads();

            ModelAndView model = new ModelAndView();
            model.setViewName("test");
            model.addObject("crossroads", crossroads);

            return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getCrossroadByName(@PathVariable String id) {

        CrossRoad crossroad = new CrossroadProvider().getCrossroadByName(id);
        ModelAndView model = getCrossroads();
        model.addObject("name", id);
        model.addObject("scenario", crossroad.getScenario());

        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView setScenario(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

        Scenario scenario = new Scenario();
        scenario.setTransitionTime(Integer.parseInt(request.getParameter("transitionTime")));
        scenario.setId(id);

        int nbGroups = Integer.parseInt(request.getParameter("nbGroups"));
        int nbGroupsFound=0;
        int num=0;
        while(nbGroupsFound < nbGroups){
            if(request.getParameter(num + "") != null){
                RuleGroup ruleGroup = new RuleGroup();
                ruleGroup.setId("group" + num);
                ruleGroup.setNormalGreenTime(Integer.parseInt(request.getParameter(num + "")));
                ruleGroup.setMinimumGreenTime(Integer.parseInt(request.getParameter(num + "")));
                String[] trafficLights = request.getParameterValues("t" + num);
                for(String t : trafficLights)
                    ruleGroup.addTrafficLight(new TrafficLightId(t));
                scenario.addRuleGroup(ruleGroup);
                nbGroupsFound++;
            }
            num++;
        }
        String status = new ScenarioProvider().setScenario(id,scenario);

        ModelAndView model = getCrossroads();
        model.addObject("status", status);

        return model;
    }
}