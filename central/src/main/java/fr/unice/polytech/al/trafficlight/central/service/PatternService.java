package fr.unice.polytech.al.trafficlight.central.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.unice.polytech.al.trafficlight.central.dao.CarrefourDao;
import fr.unice.polytech.al.trafficlight.central.provider.Carrefour;

@Path("/crossroads")
public class PatternService {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Carrefour> getCrossroadsList() {
		return CarrefourDao.getCrossroadsList();
	}
	
	
}
