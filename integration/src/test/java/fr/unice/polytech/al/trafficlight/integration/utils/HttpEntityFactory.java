package fr.unice.polytech.al.trafficlight.integration.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

/**
 * Http entity factory.
 */
public abstract class HttpEntityFactory {
    public static HttpEntity<MultiValueMap<String, String>> createFromFormData(String formData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create map form data from the specified scenario
        MultiValueMap<String, String> formDataMap = new LinkedMultiValueMap<>();
        Arrays.stream(formData.split("&")).forEach(
                v -> {
                    String[] values = v.split("=");
                    formDataMap.add(values[0], values[1]);
                });

        return new HttpEntity<>(formDataMap, headers);
    }
}
