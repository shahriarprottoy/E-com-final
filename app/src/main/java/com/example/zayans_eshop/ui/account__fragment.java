package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zayans_eshop.LoginActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.RegisterActivity;

public class account__fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.account_fragment,container,false);

        TextView button = root.findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
              //  getActivity().overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                 startActivity(intent);
            }

        });
        Button button2 = root.findViewById(R.id.loginbutton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }

        });
        return root;
    }
}
