package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.route.Application;
import fr.unice.polytech.al.trafficlight.utils.CrossRoadId;
import fr.unice.polytech.al.trafficlight.utils.PremiumCarLocation;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class PremiumCarServiceImplTest {
    @Autowired
    private PremiumCarService premiumCarService;

    @Test
    public void testDeclareLocation() throws Exception {
        // Classic location
        PremiumCarLocation premiumCarLocation = new PremiumCarLocation();
        premiumCarLocation.setCrossroadId(new CrossRoadId("carrefour_du_casino"));
        premiumCarLocation.setTrafficLightId(new TrafficLightId("haut_gauche"));
        premiumCarLocation.setCarId("Chips<3");

        assertEquals("{\"status\": \"OK\"}",
                premiumCarService.declareLocation(premiumCarLocation));
    }

    @Test
    public void testFailDeclareLocation() throws Exception {
        // Location with fake crossroad
        {
            PremiumCarLocation premiumCarLocation = new PremiumCarLocation();
            premiumCarLocation.setCrossroadId(new CrossRoadId("cr_inexistant"));
            premiumCarLocation.setTrafficLightId(new TrafficLightId("haut_gauche"));
            premiumCarLocation.setCarId("Chips<3");

            assertEquals("{\"status\": \"KO\", \"reason\": \"Crossroad 'cr_inexistant' does not exists.\"}",
                    premiumCarService.declareLocation(premiumCarLocation));
        }

        // Location with fake traffic light
        {
            PremiumCarLocation premiumCarLocation = new PremiumCarLocation();
            premiumCarLocation.setCrossroadId(new CrossRoadId("carrefour_du_casino"));
            premiumCarLocation.setTrafficLightId(new TrafficLightId("tl_inexistant"));
            premiumCarLocation.setCarId("Chips<3");

            // Declare location
            assertEquals("{\"status\": \"KO\", \"reason\": \"Traffic light 'tl_inexistant' does not exists in crossroad 'carrefour_du_casino'.\"}",
                    premiumCarService.declareLocation(premiumCarLocation));
        }
    }
}
