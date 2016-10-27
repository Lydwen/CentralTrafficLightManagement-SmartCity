package fr.unice.polytech.al.trafficlight.central.dao;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.al.trafficlight.central.provider.Carrefour;
import fr.unice.polytech.al.trafficlight.central.provider.Group;


public class CarrefourDao {

	public static List<Carrefour> getCrossroadsList() {
		// Provide data to test. After, we will get data from database.
		
		List<Carrefour> carrefourList = new ArrayList<Carrefour>();
		carrefourList.add(new Carrefour(1, "Inria"));
		carrefourList.add(new Carrefour(1, "Templiers"));
		carrefourList.add(new Carrefour(1, "Gare Routière de Valbonne Sophia Antipolis"));
		return carrefourList;
	}
}
