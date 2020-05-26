package com.example.e_comfinal;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new home__fragment());

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
