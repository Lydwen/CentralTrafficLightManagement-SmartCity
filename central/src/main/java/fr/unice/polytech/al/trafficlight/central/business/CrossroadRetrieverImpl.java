package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */
@Service
public class CrossroadRetrieverImpl implements CrossroadRetriever {

    @Autowired
    private DatabaseDao databaseDao;

    public CrossRoad getCrossroad(String name) {
        return databaseDao.getCrossroad(name);
    }

    public Set<CrossRoad> getAllCrossroad(){
        return databaseDao.getAllCrossroad();
    }

    public Set<String> getAllCrossroadName() {
        return databaseDao.getAllCrossroadKey();
    }

    public Set<String> getCrossroadNameMatchTo(String searchName) {
        Set<String> ret = new HashSet<>();
        for (String s : databaseDao.getAllCrossroadKey()) {
            if (s.contains(searchName))
                ret.add(s);
        }
        return ret;
    }

    public void addCrossroad(CrossRoad crossRoad) {
        databaseDao.addCrossroad(crossRoad);
    }

}
