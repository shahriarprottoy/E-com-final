package com.example.zayans_eshop.data;

import org.json.JSONArray;

public class Order {
    private JSONArray productArray;
    private JSONArray product;

    public Order() {
        product = new JSONArray();
        productArray = new JSONArray();
    }

    public void addProduct(String name, int price, int stock, int quantity, int totalCost, boolean deliveryTaken, boolean setupTaken) {
        product.put(name);
        product.put(String.valueOf(price));
        product.put(String.valueOf(quantity));
        product.put(String.valueOf(stock));
        product.put(String.valueOf(totalCost));
        product.put(deliveryTaken?"Yes":"No");
        product.put(setupTaken? "Yes":"No");
        productArray.put(product);
        product = new JSONArray();
    }


    public String getOrder() {
        return productArray.toString();
    }
}
