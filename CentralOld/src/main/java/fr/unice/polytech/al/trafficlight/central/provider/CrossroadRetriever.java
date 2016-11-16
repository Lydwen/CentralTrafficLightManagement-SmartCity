package fr.unice.polytech.al.trafficlight.central.provider;

import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.utils.Scenario;

import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
public interface CrossroadRetriever {

    public CrossRoad getCrossroad(String name);

    public Set<CrossRoad> getAllCrossroad();

    public Set<String> getAllCrossroadName();

    public Set<String> getCrossroadNameMatchTo(String searchName);

    public void addCrossroad(CrossRoad crossRoad);
}
