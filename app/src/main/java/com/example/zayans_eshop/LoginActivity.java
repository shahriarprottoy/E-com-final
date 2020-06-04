package com.example.zayans_eshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


                if (userName.length() < 4) {
                    if (userName.length() == 0) {
                        Toast.makeText(LoginActivity.this, "enter name",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast toast = Toast.makeText(LoginActivity.this, "invalid name",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    }
                } else if (userPass.length() < 6) {
                    if (userPass.length() == 0) {
                        Toast toast = Toast.makeText(LoginActivity.this, "enter password",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.YELLOW);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(LoginActivity.this, "invalid password",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    }
                } else {
                    BackgroundLoginEngine loginEngine = new BackgroundLoginEngine(LoginActivity.this);
                    loginEngine.execute(userName, userPass);
                }
            }
        });
    }
}
