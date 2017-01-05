package fr.unice.polytech.al.trafficlight.electriccar.provider;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by nasri on 05/01/17.
 */
public class CommunicationService {

    private final static Logger LOG = Logger.getLogger(CommunicationService.class);
    String routeUrl = "https://route-smart-city.herokuapp.com/crossroad/";
    public void ImHere(String crossroadId, String trafficLightId, String carId) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPut req = new HttpPut(routeUrl + crossroadId + "/trafficlight/" + trafficLightId + "/vehicle/" + carId);

            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            HttpResponse result = httpClient.execute(req);
            System.out.println("response status :" + result.getStatusLine().getStatusCode());
            LOG.info("response status :" + result.getStatusLine().getStatusCode());

        } catch (IOException ex) {
        }
    }

    public void ILeave(String crossroadId, String trafficLightId, String carId) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpDelete req = new HttpDelete(routeUrl + crossroadId + "/trafficlight/" + trafficLightId + "/vehicle/" + carId);
            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            HttpResponse result = httpClient.execute(req);
            System.out.println("response status :" + result.getStatusLine().getStatusCode());
            LOG.info("response status :" + result.getStatusLine().getStatusCode());

        } catch (IOException ex) {
        }
    }
}
