package pl.wydmuch.solvro.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wydmuch.solvro.model.Link;
import pl.wydmuch.solvro.util.JsonParser;

import java.io.IOException;
import java.util.List;

@Repository
public class LinkRepository {

    private static final String LINKS_JSON_NODE = "links";

    JsonParser<Link> parser;

    @Autowired
    public LinkRepository(JsonParser<Link> parser) {
        this.parser = parser;
    }

    public List<Link> findAll() throws IOException {
        return parser.retrieveData(LINKS_JSON_NODE, Link.class);
    }

}
