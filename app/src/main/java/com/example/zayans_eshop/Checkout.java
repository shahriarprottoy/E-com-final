package com.example.zayans_eshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.data.AdapterCartProduct;
import com.example.zayans_eshop.data.AdapterOrderProduct;

public class Checkout extends AppCompatActivity {

    public static Activity checkoutActivity;
    private Button btnCheckout;
    private TextView phoneNumber;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutscreen);
        checkoutActivity = this;

        ListView cartListView = findViewById(R.id.order_product_list);
        AdapterOrderProduct mAdapter = new AdapterOrderProduct(Checkout.this, MainActivity.cartProducts);
        cartListView.setAdapter(mAdapter);

        phoneNumber = findViewById(R.id.phone_placeholder);
        location = findViewById(R.id.location_placeholder);
        btnCheckout = findViewById(R.id.btnCheckout);

        phoneNumber.setText(MainActivity.userAccount.getUserPhone());
        location.setText(MainActivity.userAccount.getUserLocation());

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Checkout.this, SuccessActivity.class);
                startActivity(intent);
            }
        });
    }

}

