package com.test.terminal_service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
@Service
public class JsonService {

    public void printJsonFromFile(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        printJson(json);
    }

    public void printJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        printNode(rootNode, "");
    }
    public void findMaxFromJsonFile(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        findMaxValue(json);
    }

    public void findMaxValue(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        Map.Entry<String, Integer> maxEntry = findMaxValue(rootNode, "");
        System.out.println("Correct answer is: " + maxEntry.getKey() + ": " + maxEntry.getValue());
    }

    private void printNode(JsonNode node, String indent) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            System.out.println(indent + entry.getKey());

            if (entry.getValue().isObject()) {
                printNode(entry.getValue(), indent + ".. ");
            } else {
                System.out.println(indent + ".. " + entry.getKey());
            }
        }
    }

    private Entry<String, Integer> findMaxValue(JsonNode node, String path) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        Entry<String, Integer> maxEntry = null;

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();

            if (entry.getValue().isObject()) {
                Entry<String, Integer> subMaxEntry = findMaxValue(entry.getValue(), path + entry.getKey() + " -> ");
                if (maxEntry == null || subMaxEntry.getValue() > maxEntry.getValue()) {
                    maxEntry = subMaxEntry;
                }
            } else {
                int value = entry.getValue().asInt();
                if (maxEntry == null || value > maxEntry.getValue()) {
                    maxEntry = Map.entry(path + entry.getKey(), value);
                }
            }
        }

        return maxEntry;
    }
}
