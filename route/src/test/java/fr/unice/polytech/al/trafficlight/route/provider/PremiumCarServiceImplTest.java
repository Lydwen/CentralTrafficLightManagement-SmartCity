package fr.unice.polytech.al.trafficlight.route.provider;

import fr.unice.polytech.al.trafficlight.route.Application;
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
        assertEquals("{\"status\": \"OK\"}",
                premiumCarService.declareLocation("carrefour_de_l_INRIA", "north", "Chips"));
    }

    @Test
    public void testFailDeclareLocation() throws Exception {
        // Location with fake crossroad
        assertEquals("{\"status\": \"KO\", \"reason\": \"Crossroad 'cr_inexistant' does not exists.\"}",
                premiumCarService.declareLocation("cr_inexistant", "haut_gauche", "Chips<3"));

        // Location with fake traffic light
        // Declare location
        assertEquals("{\"status\": \"KO\", \"reason\": \"Traffic light 'tl_inexistant' does not exists in crossroad 'carrefour_du_casino'.\"}",
                premiumCarService.declareLocation("carrefour_du_casino", "tl_inexistant", "Chips<3"));
    }
}
