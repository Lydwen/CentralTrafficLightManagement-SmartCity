package fr.unice.polytech.al.trafficlight.electriccar.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.electriccar.utils.SpatialTrafficLight;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadListWrapper;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import fr.unice.polytech.al.trafficlight.utils.TrafficLight;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nasri on 05/01/17.
 */
public class CommunicationService {

    private final static Logger LOG = Logger.getLogger(CommunicationService.class);
    String routeUrl = "https://route-smart-city.herokuapp.com/premium/crossroad/";
    String trafficLightUrl = "https://route-smart-city.herokuapp.com/crossroadList";
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

    public Set<SpatialTrafficLight> getTrafficLights() {
        System.out.println("debut de methode");
        Set<SpatialTrafficLight> trafficLights = new HashSet<SpatialTrafficLight>();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet get = new HttpGet(trafficLightUrl);
            HttpResponse resp = httpClient.execute(get);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(resp);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            CrossRoadListWrapper wrap = gson.fromJson(body, CrossRoadListWrapper.class);
            System.out.println("Wrap" + wrap.getCrossRoadCores());
            for(CrossRoadCore crc : wrap.getCrossRoadCores()) {
                System.out.println("INSIDE " + crc.getTrafficLights());
                for(TrafficLight tl : crc.getTrafficLights()) {
                    trafficLights.add(new SpatialTrafficLight(tl.getName(),crc.getName(),tl.getCoordinates().getLatitude(),tl.getCoordinates().getLongitude()));
                }
            }

        } catch(IOException ex) {

        }
        return trafficLights;
    }
}
