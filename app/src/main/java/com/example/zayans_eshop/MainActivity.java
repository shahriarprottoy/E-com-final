package com.example.zayans_eshop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.zayans_eshop.data.Product;
import com.example.zayans_eshop.data.UserAccount;
import com.example.zayans_eshop.ui.account__fragment;
import com.example.zayans_eshop.ui.cart__fragment;
import com.example.zayans_eshop.ui.home__fragment;
import com.example.zayans_eshop.ui.messages__fragment;
import com.example.zayans_eshop.ui.signed__in__account__fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // The universal ArrayList<Product> used for storing products as per categories
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> cartProducts = new ArrayList<>();
    public static ActionBar actionBar;

    public static BottomNavigationView bottomNavigationView;

    public static UserAccount userAccount;
    public static FragmentManager fragmentManager;
    public static boolean loginFlag;
    public static int totalAmount =0;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            selectedFragment = new home__fragment();
                          //  FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
//
  //                          ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) frameLayout.getLayoutParams();
    //                        params.setMargins(0, 40, 0, 0);
      //                      frameLayout.setLayoutParams(params);
                            break;
                        case R.id.message:
                            selectedFragment = new messages__fragment();
                            break;
                        case R.id.cart:
                            selectedFragment = new cart__fragment();
                            break;
                        case R.id.account:
                            if (userAccount.getUserName() == null) {
                                selectedFragment = new account__fragment();
                            } else {
                                loginFlag = true;

                                selectedFragment = new signed__in__account__fragment();
                            }
                            break;
                    }

                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
        loginFlag = false;

        fragmentManager = getSupportFragmentManager();

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setTitle(Html.fromHtml("<font color='#2399DD'>ayan's eShop</font>"));
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new home__fragment()).commit();

        userAccount = new UserAccount(null, null, null, null, null);

        SharedPreferences userAccountPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        userAccount.setUserName(userAccountPrefs.getString("userName", null));
        userAccount.setUserPhone(userAccountPrefs.getString("userPhone", null));
        userAccount.setUserEmail(userAccountPrefs.getString("userEmail", null));
        userAccount.setUserLocation(userAccountPrefs.getString("userLocation", null));
        userAccount.setUniqId(userAccountPrefs.getString("uniqId", null));

        if (loginFlag && userAccount.getUserName() != null) {
            bottomNavigationView.getMenu().getItem(3).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new signed__in__account__fragment()).commit();
        }
    }
}
