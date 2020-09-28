package com.example.zayans_eshop.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.Checkout;
import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.Unsigned_screen;
import com.example.zayans_eshop.data.AdapterCartProduct;
import com.example.zayans_eshop.data.Product;

import java.util.Objects;

public class cart__fragment extends Fragment {

    public static AppCompatImageView empty;
    @SuppressLint("StaticFieldLeak")
    public static TextView total_text_view;
    public static int total_amount;
    @SuppressLint("StaticFieldLeak")
    public static AdapterCartProduct mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.cart_fragment, container, false);
        total_text_view = root.findViewById(R.id.total);
        if (MainActivity.userAccount.getUserName() != null) {
            Button btn = root.findViewById(R.id.orderbutton);
            btn.setVisibility(View.INVISIBLE);

            empty = root.findViewById(R.id.empty_cart);
            if (MainActivity.cartProducts.size() > 0) {
                btn.setVisibility(View.VISIBLE);
                empty.setVisibility(View.INVISIBLE);
                ListView cartListView = root.findViewById(R.id.cart_product_list);
                mAdapter = new AdapterCartProduct(getActivity(), MainActivity.cartProducts);
                cartListView.setAdapter(mAdapter);
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Checkout.class);
                        startActivity(intent);
                    }

                });
            }
            return root;
        }
        return root;
    }


    @Override
    public void onResume() {

        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();

        if (MainActivity.userAccount.getUserName() == null) {
            Intent intent = new Intent(getActivity(), Unsigned_screen.class);
            startActivity(intent);
            MainActivity.bottomNavigationView.getMenu().getItem(0).setChecked(true);
            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, MainActivity.home__fragment).commit();
        } else {
            RefreshTotal();
        }
        super.onResume();
    }

    // Easily refresh the Total textView after changes in the cartProduct objects
    //
    // Use: Make necessary changes to the required Product object in carProducts
    //      and simply call this function to auto update the Total textView
    @SuppressLint("SetTextI18n")
    public static void RefreshTotal(){
        total_amount = 0;
        for (Product product: MainActivity.cartProducts) {
            total_amount += product.getTotalCost();
        }
        total_text_view.setText("TK. " + total_amount);
    }
}
