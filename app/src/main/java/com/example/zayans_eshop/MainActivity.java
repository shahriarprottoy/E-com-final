package com.example.zayans_eshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.zayans_eshop.data.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // The universal Product List used for storing products as per categories
    public static ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new home__fragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment= null;
                    switch (menuItem.getItemId()){
                        case R.id.home:
                            selectedFragment=new home__fragment();
                            break;
                        case R.id.message:
                            selectedFragment=new messages__fragment();
                            break;
                        case R.id.cart:
                            selectedFragment=new cart__fragment();
                            break;
                        case R.id.account:
                            selectedFragment=new account__fragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return  true;
                }
            };
}
