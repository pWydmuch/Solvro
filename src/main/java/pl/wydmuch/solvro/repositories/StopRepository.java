package pl.wydmuch.solvro.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wydmuch.solvro.dto.StopDto;
import pl.wydmuch.solvro.model.Stop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StopRepository {


    ObjectMapper objectMapper;

    @Autowired
    public StopRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Stop> findAll() throws IOException {

        List<Stop> stops = new ArrayList<>();

            JsonNode jsonNode = objectMapper.readTree(new File("backend/solvro_city.json"));
            JsonNode stopsNode = jsonNode.get("nodes");
            stops = objectMapper.readValue(stopsNode.toString(), new TypeReference<List<Stop>>() {
            });
            return stops;

    }
}
