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
 * Emergency integration test.
 */
@Ignore
public class ScenarioIntegrationTest {
    public static final String WEB_APP_URL = "https://webapp-traffic-light.herokuapp.com/";
    private static Properties crossroadsUrls;

    @BeforeClass
    public static void beforeClass() throws IOException {
        crossroadsUrls = PropertiesLoaderUtils.loadProperties(
                new ClassPathResource("/urls/crossroads.properties"));
    }

    @Test
    public void testSetScenario() {
        final String crossroad = "carrefour_du_casino";
        final String scenarioFormData = "transitionTime=5&" +
                "nbGroups=4&" +
                "0=3&t0=gauche&t0=droite&" +
                "1=5&t1=bas_gauche&t1=haut_droite";
        RestTemplate restTemplate = new RestTemplate();

        // Set the scenario using the webapp
        restTemplate.postForEntity(
                WEB_APP_URL + crossroad,
                buildScenarioRequest(scenarioFormData),
                String.class);

        // Wait for server to receive
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the scenario from the crossroad
        Scenario scenario = restTemplate.getForEntity(crossroadsUrls.getProperty(crossroad) + "/scenario", Scenario.class).getBody();
        assertNotNull(scenario);

        assertEquals(crossroad, scenario.getId());
        assertEquals(5, scenario.getTransitionTime());

        List<RuleGroup> ruleGroups = scenario.getRuleGroupList();
        assertEquals(2, ruleGroups.size());
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
            assertEquals(5, ruleGroup.getMinimumGreenTime());
            assertEquals(5, ruleGroup.getNormalGreenTime());
            assertEquals(2, ruleGroup.getTrafficLights().size());
            assertContainsAll(ruleGroup.getTrafficLights(), "bas_gauche", "haut_droite");
        }
    }

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
