package fr.unice.polytech.al.trafficlight.central.business;

import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rhoo on 16/11/16.
 */

@Service
public class ScenarioRetrieverImpl implements ScenarioRetriever {

    @Autowired
    private DatabaseDao databaseDao;

    public Scenario getScenario(String id) {
        return databaseDao.getScenario(id);
    }

    public Set<Scenario> getAllScenario() {
        return databaseDao.getAllScenario();
    }

    public Set<String> getAllScenarioId() {
        return databaseDao.getAllScenarioKey();
    }

    public Set<String> getScenarioIdMatchTo(String searchId) {
        Set<String> ret = new HashSet<>();
        for(String s: databaseDao.getAllScenarioKey()) {
            if(s.contains(searchId))
                ret.add(s);
        }
        return ret;
    }
}
