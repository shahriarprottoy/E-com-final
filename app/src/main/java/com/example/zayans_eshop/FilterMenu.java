package com.example.zayans_eshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class FilterMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);

        final TextInputEditText maximumfield= findViewById(R.id.maximum_filter_field);
        final TextInputEditText minimumfield= findViewById(R.id.minimum_filter_field);

    }
}