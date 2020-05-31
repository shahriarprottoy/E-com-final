package com.example.zayans_eshop.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zayans_eshop.R;
import com.example.zayans_eshop.data.Product;
import com.example.zayans_eshop.data.ProductAdapter;

import java.util.ArrayList;

public class cart__fragment extends Fragment {

    public static ArrayList<Product> cartProducts = new ArrayList<Product>();
    private ProductAdapter mAdapter;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.cart_fragment, container, false);
        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ListView cartListView = root.findViewById(R.id.cart_product_list);
        mAdapter = new ProductAdapter(getActivity(), cartProducts);
        cartListView.setAdapter(mAdapter);
    }
}
