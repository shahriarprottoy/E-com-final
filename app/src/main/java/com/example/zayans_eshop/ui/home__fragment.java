package com.example.zayans_eshop.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zayans_eshop.ProductList;
import com.example.zayans_eshop.R;

public class home__fragment extends Fragment {

    @SuppressLint("StaticFieldLeak")


    // Note:  Use the function dataRetriever() to retrieve data from db
    // It automatically stores the data inside MainActivity.productList.products[]
    // pass the category as argument inside dataRetriever()
    // Later you can access the retrieved data from MainActivity.productList

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button button = root.findViewById(R.id.electronics);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Electric and Electronics");
                startActivity(intent);
            }

        });

        Button button2 = root.findViewById(R.id.foods);
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Grocery and Food");
                startActivity(intent);
            }

        });

        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // Retrieves data from db and stores inside the Product
    // objects of MainActivity.productList
    // See the definition of ProductList.java class for insight
}
