package com.example.zayans_eshop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.R;

public class FAQactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_qactivity);
        final TextView q1,a1;
        q1=findViewById(R.id.q1);
        a1=findViewById(R.id.a1);
        a1.setVisibility(View.INVISIBLE);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.VISIBLE);
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.INVISIBLE);
            }
        });
    }
}