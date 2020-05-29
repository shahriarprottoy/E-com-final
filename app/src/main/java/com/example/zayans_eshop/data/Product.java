package com.example.zayans_eshop.data;

public class Product {

    private String name;
    private int price;
    private int stock;
    // The variables which DO NOT contain image URL will be blank
    // e.g. image3Url == "";
    private String image1Url;
    private String image2Url;
    private String image3Url;

    public Product(String name, int price, int stock,String image1Url,String image2Url,String image3Url) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image1Url = image1Url;
        this.image2Url = image2Url;
        this.image3Url = image3Url;
    }

    public void setProductData(String name, int price, int stock, String image1Url, String image2Url, String image3Url) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image1Url = image1Url;
        this.image2Url = image2Url;
        this.image3Url = image3Url;
    }

    // Refer to these functions for getting and setting

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

    public void setStock(int price) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    public String getImage1Url() {
        return this.image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return this.image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public String getImage3Url() {
        return this.image3Url;
    }

    public void setImage3Url(String image3Url) {
        this.image3Url = image3Url;
    }

}