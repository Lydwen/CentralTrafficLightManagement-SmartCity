package fr.unice.polytech.al.trafficlight.central;

import fr.unice.polytech.al.trafficlight.central.business.CrossroadRetriever;
import fr.unice.polytech.al.trafficlight.central.dao.DatabaseDao;
import fr.unice.polytech.al.trafficlight.utils.CrossRoad;
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
public class TestCrossroadRetriever {


    @Autowired
    private CrossroadRetriever retriever;
    @Autowired
    private DatabaseDao database;

    private CrossRoad crossroad1;
    private CrossRoad crossroad2;
    private Set<String> answerSet = new HashSet<>();
    private String namecrossroad1 = "crossroad1";
    private String namecrossroad2 = "crossroad2";

    @Before
    public void init() {
        //database.clearDatabase();
        crossroad1 = new CrossRoad(namecrossroad1, "url1");
        crossroad2 = new CrossRoad(namecrossroad2, "url2");
        database.addCrossroad(crossroad1);
        database.addCrossroad(crossroad2);
    }

    @Test
    public void testAddGet() {
        retriever.addCrossroad(crossroad1);
        assertEquals(crossroad1, database.getCrossroad(namecrossroad1));
        assertEquals(crossroad1, retriever.getCrossroad(namecrossroad1));
    }

    @Test
    public void testGetScenarioMatchTo() {
        answerSet.add(namecrossroad1);
        answerSet.add(namecrossroad2);
        assertEquals(answerSet, retriever.getCrossroadNameMatchTo("cross"));
        answerSet.clear();
        answerSet.add(namecrossroad1);
        assertEquals(answerSet, retriever.getCrossroadNameMatchTo("1"));
        assertNotEquals(answerSet, retriever.getCrossroadNameMatchTo("2"));
        answerSet.clear();
        assertEquals(answerSet, retriever.getCrossroadNameMatchTo("notIn"));
    }

    @After
    public void clean() {
        database.clearDatabase();
    }
}
