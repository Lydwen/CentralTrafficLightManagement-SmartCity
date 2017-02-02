package fr.unice.polytech.al.trafficlight.route.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by tom on 02/02/17.
 */
public class CrossroadPropertiesExtractor {
    private CrossRoadListWrapper wrapper;

    public CrossroadPropertiesExtractor(){
        Properties properties = null;
        try {
            properties= PropertiesLoaderUtils
                    .loadProperties(new ClassPathResource("crossroadsJSON.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CrossRoadCore> crossRoadCoreList = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        crossRoadCoreList.add(gson.fromJson(properties.getProperty("crossroadINRIA"), CrossRoadCore.class));
        crossRoadCoreList.add(gson.fromJson(properties.getProperty("crossroadCASINO"), CrossRoadCore.class));

        wrapper = new CrossRoadListWrapper(crossRoadCoreList);
    }

    public CrossRoadListWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(CrossRoadListWrapper wrapper) {
        this.wrapper = wrapper;
    }
}

