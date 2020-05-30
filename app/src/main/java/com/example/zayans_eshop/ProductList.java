package com.example.zayans_eshop;

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

        String category = getIntent().getStringExtra("category");

        DbRetriever retriever = new DbRetriever(this, mAdapter);
        retriever.execute(category);
    }
}