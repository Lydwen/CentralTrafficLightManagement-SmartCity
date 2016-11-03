package fr.unice.polytech.al.trafficlight.central.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.unice.polytech.al.trafficlight.central.data.CrossRoad;
import fr.unice.polytech.al.trafficlight.central.data.TrafficLight;
import fr.unice.polytech.al.trafficlight.utils.RuleGroup;
import fr.unice.polytech.al.trafficlight.utils.enums.TrafficLightId;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Created by Tom Dall'Agnol on 02/11/16.
 */
@JsonComponent
public class CrossRoadSerializer extends JsonSerializer<CrossRoad>{
    @Override
    public void serialize(CrossRoad crossRoad, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", crossRoad.getName());

        jsonGenerator.writeArrayFieldStart("roads");
        for(String road : crossRoad.getRoads()){
            jsonGenerator.writeString(road);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField("url", crossRoad.getUrl());
        jsonGenerator.writeArrayFieldStart("trafficLights");
        for(TrafficLight light : crossRoad.getTrafficLights()){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", light.getName());
            jsonGenerator.writeArrayFieldStart("accessibleArray");
            for(String road : light.getAccessibleRoads()){
                jsonGenerator.writeString(road);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeFieldName("scenario");

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", crossRoad.getScenario().getId());
        jsonGenerator.writeNumberField("transitionTime", crossRoad.getScenario().getTransitionTime());

        jsonGenerator.writeArrayFieldStart("ruleGroupList");
        for(RuleGroup group : crossRoad.getScenario().getRuleGroupList()){
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("id", group.getId());
            jsonGenerator.writeNumberField("greenTime", group.getGreenTime());

            jsonGenerator.writeArrayFieldStart("trafficLightList");
            for(TrafficLightId id : group.getTrafficLights()){
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", id.getId());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
