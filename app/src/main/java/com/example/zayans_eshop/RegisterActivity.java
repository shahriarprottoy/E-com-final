package com.example.zayans_eshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zayans_eshop.data.BackgroundRegistrationEngine;

public class RegisterActivity extends AppCompatActivity {

    private String userName;
    private String userPass;
    private String userPhone;
    private String userEmail;
    private String userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerscreen);

        final EditText name = findViewById(R.id.username);
        final EditText pass = findViewById(R.id.userpass);
        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        final EditText location = findViewById(R.id.location);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = name.getText().toString();
                userPass = pass.getText().toString();
                userPhone = phone.getText().toString();
                userEmail = email.getText().toString();
                userLocation = location.getText().toString();

                if (userName.length() < 4) {
                    if (userName.length() == 0) {
                        Toast toast = Toast.makeText(RegisterActivity.this, "name required" + userName,
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(RegisterActivity.this, "your name is too short",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    }
                } else if (userPass.length() < 6) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "password is too short",
                            Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(Color.RED);
                    toast.show();
                } else if (userPhone.length() < 10 || userPhone.length() > 11) {
                    if (userPhone.length() == 0) {
                        Toast toast = Toast.makeText(RegisterActivity.this, "mobile number requied",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(RegisterActivity.this, "mobile number invalid",
                                Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    }
                } else {
                    BackgroundRegistrationEngine regitrationEngine = new BackgroundRegistrationEngine(RegisterActivity.this);
                    regitrationEngine.execute(userName, userPass, userPhone, userEmail, userLocation);
                    // Update UI on account created from Registerer onPostExecute;
                    // For later,, will redirect to login activity once account created

                    // getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new home__fragment()).commit();
                }
            }
        });
    }
}