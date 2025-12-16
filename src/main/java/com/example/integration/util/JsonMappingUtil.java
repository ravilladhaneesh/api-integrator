package com.example.integration.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonMappingUtil {
    public static String getValue(JsonNode node, String path) {
        return node.at(path.replace("$", "")).asText();
    }
}