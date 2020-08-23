package com.example.zayans_eshop;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.data.BackgroundOrderEngine;
import com.example.zayans_eshop.data.Order;

public class SuccessActivity extends AppCompatActivity {

    private ProgressBar orderProgress;
    private TextView placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        orderProgress = findViewById(R.id.orderProgress);
        placeholder = findViewById(R.id.placeholder);

        // Order code starts here
        String tempName;
        Order order = new Order(MainActivity.cartProducts.size());
        for (int i = 0; i < MainActivity.cartProducts.size(); i++) {
            tempName = MainActivity.cartProducts.get(i).getName();
            tempName = tempName.replace("\"", "&:1:&");
            tempName = tempName.replace("[", "&:2:&");
            tempName = tempName.replace("]", "&:3:&");
            order.addProduct(tempName,
                    MainActivity.cartProducts.get(i).getDiscountedPrice(),
                    MainActivity.cartProducts.get(i).getStock(),
                    MainActivity.cartProducts.get(i).getQuantity());
        }

        BackgroundOrderEngine backgroundOrderEngine = new BackgroundOrderEngine(this, orderProgress, placeholder);
        backgroundOrderEngine.execute(order.getOrder(), MainActivity.userAccount.getUniqId());
        // Order code finishes here
    }
}
