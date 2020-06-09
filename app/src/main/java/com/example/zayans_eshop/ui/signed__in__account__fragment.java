package com.example.zayans_eshop.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.data.UserAccount;

public class signed__in__account__fragment extends Fragment {

    private UserAccount userAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.signed_in_account_fragment, container, false);

        TextView userName = root.findViewById(R.id.un);
        TextView userPhone = root.findViewById(R.id.phoneNumber);
        TextView userEmail = root.findViewById(R.id.emailAddress);
        TextView userLocation = root.findViewById(R.id.locationText);

        this.userAccount = MainActivity.userAccount;

        userName.setText(userAccount.getUserName());
        userPhone.setText(userAccount.getUserPhone());
        userEmail.setText(userAccount.getUserEmail());
        userLocation.setText(userAccount.getUserLocation());

        return root;
    }
}
