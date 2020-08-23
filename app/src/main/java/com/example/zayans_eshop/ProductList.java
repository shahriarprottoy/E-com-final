package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.data.AdapterProduct;
import com.example.zayans_eshop.data.BackgroundProductRetrieverEngine;

public class ProductList extends AppCompatActivity {

    private AdapterProduct mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(Html.fromHtml("<font color='#2399DD'>eShop</font>"));
        
        Intent intent = getIntent();
        String argument = null;

        if (!(intent.getStringExtra("search") == null))
        argument = getIntent().getStringExtra("search");

        if (!(intent.getStringExtra("category") == null))
        argument = getIntent().getStringExtra("category");

        ProgressBar progressBar = findViewById(R.id.progressBar);
        GridView gridView = findViewById(R.id.product_list);

        BackgroundProductRetrieverEngine retriever = new BackgroundProductRetrieverEngine(this, mAdapter);
        
        retriever.execute(argument);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}