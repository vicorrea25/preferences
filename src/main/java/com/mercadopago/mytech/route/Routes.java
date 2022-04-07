package com.mercadopago.mytech.route;

import com.mercadopago.mytech.service.impl.PaymentsImpl;
import com.mercadopago.mytech.service.impl.PreferenceImpl;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Routes implements SparkApplication {

    public static final String PATH_PREFERENCES = "/preferences/";

    public static final String PATH_PAYMENTS = "/payments/";

    @Override
    public void init() {
        PreferenceImpl preference = new PreferenceImpl();
        PaymentsImpl payments = new PaymentsImpl();

        post(PATH_PREFERENCES.concat("create"), (request, response) -> preference.create(request, response));

        get(PATH_PREFERENCES.concat(":id"), (request, response) -> preference.getById(request, response));

        put(PATH_PREFERENCES.concat("/:id"), (request, response) -> preference.update(request, response));


        // PAYMENTS
        post(PATH_PAYMENTS.concat("create"), (request, response) -> payments.create(request, response));




    };

}
// PATH pesquisar
