package com.mercadopago.mytech.service.impl;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.mytech.utils.MapUtils;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.Map;

public class PaymentsImpl {

    private Request request;

    private Response response;

    public PaymentsImpl(){}

    public PaymentsImpl(Request request, Response response){
        this.request = request;
        this.response = response;
    }


    public String create(Request request, Response response) throws Exception {
        Payment createdPayment = null;
        Map<String, Object> paymentMap = MapUtils.getRequestMap(request);
        Payment paymentEntity = (Payment) MapUtils.hashMapToObject(paymentMap, Payment.class);

        if (paymentEntity.getPaymentMethodId().contains("bol")) {
            createdPayment = createPaymentTicket(paymentEntity);

        } else {
            createdPayment = createPaymentCreditCard(paymentEntity);

        }
        String tickerString = String.valueOf(createdPayment.getLastApiResponse().getJsonElementResponse());

        response.body(tickerString);
        response.type("application/json");
        response.status(createdPayment.getLastApiResponse().getStatusCode());

        return response.body();

    }

    private Payment createPaymentTicket(Payment paymentEntity) throws MPException {
        MercadoPago.SDK.setAccessToken("TEST-408487475059352-040620-5f62ceda7c2b07be8a788d277ab17adf-1102593865");

        Payment payment = new Payment()
                .setTransactionAmount(paymentEntity.getTransactionAmount())
                .setDescription(paymentEntity.getDescription())
                .setPaymentMethodId(paymentEntity.getPaymentMethodId())
                .setPayer(new Payer()
                        .setEmail(paymentEntity.getPayer().getEmail())
                        .setFirstName(paymentEntity.getPayer().getFirstName())
                        .setLastName(paymentEntity.getPayer().getLastName())
                        .setIdentification(new Identification().setNumber(paymentEntity.getPayer().getIdentification().getNumber())
                        .setType(paymentEntity.getPayer().getIdentification().getType())));

        return payment.save();
    }

    private Payment createPaymentCreditCard(Payment paymentEntity) throws MPException {
        MercadoPago.SDK.setAccessToken("TEST-3160604115847479-040422-ec6b9f8d1919f8e17c16ef5a26dc9206-1101391960");

        Payment payment = new Payment()
                .setTransactionAmount(paymentEntity.getTransactionAmount())
                .setToken("TEST-5072708572575828-033020-5847848eb120f551f1b39a19d5c83117-1098058826") //payer
                .setDescription(paymentEntity.getDescription())
                .setInstallments(paymentEntity.getInstallments())
                .setPaymentMethodId(paymentEntity.getPaymentMethodId())
                .setPayer(new Payer()
                        .setEmail(paymentEntity.getPayer().getEmail()));

        return payment.save();
    }
}
