package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zayans_eshop.data.Product;
import com.example.zayans_eshop.data.ViewPagerAdapter;
import com.example.zayans_eshop.ui.cart__fragment;


public class ProductDetails extends AppCompatActivity {

    // This is the product to show details of
    private Product product = new Product();
    private boolean fromCart;

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
        fromCart = intent.getBooleanExtra("fromCart", false);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, product.getImageUrls());
        viewPager.setAdapter(adapter);

        Button addToCartButton = findViewById(R.id.addtocart);
        if (fromCart) {
            addToCartButton.setVisibility(View.INVISIBLE);
        }
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cart__fragment.cartProducts.contains(product)) {
                    cart__fragment.cartProducts.add(product);
                } else {

                }
            }
        });

        TextView name = findViewById(R.id.name);
        name.setText("Name: " + product.getName());
        TextView price = findViewById(R.id.price);
        price.setText("Regular Price: " + product.getPrice());
        TextView offerPrice = findViewById(R.id.offerPrice);
        offerPrice.setText("Discounted Price: " + product.getDiscountedPrice());
        TextView details = findViewById(R.id.details);
        details.setText("Discounted Price: " + product.getDescription());

    }
}

