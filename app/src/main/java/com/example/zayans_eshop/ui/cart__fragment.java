package com.example.zayans_eshop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.RegisterActivity;
import com.example.zayans_eshop.Unsigned_screen;
import com.example.zayans_eshop.data.AdapterCartProduct;

public class cart__fragment extends Fragment {

    private AdapterCartProduct mAdapter;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.cart_fragment, container, false);

        ListView cartListView = root.findViewById(R.id.cart_product_list);
        mAdapter = new AdapterCartProduct(getActivity(), MainActivity.cartProducts);
        cartListView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (MainActivity.userAccount.getUserName() == null) {
            startActivity(new Intent(getActivity(), Unsigned_screen.class));
            getActivity().overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            Intent intent = new Intent(getActivity(), Unsigned_screen.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
