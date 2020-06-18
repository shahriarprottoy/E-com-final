package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.ProductList;
import com.example.zayans_eshop.R;

public class home__fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button button = root.findViewById(R.id.electronics);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Electric and Electronics");
                startActivity(intent);
            }

        });

        Button button2 = root.findViewById(R.id.foods);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Grocery and Food");
                startActivity(intent);
            }

        });
        Button button3 = root.findViewById(R.id.crockeries);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Home Appliances and Crockeries");
                startActivity(intent);
            }

        });
        Button button4 = root.findViewById(R.id.bicycle);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Bicycle and Tricycle");
                startActivity(intent);
            }

        });
        Button button5 = root.findViewById(R.id.furniture);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Furniture and Home Decor");
                startActivity(intent);
            }

        });
        Button button6 = root.findViewById(R.id.toolsandaccessories);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Tools and Accessories");
                startActivity(intent);
            }

        });
        Button button7 = root.findViewById(R.id.pump);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Pumps and Machineries");
                startActivity(intent);
            }

        });
        Button button8 = root.findViewById(R.id.fashion);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Fashion");
                startActivity(intent);
            }

        });
        Button button9 = root.findViewById(R.id.gifts);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Gifts and Toys");
                startActivity(intent);
            }

        });
        Button button10 = root.findViewById(R.id.others);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("category", "Others");
                startActivity(intent);
            }

        });

        //calling the search bar
        setHasOptionsMenu(true);
        return root;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setQueryHint(Html.fromHtml("<font color =#DDDDDD>Search Product</font>"));

        searchView.setBackgroundColor(Color.rgb(225, 225, 225));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("search", query);
                getActivity().startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
}
