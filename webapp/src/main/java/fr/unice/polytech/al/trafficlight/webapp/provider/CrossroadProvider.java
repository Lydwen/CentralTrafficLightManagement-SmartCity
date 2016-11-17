package fr.unice.polytech.al.trafficlight.webapp.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nasri on 15/11/16.
 */
public class CrossroadProvider {

    public List<String> getCrossroads(){
        List<String> crossroads = new ArrayList<String>();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet req = new HttpGet("https://central-traffic-light.herokuapp.com/crossroad");
            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(req);
            System.out.println("response status :" + response.getStatusLine().getStatusCode());


            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            Gson gson = new Gson();
            crossroads = gson.fromJson(result.toString(), new TypeToken<ArrayList<String>>(){}.getType());

        } catch (IOException ex) {

        }

        return crossroads;
    }

    public CrossRoad getCrossroadByName(String crossroadName){
        CrossRoad crossroad = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet req = new HttpGet("https://central-traffic-light.herokuapp.com/crossroad/" + crossroadName);
            req.addHeader("accept", "application/json");
            req.addHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(req);
            System.out.println("response status :" + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            Gson gson = new Gson();
            crossroad = gson.fromJson(result.toString(), CrossRoad.class);

        } catch (IOException ex) {

        }

        return crossroad;
    }

}
