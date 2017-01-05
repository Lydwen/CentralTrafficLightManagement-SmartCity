package fr.unice.polytech.al.trafficlight.route.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

/**
 * Web requester.
 *
 * @author Kévin Buisson
 */
public class WebRequester {

    private Properties urlsConfig;

    /**
     * Creates a new web requester.
     */
    public WebRequester(String urlType) {
        try {
            this.urlsConfig = PropertiesLoaderUtils.loadProperties(
                    new ClassPathResource("/urls/" + urlType + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Specified URL's Type doesn't exists in configuration file : " + urlType);
        }
    }

    /**
     * Creates a String uri for the specified URL (by ID, in the configuration file).
     *
     * @param urlId   the url id (in config file)
     * @param urlPath the path you want to hit with your uri
     * @return the web target
     */
    public String target(String urlId, String urlPath) {
        // Get URLs from ID
        String url = urlsConfig.getProperty(urlId);

        // Check if URL exists
        if (url == null) {
            throw new RuntimeException("Specified URL's ID doesn't exists in configuration file : " + urlId);
        }

        // Set base URL
        return url + urlPath;
    }

    /**
     * Puts a request for the specified URL at the specified path.
     *
     * @param urlId   url's ID (in properties file)
     * @param urlPath url path to put
     * @param entity  the entity we want to put at the specifiied uri
     */
    public void put(String urlId, String urlPath, Object entity) {
        // Put the entity
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(
                this.target(urlId, urlPath), entity);
    }

    /**
     * Deletes for the specified URL at the specified path.
     *
     * @param urlId   url's ID (in properties file)
     * @param urlPath url path to delete
     */
    public void delete(String urlId, String urlPath) {
        // Delete the entity
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                this.target(urlId, urlPath));
    }
    // @TODO GET/POST if needed
}
