package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.central.business.ScenarioRetriever;
import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rhoo on 17/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class TestScenarioRetriever {
    @Autowired
    private ScenarioRetriever retriever;

    @Autowired
    private DatabaseDao database;

    private Scenario scenario1;
    private Scenario scenario2;
    private Set<String> answerSet = new HashSet<>();
    private String nameScenar1 = "Scenar1";
    private String nameScenar2 = "Scenar2";

    @Before
    public void init() {
        database.clearDatabase();
        scenario1 = new Scenario(nameScenar1);
        scenario2 = new Scenario(nameScenar2);
        database.addScenario(scenario1);
        database.addScenario(scenario2);
    }

    @Test
    public void testGetScenarioMatchTo() {
        answerSet.add(nameScenar1);
        answerSet.add(nameScenar2);
        assertEquals(answerSet,retriever.getScenarioIdMatchTo("Sce"));
        answerSet.clear();
        answerSet.add(nameScenar1);
        assertEquals(answerSet,retriever.getScenarioIdMatchTo("1"));
        assertNotEquals(answerSet,retriever.getScenarioIdMatchTo("2"));
        answerSet.clear();
        assertEquals(answerSet,retriever.getScenarioIdMatchTo("notIn"));
    }

    @After
    public void clean(){
        database.clearDatabase();
    }
}
