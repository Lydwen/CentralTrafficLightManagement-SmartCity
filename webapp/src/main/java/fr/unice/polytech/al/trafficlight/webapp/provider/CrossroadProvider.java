package fr.unice.polytech.al.trafficlight.webapp.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.servlet.ModelAndView;

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

            String json = gson.toJson(result);
            System.out.println(json);

            //List<String> crossRoadsS = new ArrayList<>();
            crossroads.add("carrefour_de_l_INRIA");
            crossroads.add("carrefour_du_casino");

            //gson.toJson(crossRoadsS);

            /*String json = gson.toJson(crossRoadsS);
            System.out.println(json);*/

            //crossroads = gson.fromJson(json, new TypeToken<ArrayList<String>>(){}.getType());



        } catch (IOException ex) {

        }

        return crossroads;
    }

    public String getCrossroadByName(){
        return "testCrossroad";
    }

}
