package com.mercadopago.preferences.route;

import com.mercadopago.preferences.service.impl.PreferenceImpl;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Routes implements SparkApplication {

    public static final String PATH_PREFERENCES = "/preferences/";

    @Override
    public void init() {
        PreferenceImpl preferenceImpl = new PreferenceImpl();

        post(PATH_PREFERENCES.concat("create"), (request, response) -> preferenceImpl.create(request, response));

        get(PATH_PREFERENCES.concat(":id"), (request, response) -> preferenceImpl.getById(request, response));
        //post(PATH_PREFERENCES.concat("create"), (request, response) -> new PreferenceImpl(request, response));

        put(PATH_PREFERENCES.concat("/:id"), (request, response) -> preferenceImpl.update(request, response));

    };

}
