package com.example.zayans_eshop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.SettingsActivity;
import com.example.zayans_eshop.data.UserAccount;

import java.util.Objects;

public class signed__in__account__fragment extends Fragment {

    private TextView userName;
    private TextView userPhone;
    private TextView userEmail;
    private TextView userLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signed_in_account_fragment, container, false);

       MainActivity.actionBar.setTitle(Html.fromHtml("<FONT color='#999999'>My Account</FONT>"));

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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onStop() {
        super.onStop();
       MainActivity.actionBar.setIcon(R.mipmap.ic_launcher);
       MainActivity.actionBar.setTitle(Html.fromHtml("<font color='#2399DD'>ayan's eShop</font>"));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MainActivity.loginFlag) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new account__fragment()).commit();
        } else {
            UserAccount userAccount = MainActivity.userAccount;

            userName.setText(userAccount.getUserName());
            userPhone.setText(userAccount.getUserPhone());
            userEmail.setText(userAccount.getUserEmail());
            userLocation.setText(userAccount.getUserLocation());
        }
        MainActivity.actionBar.setTitle(Html.fromHtml("<FONT color='#999999'>My Account</FONT>"));
        MainActivity.actionBar.setIcon(null);
    }
}