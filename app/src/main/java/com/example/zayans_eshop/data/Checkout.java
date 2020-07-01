package com.example.zayans_eshop.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.R;

public class Checkout extends AppCompatActivity {

    private Button btnCheckout;
    private EditText etAmount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutscreen);

        etAmount = findViewById(R.id.etAmount);
        btnCheckout = findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });

    }

    private void checkInputs() {
        String amountInString = etAmount.getText().toString().trim();

        //to check amount validity
        double amount = 0.0;

        try {
            amount = Double.parseDouble(amountInString);   // use try catch so that, if input is invalid, stop taking payment here.
        } catch (Exception e) {
            amount = 0.0;
        }

        if (amount < 1) {
            etAmount.setError("You have to pay at least BDT 1. ");
            etAmount.requestFocus();
            return;
        }   // here you need to check internet connection on another condition like if(is_online)
        else {
            Intent intent = new Intent(Checkout.this, BkashActivity.class);
            intent.putExtra("AMOUNT", String.valueOf(amount));  //sent amount to bkash activity
            startActivity(intent);
        }

    }
}

