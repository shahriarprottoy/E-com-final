package com.example.zayans_eshop.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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


        androidx.appcompat.widget.Toolbar  mToolbar;

        mToolbar =(androidx.appcompat.widget.Toolbar)  root.findViewById(R.id.toolbar);
        mToolbar.setTitle("My Account");
        mToolbar.setTitleTextColor(Color.GRAY);
        mToolbar.inflateMenu(R.menu.settings_menu);
        mToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String title = (String) item.getTitle() ;

                switch (item.getItemId()) {

                    case R.id.settings:
                       Intent intent=new Intent(getActivity(),SettingsActivity.class);
                        startActivity(intent);
                }
                return true;
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



        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MainActivity.loginFlag) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new account__fragment()).commit();
        } else {
            UserAccount userAccount = MainActivity.userAccount;

            userName.setText(userAccount.getUserName());
            userPhone.setText(userAccount.getUserPhone());
            userEmail.setText(userAccount.getUserEmail());
            userLocation.setText(userAccount.getUserLocation());
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
}
