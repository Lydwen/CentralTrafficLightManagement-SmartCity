package fr.unice.polytech.al.trafficlight.integration;

import fr.unice.polytech.al.trafficlight.integration.utils.HttpEntityFactory;
import fr.unice.polytech.al.trafficlight.utils.Emergency;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Emergency integration test.
 * This test asserts that when you submit an emergency from the web-app, the emergency is well declared on the central, and then on the crossroad.
 */
@Ignore
public class EmergencyIntegrationTest {
    private static final String WEBAPP_URL = "https://webapp-traffic-light.herokuapp.com/";
    private static final String CENTRAL_URL = "https://central-traffic-light.herokuapp.com/";
    private static final String CASINO_CROSSROAD = "carrefour_du_casino";
    private static Properties crossroadsUrls;

    @BeforeClass
    public static void beforeClass() throws IOException {
        crossroadsUrls = PropertiesLoaderUtils.loadProperties(
                new ClassPathResource("/urls/crossroads.properties"));
    }

    @Test
    public void testDeclareEmergency() {
        final String emergencyFormData = "" +
                "crossroad=" + CASINO_CROSSROAD + "&" +
                "traffic_light=gauche&" +
                "duration=5";
        RestTemplate restTemplate = new RestTemplate();

        // Get number of emergencies before call
        int nbEmergenciesCentral = restTemplate.getForEntity(
                CENTRAL_URL + "emergency/logs", Emergency[].class).getBody().length;
        int nbEmergenciesCrossroad = restTemplate.getForEntity(
                crossroadsUrls.getProperty(CASINO_CROSSROAD) + "/crossroad/emergency/logs", Emergency[].class).getBody().length;

        // Set the scenario using the webapp
        restTemplate.postForEntity(
                WEBAPP_URL + "emergency",
                HttpEntityFactory.createFromFormData(emergencyFormData),
                String.class);

        // Wait for server to receive
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the emergency logs from the central
        {
            Emergency[] emergencies = restTemplate.getForEntity(
                    CENTRAL_URL + "emergency/logs", Emergency[].class).getBody();
            assertEquals(nbEmergenciesCentral + 1, emergencies.length);
            assertEmergency(emergencies[emergencies.length - 1]);
        }

        // Get the scenario from the crossroad
        {
            Emergency[] emergencies = restTemplate.getForEntity(
                    crossroadsUrls.getProperty(CASINO_CROSSROAD) + "/crossroad/emergency/logs", Emergency[].class).getBody();
            assertEquals(nbEmergenciesCrossroad + 1, emergencies.length);
            assertEmergency(emergencies[emergencies.length - 1]);
        }
    }

    private static void assertEmergency(Emergency emergency) {
        assertNotNull(emergency);
    }
}
