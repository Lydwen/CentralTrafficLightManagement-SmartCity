package fr.unice.polytech.al.trafficlight.webapp.provider;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nasri on 16/11/16.
 */
public class ScenarioProvider {
    public String setScenario(String id, Scenario scenario, String spread) {
        Logger l = Logger.getLogger(this.getClass());
        l.debug("START SENDING SCENARIO");
        String status = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPut req;
            if(spread.equals(""))
                req = new HttpPut("https://central-traffic-light.herokuapp.com/scenario/" + id);
            else
                req = new HttpPut("https://central-traffic-light.herokuapp.com/scenario/" + id + "/" + spread);

            Gson gson = new Gson();
            String json = gson.toJson(scenario);

            StringEntity se = new StringEntity(json);
            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            req.setEntity(se);

            HttpResponse result = httpClient.execute(req);
            status = result.getStatusLine().getStatusCode() + "";
            //System.out.println("response status :" + result.getStatusLine().getStatusCode());

        } catch (IOException ex) {
        }

        return status;
    }
}
