package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zayans_eshop.data.DbRetriever;
import com.example.zayans_eshop.data.ProductAdapter;

public class ProductList extends AppCompatActivity {

    private ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        
        Intent intent = getIntent();
        String argument = null;

        if (!(intent.getStringExtra("search") == null))
        argument = getIntent().getStringExtra("search");

        if (!(intent.getStringExtra("category") == null))
        argument = getIntent().getStringExtra("category");

        DbRetriever retriever = new DbRetriever(this, mAdapter);
        
        retriever.execute(argument);
    }
}