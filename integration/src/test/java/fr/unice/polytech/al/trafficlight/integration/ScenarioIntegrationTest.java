package fr.unice.polytech.al.trafficlight.integration;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

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
        final String scenario = "transitionTime=5&" +
                "nbGroups=4&" +
                "0=3&t0=gauche&t0=droite&" +
                "1=4&t1=haut&t1=bas&" +
                "2=5&t2=bas_gauche&t2=haut_droite&" +
                "3=6&t3=bas_droite&t3=haut_gauche";
        RestTemplate restTemplate = new RestTemplate();

        // Set the scenario using the webapp
        restTemplate.postForEntity(
                WEB_APP_URL + crossroad,
                buildScenarioRequest(scenario),
                String.class);

        // Wait for server to receive
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get the scenario from the crossroad
        //restTemplate.getForEntity()
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
