package com.mercadopago.preferences.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.preferences.auth.Authetication;
import com.mercadopago.preferences.utils.MapUtils;
import com.mercadopago.resources.Preference;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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

        Map<String, Object> preferenceMap = MapUtils.getRequestMap(request);
        String credentials = Authetication.getAuth(request);
        Preference preferenceEntity = (Preference) MapUtils.hashMapToObject(preferenceMap, Preference.class);
        preferenceEntity.setMarketplaceAccessToken(credentials);
        Preference savedPreference = savePreference(preferenceEntity);

        setReturn(savedPreference, response);

        return response.body();

    }

    private Preference savePreference(Preference preferenceEntity) throws MPException {
        return preferenceEntity.save();
    }

    private Preference getPreferenceById(String id) throws MPException {
        return Preference.findById(id);
    }

    public String getById(Request request, Response response) throws MPException, JsonProcessingException {
        Preference preference = new Preference();
        String id = request.params(":id");

        if(StringUtils.isBlank(id)) {
            throw new RuntimeException("Id nao informado");
        }

        preference.setMarketplaceAccessToken(Authetication.getAuth(request));
        setReturn(getPreferenceById(id), response);

        return response.body();
    }

    private void setReturn(Preference preferenceEntity, Response response) throws JsonProcessingException {
        Gson json = new Gson();

        response.body(json.toJson(preferenceEntity));
        response.type("application/json");

//        //deixar mais generico
//        response.status(200);
    }

    public Object update(Request request, Response response) throws Exception {
        Map<String, Object> preferenceMap = MapUtils.getRequestMap(request);
        String credentials = Authetication.getAuth(request);
        Preference preferenceEntity = (Preference) MapUtils.hashMapToObject(preferenceMap, Preference.class);
        preferenceEntity.setMarketplaceAccessToken(credentials);

        Preference updatedPreference = updatePreference(preferenceEntity, request);

        setReturn(updatedPreference, response);

        return response.body();
    }

    private Preference updatePreference(Preference preferenceChanged, Request request) throws MPException {

        if(StringUtils.isBlank(request.params(":id"))) {
            throw new RuntimeException("Id nao informado");
        }

        Preference savedPreference = getPreferenceById(request.params(":id"));

        if(savedPreference != null) {
            BeanUtils.copyProperties(preferenceChanged, savedPreference, "idempotenceKey");
            return savedPreference.update();
        }

        return new Preference();
    }
}
