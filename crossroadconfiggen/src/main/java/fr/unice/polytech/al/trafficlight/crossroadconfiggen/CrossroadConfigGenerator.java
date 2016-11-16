package fr.unice.polytech.al.trafficlight.crossroadconfiggen;

import com.google.gson.Gson;
import fr.unice.polytech.al.trafficlight.utils.TrafficLightId;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nathael on 16/11/16.
 */
public class CrossroadConfigGenerator {

    public static void main(String[] args) {
        Set<TrafficLightId> idList = new HashSet<>();

        // Check arguments
        if(args.length < 2) {
            System.err.println("Need at least 2 arguments separated by spaces:\n"
            +" 1st argument : Name for Generated file\n"
            +" 2nd argument : first TrafficLightId\n"
            +"(nth argument): others TrafficLightIds");
            return;
        }


        for(int i=1; i<args.length; i++)
            idList.add(new TrafficLightId(args[i]));

        String json = new Gson().toJson(idList);
        File f = new File(args[0]);
        try {
            f.createNewFile();

            // write Json of idList in file
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.flush();
            System.out.printf("Generated json:\n"+json);
        } catch (IOException e) {
            System.err.println("Impossible to create config file: Aborted");
            e.printStackTrace();
        }
    }
}
