package com.example.zayans_eshop.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;
import com.example.zayans_eshop.SettingsActivity;
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
        Button logOutButton = root.findViewById(R.id.log_out_btn);
        Button editButton = root.findViewById(R.id.edit_account_button);

        this.userAccount = MainActivity.userAccount;

        userName.setText(userAccount.getUserName());
        userPhone.setText(userAccount.getUserPhone());
        userEmail.setText(userAccount.getUserEmail());
        userLocation.setText(userAccount.getUserLocation());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to Log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences userAccountPrefs = getActivity().getSharedPreferences("userAccount", 0);
                        SharedPreferences.Editor editor = userAccountPrefs.edit();

                        editor.clear().apply();

                        MainActivity.userAccount.logOut();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new account__fragment()).commit();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Log out?");
                alertDialog.show();
            }
        });

        return root;
    }
}
