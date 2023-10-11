package com.example.authenticate.model;

public class ProductDetail {

    private String id;
    private String sellerId;
    private String name;
    private float price;
    private String description;
    private  String address;
    private Boolean isSold=false;


    public ProductDetail(){}

    public ProductDetail(String id,String sellerId,String name, float price, String description, String address, Boolean isSold) {
        this.id=id;
        this.sellerId=sellerId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.address = address;
        this.isSold = isSold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

}
