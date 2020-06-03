package com.example.zayans_eshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zayans_eshop.data.BackgroundLoginEngine;

public class LoginActivity extends AppCompatActivity {

    private String userName;
    private String userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        final EditText name = findViewById(R.id.username);
        final EditText pass = findViewById(R.id.userpass);
        final Button submit = findViewById(R.id.login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = name.getText().toString();
                userPass = pass.getText().toString();

                // TODO: Impose more possible conditions to ensure prevention of invalid input
                if (userName.length() < 4) {
                    if (userName.length() == 0) {
                        // TODO: show Invalid
                    } else {
                        // TODO: show Invalid
                    }
                } else if (userPass.length() < 6) {
                    if (userPass.length() == 0) {
                        // TODO: show Invalid
                    } else {
                        // TODO: show Invalid
                    }
                } else {
                    BackgroundLoginEngine loginEngine = new BackgroundLoginEngine();
                    loginEngine.execute(userName, userPass);
                }
            }
        });
    }
    }
