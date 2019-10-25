package pl.wydmuch.solvro.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.wydmuch.solvro.model.Stop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser<T> {

    @Value("${json.path}")
    private  String PATH;

    ObjectMapper objectMapper;

    @Autowired
    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<T> retrieveData(String nodeName, Class<T> contentClass) throws IOException {
        List<T> objectsList;
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
        JsonNode jsonNode = objectMapper.readTree(new File(PATH));
        JsonNode objectNode = jsonNode.get(nodeName);
        objectsList = objectMapper.readValue(objectNode.toString(),type);
        return objectsList;
    }
}
