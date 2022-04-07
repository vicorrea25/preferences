package com.mercadopago.mytech.auth;

import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPCredentials;
import com.mercadopago.exceptions.MPException;
import org.apache.commons.lang.StringUtils;
import spark.Request;

public class Authetication {

    public static String getAuth(Request request) throws MPException {
        String clientId = request.headers("clientId");
        String clientSecret = request.headers("clientSecret");


        if(StringUtils.isBlank(clientId) || StringUtils.isBlank(clientSecret)) {
            throw new RuntimeException("credenciais nao informadas. Informar clientId e clientSecret");
        }

        MercadoPago.SDK.setClientId(clientId);
        MercadoPago.SDK.setClientSecret(clientSecret);

        return MPCredentials.getAccessToken();

    }
}
