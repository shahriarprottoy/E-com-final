package com.zayan.eshop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zayan.eshop.R;

public class FAQactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_qactivity);
        final TextView q1, a1, q2, a2;
        q1 = findViewById(R.id.q1);
        a1 = findViewById(R.id.a1);
        q2 = findViewById(R.id.q2);
        a2 = findViewById(R.id.a2);

        a2.setVisibility(View.GONE);
        a1.setVisibility(View.GONE);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.VISIBLE);
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.GONE);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a2.setVisibility(View.VISIBLE);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a2.setVisibility(View.GONE);
            }
        });
    }
}