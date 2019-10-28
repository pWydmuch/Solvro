package pl.wydmuch.solvro.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wydmuch.solvro.model.Stop;
import pl.wydmuch.solvro.util.JsonParser;
import java.io.IOException;
import java.util.List;


@Repository
public class StopRepository {

    private static final String STOPS_JSON_NODE = "nodes";

    JsonParser<Stop> parser;

    @Autowired
    public StopRepository(JsonParser<Stop> parser) {
        this.parser = parser;
    }

    public List<Stop> findAll() throws IOException {
        return parser.retrieveData(STOPS_JSON_NODE, Stop.class);
    }

    public Stop findByName(String name) throws IOException {
        return findAll().stream()
                .filter(stop -> stop.getName().equals(name))
                .findFirst()
                .get();
    }
}
