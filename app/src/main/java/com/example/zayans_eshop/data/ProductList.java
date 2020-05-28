package com.example.zayans_eshop.data;

public class ProductList {

    public Product[] products;

    // Declares product array on the basis of no of products obtained from server
    public ProductList(int productNumber) {
        products = new Product[productNumber];
    }

    // Search for product by index
    public Product getProduct(int productIndex) {
        return this.products[productIndex];
    }

    // Search for product by name
    public Product getProduct(String productName) {
        int i = 0;
        while (i < length()) {
            if (products[i].getName().equals(productName)) {
                return this.products[i];
            } else {
                i++;
            }
        }
        return null;
    }

    // Get all the products
    public Product[] getAllProducts() {
        return this.products;
    }

    // Returns no of products obtained from database
    private int length() {
        return products.length;
    }
}