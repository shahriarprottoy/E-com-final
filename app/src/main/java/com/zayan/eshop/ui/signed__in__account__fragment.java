package com.zayan.eshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zayan.eshop.MainActivity;
import com.zayan.eshop.R;
import com.zayan.eshop.SettingsActivity;
import com.zayan.eshop.data.UserAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class signed__in__account__fragment extends Fragment {

    private TextView userName;
    private TextView userPhone;
    private TextView userEmail;
    private TextView userLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signed_in_account_fragment, container, false);

        if (MainActivity.userAccount.getUserName() == null) {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MainActivity.account__fragment).commit();
        }

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        userName = root.findViewById(R.id.un);
        userPhone = root.findViewById(R.id.phoneNumber);
        userEmail = root.findViewById(R.id.emailAddress);
        userLocation = root.findViewById(R.id.locationText);

        UserAccount userAccount = MainActivity.userAccount;

        userName.setText(userAccount.getUserName());
        userPhone.setText(userAccount.getUserPhone());
        userEmail.setText(userAccount.getUserEmail());
        userLocation.setText(userAccount.getUserLocation());

        setHasOptionsMenu(true);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.userAccount.getUserName() == null) {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MainActivity.account__fragment).commit();
        } else {
            UserAccount userAccount = MainActivity.userAccount;

            userName.setText(userAccount.getUserName());
            userPhone.setText(userAccount.getUserPhone());
            userEmail.setText(userAccount.getUserEmail());
            userLocation.setText(userAccount.getUserLocation());
        }
    }
}