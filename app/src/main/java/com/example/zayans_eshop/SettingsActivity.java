package com.example.zayans_eshop;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.zayans_eshop.data.UserAccountUpdaterEngine;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            Preference button = findPreference("logout");
            assert button != null;
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setMessage("Do you want to Log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences userAccountPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity());
                            SharedPreferences.Editor editor = userAccountPrefs.edit();

                            editor.clear().apply();
                            MainActivity.userAccount.logOut();
                            MainActivity.loginFlag = false;
                            requireActivity().onBackPressed();
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
                    return false;
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        UserAccountUpdaterEngine userAccountUpdaterEngine = new UserAccountUpdaterEngine(SettingsActivity.this);
        userAccountUpdaterEngine.execute(
                sp.getString("userName", ""),
                sp.getString("uniqId", ""),
                sp.getString("userPhone", ""),
                sp.getString("userEmail", ""),
                sp.getString("userLocation", "")
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        UserAccountUpdaterEngine userAccountUpdaterEngine = new UserAccountUpdaterEngine(SettingsActivity.this);
        userAccountUpdaterEngine.execute(
                sp.getString("userName", ""),
                sp.getString("uniqId", ""),
                sp.getString("userPhone", ""),
                sp.getString("userEmail", ""),
                sp.getString("userLocation", "")
        );
    }
}