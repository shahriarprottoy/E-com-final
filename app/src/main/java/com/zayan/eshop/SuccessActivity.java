package com.zayan.eshop;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zayan.eshop.data.BackgroundOrderEngine;
import com.zayan.eshop.data.Order;
import com.zayan.eshop.ui.cart__fragment;

public class SuccessActivity extends AppCompatActivity {

    private BackgroundOrderEngine backgroundOrderEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        ProgressBar orderProgress = findViewById(R.id.orderProgress);
        TextView placeholder = findViewById(R.id.placeholder);

        // Order code starts here
        String tempName;
        Order order = new Order();
        for (int i = 0; i < MainActivity.cartProducts.size(); i++) {
            tempName = MainActivity.cartProducts.get(i).getName();
            tempName = tempName.replace("\"", "&:1:&");
            tempName = tempName.replace("[", "&:2:&");
            tempName = tempName.replace("]", "&:3:&");
            order.addProduct(tempName,
                    MainActivity.cartProducts.get(i).getDiscountedPrice(),
                    MainActivity.cartProducts.get(i).getStock(),
                    MainActivity.cartProducts.get(i).getQuantity(),
                    MainActivity.cartProducts.get(i).getTotalCost(),
                    MainActivity.cartProducts.get(i).isDeliveryTaken(),
                    MainActivity.cartProducts.get(i).isSetupTaken(),
                    MainActivity.cartProducts.get(i).getId());
        }

        backgroundOrderEngine = new BackgroundOrderEngine(orderProgress, placeholder);
        cart__fragment.RefreshTotal();
        backgroundOrderEngine.execute(order.getOrder(), MainActivity.userAccount.getUniqId(), String.valueOf(cart__fragment.total_amount));
        // Order code finishes here
    }

    @Override
    public void onBackPressed() {
        backgroundOrderEngine.cancel(true);
        super.onBackPressed();
    }
}
