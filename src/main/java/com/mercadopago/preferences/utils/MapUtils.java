package com.mercadopago.preferences.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import spark.Request;

import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    public static Object hashMapToObject(Map<String, Object> params, Class className) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Gson json = new Gson();

        String HashMapToJson = mapper.writeValueAsString(params);

        return json.fromJson(HashMapToJson, className);
    }

    public static Map<String, Object> getRequestMap(Request request) throws Exception {
        ObjectMapper mapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String body = request.body();
        if (body.length() == 0) {
            return new TreeMap<>();
        }
        return mapper.readValue(body, new TypeReference<Map<String, Object>>() {
        });
    }
}
