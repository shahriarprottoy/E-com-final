package com.example.zayans_eshop;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.zayans_eshop.data.UserAccountUpdaterEngine;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(Html.fromHtml("<font color='#2399DD'>Settings</font>"));
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                    builder.setMessage("Do you want to Log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences userAccountPrefs = getActivity().getSharedPreferences("userAccount", 0);
                            SharedPreferences.Editor editor = userAccountPrefs.edit();

                            editor.clear().apply();

                            MainActivity.userAccount.logOut();
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
                    MainActivity.loginFlag = false;
                    return false;
                }
            });

            Preference save = findPreference("saveSettings");
            assert save != null;
            save.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
                    UserAccountUpdaterEngine userAccountUpdaterEngine = new UserAccountUpdaterEngine(getActivity());
                    userAccountUpdaterEngine.execute(
                            sp.getString("userName", ""),
                            sp.getString("uniqId", ""),
                            sp.getString("userPhone", ""),
                            sp.getString("userEmail", ""),
                            sp.getString("userLocation", "")
                    );
                    return false;
                }
            });

            Preference updatePass = findPreference("changePassword");
            assert updatePass != null;
            updatePass.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    // TODO: go to password changer activity;
                    return false;
                }
            });
        }
    }
}