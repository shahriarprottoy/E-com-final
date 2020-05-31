package com.example.zayans_eshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zayans_eshop.data.Product;
import com.squareup.picasso.Picasso;


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

            TextView name =(TextView) findViewById(R.id.name);
            name.setText("Name: " + product.getName());
            TextView price = (TextView) findViewById(R.id.price);
            price.setText("Regular Price: " + product.getPrice());
            TextView offerPrice =(TextView) findViewById(R.id.offerPrice);
            offerPrice.setText("Discounted Price: " + product.getDiscountedPrice());
            TextView details =(TextView) findViewById(R.id.details);
            details.setText("Discounted Price: " + product.getDescription());
            ImageView image1 =(ImageView) findViewById(R.id.image1);
            if (product.getImage1Url() != "")
                Picasso.with(this).load(Uri.parse(product.getImage1Url())).into(image1);
            ImageView image2 =(ImageView) findViewById(R.id.image2);
            if (product.getImage1Url() != "")
                Picasso.with(this).load(Uri.parse(product.getImage1Url())).into(image2);
            ImageView image3 =(ImageView) findViewById(R.id.image3);
            if (product.getImage1Url() != "")
                Picasso.with(this).load(Uri.parse(product.getImage1Url())).into(image3);

        }
    }

