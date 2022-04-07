package com.mercadopago.mytech.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import spark.Response;

public class ReturnUtils {

    public static void setReturn(Object returnObject, Response response, int status) throws JsonProcessingException {
        Gson json = new Gson();

        response.body(json.toJson(returnObject));
        response.type("application/json");

        response.status(status);
    }
}
