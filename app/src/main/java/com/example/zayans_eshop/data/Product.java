package com.example.zayans_eshop.data;

import android.util.Log;

public class Product {

    private String name;
    private int price;
    private int stock;
    private int quantity = 1;
    private int discountedPrice;
    private String description;
    private int setupCost;
    private int deliveryCost;
    private int totalCost;

    private boolean deliveryTaken = true;
    private boolean setupTaken = true;

    // The variables which DO NOT contain image URL will be blank
    // e.g. image3Url == "";
    private String[] imageUrls = new String[3];

    // Empty constructor
    public Product() {

    }

    Product(String name, int price, int stock,
            String description, String image1Url, String image2Url, String image3Url,
            int discountedPrice, int setupCost, int deliveryCost) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrls[0] = image1Url;
        this.imageUrls[1] = image2Url;
        this.imageUrls[2] = image3Url;
        this.discountedPrice = discountedPrice;
        this.setupCost = setupCost;
        this.deliveryCost = deliveryCost;
    }

    // Refer to these functions for getting and setting

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    String getImage1Url() {
        return this.imageUrls[0];
    }

    public void setImage1Url(String image1Url) {
        this.imageUrls[0] = image1Url;
    }


    String getImage2Url() {
        return this.imageUrls[1];
    }

    public void setImage2Url(String image2Url) {
        this.imageUrls[1] = image2Url;
    }


    String getImage3Url() {
        return this.imageUrls[2];
    }

    public void setImage3Url(String image3Url) {
        this.imageUrls[2] = image3Url;
    }


    public String[] getImageUrls() {
        return imageUrls;
    }


    public int getSetupCost() {
        return setupCost;
    }

    public void setSetupCost(int setupCost) {
        this.setupCost = setupCost;
    }


    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }


    public boolean isDeliveryTaken() {
        return deliveryTaken;
    }

    public void setDeliveryTaken(boolean deliveryTaken) {
        this.deliveryTaken = deliveryTaken;
    }

    public boolean isSetupTaken() {
        return setupTaken;
    }

    public void setSetupTaken(boolean setupTaken) {
        this.setupTaken = setupTaken;
    }


    public int getTotalCost() {
        totalCost = discountedPrice*quantity;
        totalCost += deliveryTaken ? deliveryCost*quantity : 0 ;
        totalCost += setupTaken ? setupCost*quantity : 0 ;
        return  totalCost;
    }
}