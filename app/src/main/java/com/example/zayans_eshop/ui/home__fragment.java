package com.example.zayans_eshop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.ProductList;
import com.example.zayans_eshop.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayout.LayoutParams;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class home__fragment extends Fragment {

    private SearchView searchView;
    public static Boolean oneFlag = true;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout.LayoutParams params,params2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CollapsingToolbarLayout collapsingToolbarLayout = root.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Zayan's eShop");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorPrimaryDark));

        appBarLayout = root.findViewById(R.id.app_bar_layout);
        params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (340*getContext().getResources().getDisplayMetrics().density));
        params.setMargins(0, (int) (-58 * getContext().getResources().getDisplayMetrics().density),0,0);
        appBarLayout.setLayoutParams(params);

        if(!oneFlag){
            Log.i("TEST","here");
            params2 = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (340*getContext().getResources().getDisplayMetrics().density));
            params2.setMargins(0,(int) (29 * getContext().getResources().getDisplayMetrics().density),0,0);
            appBarLayout.setLayoutParams(params2);
        } else{
            oneFlag = false;
        }

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

        searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), ProductList.class);
                intent.putExtra("search", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        super.onAttach(context);
    }
}
