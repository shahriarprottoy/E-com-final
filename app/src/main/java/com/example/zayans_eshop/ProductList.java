package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

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
        actionBar.setTitle(Html.fromHtml("<font color='#2399DD'>ayan's Megashop</font>"));
        
        Intent intent = getIntent();
        String argument = null;

        if (!(intent.getStringExtra("search") == null))
        argument = getIntent().getStringExtra("search");

        if (!(intent.getStringExtra("category") == null))
        argument = getIntent().getStringExtra("category");

        BackgroundProductRetrieverEngine retriever = new BackgroundProductRetrieverEngine(this, mAdapter);
        
        retriever.execute(argument);
    }
}