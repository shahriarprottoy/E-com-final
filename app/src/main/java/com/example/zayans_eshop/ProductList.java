package com.example.zayans_eshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.data.AdapterProduct;
import com.example.zayans_eshop.data.BackgroundProductRetrieverEngine;

public class ProductList extends AppCompatActivity {

    private AdapterProduct mAdapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        Button filter = findViewById(R.id.filter_button);
        Button sort = findViewById(R.id.sort_button);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductList.this,FilterMenu.class);
                startActivityForResult(intent,102);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductList.this,SortMenu.class);
                startActivityForResult(intent,103);
            }
        });
        
        Intent intent = getIntent();
        String argument = null;

        if (!(intent.getStringExtra("search") == null))
        argument = getIntent().getStringExtra("search");

        if (!(intent.getStringExtra("category") == null))
        argument = getIntent().getStringExtra("category");

        gridView = findViewById(R.id.product_list);

        BackgroundProductRetrieverEngine retriever = new BackgroundProductRetrieverEngine(this, mAdapter);
        retriever.execute(argument);
    }

    @Override
    protected void onResume() {
        mAdapter = new AdapterProduct(ProductList.this, MainActivity.products);
        gridView.setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FilterMenu.filterFlag = false;
        finish();
    }
}