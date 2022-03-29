package com.mercadopago.preferences.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPCredentials;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import org.apache.commons.lang.StringUtils;
import spark.Request;
import spark.Response;
import java.util.*;


public class PreferenceImpl {

    private Request request;

    private Response response;

    public PreferenceImpl(){}

    public PreferenceImpl(Request request, Response response){
        this.request = request;
        this.response = response;
    }


    public String create(Request request, Response response) throws Exception {

        Map<String, Object> preferenceHashMap = getRequestMap(request);
        String credentials = getAuth(request);
        Preference preferenceEntity = HashMapToObjectPreference(preferenceHashMap);
        preferenceEntity.setMarketplaceAccessToken(credentials);
        Preference savedPreference = savePreference(preferenceEntity);
        setReturn(savedPreference, response);
        return response.body();

    }

    private String getAuth(Request request) throws MPException {
        String clientId = request.headers("clientId");
        String clientSecret = request.headers("clientSecret");

        MercadoPago.SDK.setClientId(clientId);
        MercadoPago.SDK.setClientSecret(clientSecret);

        return MPCredentials.getAccessToken();

    }

    private void setReturn(Preference preferenceEntity, Response response) throws JsonProcessingException {
        Gson json = new Gson();

        response.body(json.toJson(preferenceEntity));

//        //deixar mais generico
//        response.status(200);

    }

    private Preference savePreference(Preference preferenceEntity) throws MPException {
        Preference.findById("123");
        return preferenceEntity.save();
    }

    private Preference getPreferenceById(String id) throws MPException {
        return Preference.findById(id);
    }

    private Preference HashMapToObjectPreference(Map<String, Object> params) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Gson json = new Gson();

        String HashMapToJson = mapper.writeValueAsString(params);

        return json.fromJson(HashMapToJson, Preference.class);
    }

    public final ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    private Map<String, Object> getRequestMap(Request request) throws Exception {
        String body = request.body();
        if (body.length() == 0) {
            return new TreeMap<>();
        }
        return mapper.readValue(body, new TypeReference<Map<String, Object>>() {
        });
    }

    public String getById(Request request, Response response) throws MPException, JsonProcessingException {
        Preference preference = new Preference();

        String id = request.params(":id");

        if(StringUtils.isBlank(id)) {
            throw new RuntimeException("Id nao informado");
        }
        preference.setMarketplaceAccessToken(getAuth(request));

        setReturn(getPreferenceById(id), response);

        return response.body();
    }
}
