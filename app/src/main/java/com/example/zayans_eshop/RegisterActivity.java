package com.example.zayans_eshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zayans_eshop.data.Registerer;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerscreen);

        final EditText name = findViewById(R.id.username);
        final EditText pass = findViewById(R.id.userpass);
        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        final EditText location = findViewById(R.id.location);

        final String userName = name.getText().toString();
        final String userPass = pass.getText().toString();
        final String userPhone = phone.getText().toString();
        final String userEmail = email.getText().toString();
        final String userLocation = location.getText().toString();

        Button submit = findViewById(R.id.submit);

        // TODO Impose some more conditions if you feel like
        // TODO Needs to show "+880" before number by default
        // TODO Improve Register activity design
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.length() < 5) {
                    if (userName.length() == 0) {
                        // TODO Update UI for no input
                    } else {
                        // TODO Update UI
                    }
                } else if (userPass.length() < 6) {
                    // TODO Update UI
                } else if (userPhone.length() < 10 || userPhone.length() > 11) {
                    if (userPhone.length() == 0) {
                        // TODO Update UI
                    }
                } else {
                    // TODO Hide/disable submit button
                    Registerer registerer = new Registerer();
                    registerer.execute(userName, userPass, userPhone, userEmail, userLocation);
                    // Update UI on account created from Registerer onPostExecute;
                    // For later,, will redirect to login activity once account created
                }
            }
        });
    }
}