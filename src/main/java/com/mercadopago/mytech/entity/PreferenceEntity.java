package com.mercadopago.mytech.entity;

import com.mercadopago.resources.datastructures.preference.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceEntity {

    private String additionalInfo;

    private String autoReturn;

    private BackUrls backUrls;

    private String dateOfExpiration;

    private DifferentialPricing differentialPricing;

    private String expirationDateFrom;

    private String expirationDateTo;

    private Boolean expires;

    private String externalReference;

    private Item items;

    private String marketPlace;

    private float marketPlaceFee;

    private Object metaData;

    private String notificationUrl;

    private Payer payer;

    private PaymentMethods paymentMethods;

    private Shipments shipments;

    private String statementDescriptor;

    private Track tracks;

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAutoReturn() {
        return autoReturn;
    }

    public void setAutoReturn(String autoReturn) {
        this.autoReturn = autoReturn;
    }

    public BackUrls getBackUrls() {
        return backUrls;
    }

    public void setBackUrls(BackUrls backUrls) {
        this.backUrls = backUrls;
    }

    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public DifferentialPricing getDifferentialPricing() {
        return differentialPricing;
    }

    public void setDifferentialPricing(DifferentialPricing differentialPricing) {
        this.differentialPricing = differentialPricing;
    }

    public String getExpirationDateFrom() {
        return expirationDateFrom;
    }

    public void setExpirationDateFrom(String expirationDateFrom) {
        this.expirationDateFrom = expirationDateFrom;
    }

    public String getExpirationDateTo() {
        return expirationDateTo;
    }

    public void setExpirationDateTo(String expirationDateTo) {
        this.expirationDateTo = expirationDateTo;
    }

    public Boolean getExpires() {
        return expires;
    }

    public void setExpires(Boolean expires) {
        this.expires = expires;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }

    public String getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }

    public float getMarketPlaceFee() {
        return marketPlaceFee;
    }

    public void setMarketPlaceFee(float marketPlaceFee) {
        this.marketPlaceFee = marketPlaceFee;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public Shipments getShipments() {
        return shipments;
    }

    public void setShipments(Shipments shipments) {
        this.shipments = shipments;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public Track getTracks() {
        return tracks;
    }

    public void setTracks(Track tracks) {
        this.tracks = tracks;
    }



}
