package fr.unice.polytech.al.trafficlight.central.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Web requester.
 *
 * @author Kévin Buisson
 */
public class WebRequester {
    /**
     * URLs files configurations location.
     */
    public static final Path URLS_FILE_DIR = Paths.get("./urls/");

    /**
     * Logger.
     */
    //private final static Logger logger = LogManager.getLogger(WebRequester.class);

    /**
     * JSON serializer/deserializer.
     */
    private static Gson gson = new GsonBuilder().create();

    /**
     * URLs configuration.
     */
    private Properties urlsConfig = new Properties();

    /**
     * URL base path.
     */
    private String urlPath;

    /**
     * Instantiates a new web requester.
     */
    public WebRequester(String urlType, String urlPath) {
        try {
            // Load the configuration file with URLs
            this.loadUrls(URLS_FILE_DIR.resolve(urlType + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.urlPath = urlPath;
    }

    /**
     * Loads URLs configuration from specified configuration path.
     *
     * @param urlsConfigPath path to configuration file
     * @throws IOException if IO error occurs
     */
    public void loadUrls(Path urlsConfigPath) throws IOException {
        //logger.info("Loading URLs configuration : {}",
        //        () -> getClass().getClassLoader().getResource(urlsConfigPath.toString()));

        try (InputStream fis = getClass().getClassLoader()
                // Get resource from class loader
                .getResourceAsStream(urlsConfigPath.toString())) {
            // Load properties
            urlsConfig.load(fis);
        }
    }

    // ================================

    /**
     * Creates a web target for the specified URL (by ID, in the configuration file).
     *
     * @param urlId the url id (in config file)
     * @param path  the path
     * @return the web target
     */
    public WebTarget target(String urlId, String path) {
        // Get URLs from ID
        String url = this.urlsConfig.getProperty(urlId);
        if (url == null) {
            throw new RuntimeException("Specified URL's ID doesn't exists in configuration file.");
        }

        // Create target
        return ClientBuilder.newClient()
                .target(url) // Set base URL
                .path(this.urlPath + path); // Set path
    }

    /**
     * Puts a request for the specified URL (by ID, in the configuration file), at the specified path.
     *
     * @param urlId  the url id (in config file)
     * @param path   the path
     * @param entity the entity
     * @return the response
     */
    public Response put(String urlId, String path, Object entity) {
        // Convert entity to JSON
        Entity requestEntity = Entity.entity(gson.toJson(entity), MediaType.APPLICATION_JSON);

        // Call the target path
        return target(urlId, path)
                .request(MediaType.APPLICATION_JSON)
                .put(requestEntity, Response.class);
    }

    // @TODO GET/POST/DELETE if needed
}
