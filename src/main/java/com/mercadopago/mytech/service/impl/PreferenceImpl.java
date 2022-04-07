package com.mercadopago.mytech.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.mytech.auth.Authetication;
import com.mercadopago.mytech.utils.MapUtils;
import com.mercadopago.resources.Preference;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import spark.Request;
import spark.Response;
import java.util.*;

import static com.mercadopago.mytech.utils.ReturnUtils.setReturn;


public class PreferenceImpl {

    private Request request;

    private Response response;

    public PreferenceImpl(){}

    public PreferenceImpl(Request request, Response response){
        this.request = request;
        this.response = response;
    }


    public String create(Request request, Response response) throws Exception {
        // ver formas do de para
        // generics
        Map<String, Object> preferenceMap = MapUtils.getRequestMap(request);
        String credentials = Authetication.getAuth(request);
        Preference preferenceEntity = (Preference) MapUtils.hashMapToObject(preferenceMap, Preference.class);
        preferenceEntity.setMarketplaceAccessToken(credentials);
        Preference savedPreference = savePreference(preferenceEntity);

        setReturn(savedPreference, response, HttpStatus.SC_CREATED);

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
        setReturn(getPreferenceById(id), response, HttpStatus.SC_OK);

        return response.body();
    }



    public Object update(Request request, Response response) throws Exception {
        Map<String, Object> preferenceMap = MapUtils.getRequestMap(request);
        String credentials = Authetication.getAuth(request);
        Preference preferenceEntity = (Preference) MapUtils.hashMapToObject(preferenceMap, Preference.class);
        preferenceEntity.setMarketplaceAccessToken(credentials);

        Preference updatedPreference = updatePreference(preferenceEntity, request);

        setReturn(updatedPreference, response, HttpStatus.SC_OK);

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
        // validar como nao encontrado
        return new Preference();
    }
}
