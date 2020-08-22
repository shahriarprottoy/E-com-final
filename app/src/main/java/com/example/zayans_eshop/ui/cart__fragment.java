package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.Unsigned_screen;
import com.example.zayans_eshop.data.AdapterCartProduct;
import com.example.zayans_eshop.data.BackgroundOrderEngine;
import com.example.zayans_eshop.data.Checkout;
import com.example.zayans_eshop.data.Order;

public class cart__fragment extends Fragment {

    public static TextView empty;
    public Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (MainActivity.userAccount.getUserName() == null) {
            Intent intent = new Intent(getActivity(), Unsigned_screen.class);
            startActivity(intent);
            return null;
        } else {
            View root = inflater.inflate(R.layout.cart_fragment, container, false);

            Button btn = root.findViewById(R.id.orderbutton);
            btn.setVisibility(View.INVISIBLE);

            empty = root.findViewById(R.id.empty_cart);
            if (MainActivity.cartProducts.size() > 0) {
                btn.setVisibility(View.VISIBLE);
                empty.setVisibility(View.INVISIBLE);
            }
            ListView cartListView = root.findViewById(R.id.cart_product_list);
            AdapterCartProduct mAdapter = new AdapterCartProduct(getActivity(), MainActivity.cartProducts);
            cartListView.setAdapter(mAdapter);

            button = root.findViewById(R.id.orderbutton);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Order code starts here
                    String tempName;
                    Order order = new Order(MainActivity.cartProducts.size());
                    for (int i = 0; i < MainActivity.cartProducts.size(); i++) {
                        tempName = MainActivity.cartProducts.get(i).getName();
                        tempName = tempName.replace("\"", "&:1:&");
                        tempName = tempName.replace("[", "&:2:&");
                        tempName = tempName.replace("]", "&:3:&");
                        order.addProduct(tempName,
                                MainActivity.cartProducts.get(i).getDiscountedPrice(),
                                MainActivity.cartProducts.get(i).getStock(),
                                MainActivity.cartProducts.get(i).getQuantity());
                    }

                    BackgroundOrderEngine backgroundOrderEngine = new BackgroundOrderEngine(getActivity());
                    backgroundOrderEngine.execute(order.getOrder(), MainActivity.userAccount.getUniqId());
                    // Order code finishes here

                    Intent intent = new Intent(getActivity(), Checkout.class);
                    startActivity(intent);
                }

            });
            return root;
        }
    }
    
    @Override
    public void onAttach(){
      if (MainActivity.userAccount.getUserName() == null) {
                  Intent intent = new Intent(getActivity(), Unsigned_screen.class);
                  startActivity(intent);
              }
    }
}
