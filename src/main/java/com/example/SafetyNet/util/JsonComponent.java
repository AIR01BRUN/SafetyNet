package com.example.safetynet.util;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;

@Component
public class JsonComponent {

    private ObjectMapper objectMapper = new ObjectMapper();
    private String filePath = "src/main/resources/data.json";
    private File dataFile = new File(filePath);

    public <E> List<E> getFromJson(Class<E> type, String fieldName) {
        List<E> list = null;

        try {
            JsonNode root = objectMapper.readTree(dataFile);

            JsonNode typeArray = root.get(fieldName);

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            list = objectMapper.readerFor(listType).readValue(typeArray);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public <E> void updateJson(String typeName, List<E> newList) {
        try {

            JsonNode root = objectMapper.readTree(dataFile);

            JsonNode updatedArray = objectMapper.valueToTree(newList);

            if (root instanceof ObjectNode objectNode) {
                objectNode.set(typeName, updatedArray);

                objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, objectNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
