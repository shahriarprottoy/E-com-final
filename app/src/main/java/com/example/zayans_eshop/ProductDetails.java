package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zayans_eshop.data.Product;


public class ProductDetails extends AppCompatActivity {

    // This is the product to show details of
    private Product product = new Product();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.product_details);

            Intent intent = getIntent();

            // Setting product data from passed data
            product.setName(intent.getStringExtra("name"));
            product.setPrice(intent.getIntExtra("price", 0));
            product.setStock(intent.getIntExtra("stock", 0));
            product.setDiscountedPrice(intent.getIntExtra("discPrice", 0));
            product.setDescription(intent.getStringExtra("description"));
            product.setImage1Url(intent.getStringExtra("im1"));
            product.setImage2Url(intent.getStringExtra("im2"));
            product.setImage3Url(intent.getStringExtra("im3"));

            //
            // You do the rest
            //
        }
    }

