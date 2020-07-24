package com.example.zayans_eshop.data;

import org.json.JSONArray;

public class Order {
    private JSONArray productArray;
    private JSONArray product;
    private int numberOfProducts;

    public Order(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
        product = new JSONArray();
        productArray = new JSONArray();
    }

    public void addProduct(String name, int price, int stock, int quantity) {
        product.put(name);
        product.put(String.valueOf(price));
        product.put(String.valueOf(quantity));
        product.put(String.valueOf(stock));
        productArray.put(product);
        product = new JSONArray();
    }


    public String getOrder() {
        return productArray.toString();
    }
}
