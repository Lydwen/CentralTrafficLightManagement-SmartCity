package fr.unice.polytech.al.trafficlight.integration;

import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.Scenario;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Scenario integration test.
 * This test asserts that when you submit a scenario from the web-app, the scenario is well deployed on the central, and then on the crossroad.
 */
@Ignore
public class ScenarioIntegrationTest {
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
    public void testSetScenario() {
        final String scenarioFormData = "transitionTime=5&" +
                "nbGroups=4&" +
                "0=3&t0=gauche&t0=droite&" +
                "1=4&t1=haut&t1=bas&" +
                "2=5&t2=bas_gauche&t2=haut_droite&" +
                "3=6&t3=bas_droite&t3=haut_gauche";
        RestTemplate restTemplate = new RestTemplate();

        // Set the scenario using the webapp
        restTemplate.postForEntity(
                WEBAPP_URL + CASINO_CROSSROAD,
                buildScenarioRequest(scenarioFormData),
                String.class);

        // Wait for server to receive
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the scenario from the central
        {
            System.out.println(CENTRAL_URL + "crossroad/" + CASINO_CROSSROAD + "/scenario");
            Scenario scenario = restTemplate.getForEntity(
                    CENTRAL_URL + "crossroad/" + CASINO_CROSSROAD + "/scenario", Scenario.class).getBody();
            assertCasinoScenario(scenario);
        }

        // Get the scenario from the crossroad
        {
            Scenario scenario = restTemplate.getForEntity(
                    crossroadsUrls.getProperty(CASINO_CROSSROAD) + "/crossroad/scenario", Scenario.class).getBody();
            assertCasinoScenario(scenario);
        }
    }

    private static void assertCasinoScenario(Scenario scenario) {
        assertNotNull(scenario);

        assertEquals(CASINO_CROSSROAD, scenario.getId());
        assertEquals(5, scenario.getTransitionTime());

        List<RuleGroup> ruleGroups = scenario.getRuleGroupList();
        assertEquals(4, ruleGroups.size());
        {
            RuleGroup ruleGroup = ruleGroups.get(0);
            assertEquals("group0", ruleGroup.getId());
            assertEquals(3, ruleGroup.getMinimumGreenTime());
            assertEquals(3, ruleGroup.getNormalGreenTime());
            assertEquals(2, ruleGroup.getTrafficLights().size());
            assertContainsAll(ruleGroup.getTrafficLights(), "gauche", "droite");
        }
        {
            RuleGroup ruleGroup = ruleGroups.get(1);
            assertEquals("group1", ruleGroup.getId());
            assertEquals(4, ruleGroup.getMinimumGreenTime());
            assertEquals(4, ruleGroup.getNormalGreenTime());
            assertEquals(2, ruleGroup.getTrafficLights().size());
            assertContainsAll(ruleGroup.getTrafficLights(), "haut", "bas");
        }
        {
            RuleGroup ruleGroup = ruleGroups.get(2);
            assertEquals("group2", ruleGroup.getId());
            assertEquals(5, ruleGroup.getMinimumGreenTime());
            assertEquals(5, ruleGroup.getNormalGreenTime());
            assertEquals(2, ruleGroup.getTrafficLights().size());
            assertContainsAll(ruleGroup.getTrafficLights(), "bas_gauche", "haut_droite");
        }
        {
            RuleGroup ruleGroup = ruleGroups.get(3);
            assertEquals("group3", ruleGroup.getId());
            assertEquals(6, ruleGroup.getMinimumGreenTime());
            assertEquals(6, ruleGroup.getNormalGreenTime());
            assertEquals(2, ruleGroup.getTrafficLights().size());
            assertContainsAll(ruleGroup.getTrafficLights(), "bas_droite", "haut_gauche");
        }
    }

    // ====================================================
    // ====================================================

    private static void assertContainsAll(Set<TrafficLightId> trafficLights, String... ids) {
        assertTrue(trafficLights.stream()
                .map(TrafficLightId::getId).collect(Collectors.toList())
                .containsAll(Arrays.asList(ids)));
    }

    private HttpEntity<MultiValueMap<String, String>> buildScenarioRequest(String scenario) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create map form data from the specified scenario
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        Arrays.stream(scenario.split("&")).forEach(
                v -> {
                    String[] values = v.split("=");
                    formData.add(values[0], values[1]);
                });

        return new HttpEntity<>(formData, headers);
    }
}
