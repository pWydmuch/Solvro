package pl.wydmuch.solvro.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.model.Stop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonReaderSandBox {

    public static void main(String[] args) {
        new JsonReaderSandBox().ddo();
    }

    public void ddo(){
        ObjectMapper objectMapper = new ObjectMapper();
        StopDto stopDto = new StopDto("dupa");
        List<Stop> stops = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File("backend/solvro_city.json"));
            JsonNode stopsNode = jsonNode.get("nodes");
//            System.out.println(stopsNode);
            stops = objectMapper.readValue(stopsNode.toString(), new TypeReference<List<Stop>>(){});
            System.out.println(stops.get(0));
//            objectMapper.writeValue(new File("./dupa.json"),stopDto);
//            Stop s = objectMapper.readValue(new File("./backend/solvro_city.json"), Stop.class);
//            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public StopDto[] findAll(){
//
//    }
}
