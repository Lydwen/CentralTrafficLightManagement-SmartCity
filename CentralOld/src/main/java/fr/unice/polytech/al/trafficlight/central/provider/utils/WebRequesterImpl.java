package fr.unice.polytech.al.trafficlight.central.provider.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Web requester.
 *
 * @author Kévin Buisson
 */
@Component
public class WebRequesterImpl implements WebRequester {




    /**
     * Creates a String uri for the specified URL (by ID, in the configuration file).
     *
     * @param urlId the url id (in config file)
     * @param path  the path
     * @param urlType the name of the properties file with all the URI for your specific type
     * @param urlPath the path you want to hit with your uri
     * @return the web target
     */
    public String target(String urlType, String urlPath, String urlId, String path) {
        Properties urlsConfig = null;
        try {
            Resource resource = new ClassPathResource("/urls/"+urlType + ".properties");
            urlsConfig = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            //can't load the property file
            e.printStackTrace();
            throw new RuntimeException("Specified URL's Type doesn't exists in configuration file : "+urlType);
        }

        // Get URLs from ID
        String url = urlsConfig.getProperty(urlId);

        if (url == null) {
            throw new RuntimeException("Specified URL's ID doesn't exists in configuration file : "+urlId);
        }

        // Set base URL
       return url+"/"+urlPath+"/"+ path;
    }

    /**
     * Puts a request for the specified URL at the specified path.
     *
     * @param entity the entity we want to put at the specifiied uri
     */
    public void put(String uri, Object entity) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(uri, entity);
    }

    // @TODO GET/POST/DELETE if needed
}
