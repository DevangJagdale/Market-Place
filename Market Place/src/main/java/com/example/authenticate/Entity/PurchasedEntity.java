package com.example.authenticate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="purchased_data")
public class PurchasedEntity {
    @Id
    private String id;

    private String buyerId;
    private String sellerId;
    private String productId;

    public PurchasedEntity(){}
    public PurchasedEntity(String id, String buyerId, String sellerId, String productId) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
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
}
