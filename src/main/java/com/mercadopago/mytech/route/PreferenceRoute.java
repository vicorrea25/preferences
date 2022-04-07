package com.mercadopago.mytech.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class PreferenceRoute implements Route {

    private Request request;

    private Response response;

    public PreferenceRoute(){}


    public PreferenceRoute(Request request, Response response){
        this.request = request;
        this.response = response;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String body = request.body();

        return body;
    }
}
