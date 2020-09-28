package com.zayan.eshop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zayan.eshop.data.AdapterOrderProduct;
import com.zayan.eshop.ui.cart__fragment;

public class Checkout extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Activity checkoutActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutscreen);
        checkoutActivity = this;

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        ListView cartListView = findViewById(R.id.order_product_list);
        AdapterOrderProduct mAdapter = new AdapterOrderProduct(Checkout.this, MainActivity.cartProducts);
        cartListView.setAdapter(mAdapter);

        TextView total = findViewById(R.id.checkout_screen_total);
        TextView phoneNumber = findViewById(R.id.phone_placeholder);
        TextView location = findViewById(R.id.location_placeholder);
        Button btnCheckout = findViewById(R.id.btnCheckout);

        cart__fragment.RefreshTotal();
        total.setText("TK. " + String.valueOf(cart__fragment.total_amount));
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

