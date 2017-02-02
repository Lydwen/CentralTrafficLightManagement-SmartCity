package fr.unice.polytech.al.trafficlight.route.utils;

import fr.unice.polytech.al.trafficlight.utils.CrossRoadCore;

import java.util.List;

/**
 * Created by tom on 02/02/17.
 */
public class CrossRoadListWrapper {
    private List<CrossRoadCore> crossRoadCores;

    public CrossRoadListWrapper(){}

    public CrossRoadListWrapper(List<CrossRoadCore> crossRoadCores){
        this.crossRoadCores = crossRoadCores;
    }

    public List<CrossRoadCore> getCrossRoadCores() {
        return crossRoadCores;
    }

    public void setCrossRoadCores(List<CrossRoadCore> crossRoadCores) {
        this.crossRoadCores = crossRoadCores;
    }
}
