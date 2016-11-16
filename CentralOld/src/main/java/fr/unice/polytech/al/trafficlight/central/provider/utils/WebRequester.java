package fr.unice.polytech.al.trafficlight.central.provider.utils;

/**
 * Created by rhoo on 14/11/16.
 */
public interface WebRequester {
    public String target(String urlType, String urlPath, String urlId, String path);

    public void put(String uri, Object entity);
}
