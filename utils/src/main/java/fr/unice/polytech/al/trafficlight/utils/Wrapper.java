package fr.unice.polytech.al.trafficlight.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhoo on 16/11/16.
 */
public class Wrapper {

    private List<String> crossRoadName;

    public Wrapper() {
        crossRoadName = new ArrayList<>();
    }

    public void setCrossRoadName(List<String> crossRoadName) {
        this.crossRoadName = crossRoadName;
    }

    public List<String> getCrossRoadName() {

        return crossRoadName;
    }
}
