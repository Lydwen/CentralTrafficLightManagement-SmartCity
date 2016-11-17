package fr.unice.polytech.al.trafficlight.webapp.servlets;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add scenario servlet.
 */
public class AddScenarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPut req = new HttpPut("https://central-traffic-light.herokuapp.com/crossroad");

            Scenario scenario = new Scenario("test");
            scenario.setTransitionTime(Integer.parseInt(request.getParameter("transition_time")));

            RuleGroup rgA = new RuleGroup("A", Integer.parseInt(request.getParameter("green_timeA")));
            rgA.getTrafficLights().add(new TrafficLightId(request.getParameter("traffic_light1")));
            rgA.getTrafficLights().add(new TrafficLightId(request.getParameter("traffic_light2")));

            RuleGroup rgB = new RuleGroup("B", Integer.parseInt(request.getParameter("green_timeB")));
            rgB.getTrafficLights().add(new TrafficLightId(request.getParameter("traffic_light3")));
            rgB.getTrafficLights().add(new TrafficLightId(request.getParameter("traffic_light4")));


            scenario.getRuleGroupList().add(rgA);
            scenario.getRuleGroupList().add(rgB);


            Gson gson = new Gson();
            String json = gson.toJson(scenario);
            System.out.println(json);

            StringEntity se = new StringEntity(json);
            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            //sets the post request as the resulting string
            req.setEntity(se);


            HttpResponse result = httpClient.execute(req);
            System.out.println("response status :" + result.getStatusLine().getStatusCode());

        } catch (IOException ex) {
        }
    }
}
