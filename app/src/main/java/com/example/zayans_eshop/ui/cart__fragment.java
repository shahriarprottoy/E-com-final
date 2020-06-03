package com.example.zayans_eshop.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zayans_eshop.R;
import com.example.zayans_eshop.data.AdapterCartProduct;
import com.example.zayans_eshop.data.Product;

import java.util.ArrayList;

public class cart__fragment extends Fragment {

    public static ArrayList<Product> cartProducts = new ArrayList<Product>();
    private AdapterCartProduct mAdapter;
    private View root;
    private ListView cartListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.cart_fragment, container, false);

        ListView cartListView = root.findViewById(R.id.cart_product_list);
        mAdapter = new AdapterCartProduct(getActivity(), cartProducts);
        cartListView.setAdapter(mAdapter);
        return root;
    }

}
