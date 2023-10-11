package com.example.authenticate.model;

public class Offer {
    private String id;
    private String buyerId;
    private  String sellerId;
    private  String productId;
    private String offer;

    public Offer() {
    }

    public Offer(String id, String buyerId, String sellerId, String productId, String offer) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.offer = offer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
