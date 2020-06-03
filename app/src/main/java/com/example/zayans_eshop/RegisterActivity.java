package com.example.zayans_eshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if (userName.length() < 4) {
                    if (userName.length() == 0) {
                        // TODO Update UI for no input
                      Toast toast= Toast.makeText(RegisterActivity.this, "name required",
                                Toast.LENGTH_LONG);
                                toast.getView().setBackgroundColor(Color.RED);
                                toast.show();
                    }
                    else { Toast toast= Toast.makeText(RegisterActivity.this, "your name is too short",
                            Toast.LENGTH_LONG);
                            toast.getView().setBackgroundColor(Color.RED);
                             toast.show();}
                }
               else if (userPass.length() < 6) {
                    // TODO Update UI
                   Toast toast= Toast.makeText(RegisterActivity.this, "password is too short",
                            Toast.LENGTH_LONG);
                            toast.getView().setBackgroundColor(Color.RED);
                            toast.show();
                }
              else  if (userPhone.length() < 10 || userPhone.length() > 11) {
                      if (userPhone.length() == 0) {
                        // TODO Update UI
                        Toast toast =  Toast.makeText(RegisterActivity.this, "mobile number requied",
                                Toast.LENGTH_LONG);
                                toast.getView().setBackgroundColor(Color.RED);
                                toast.show();}

                      else {   Toast toast = Toast.makeText(RegisterActivity.this, "mobile number invalid",
                             Toast.LENGTH_LONG);
                             toast.getView().setBackgroundColor(Color.RED);
                             toast.show();}
                }
                else {
                    // TODO Hide/disable submit button
                    Registerer registerer = new Registerer();
                    registerer.execute(userName, userPass, userPhone, userEmail, userLocation);
                    // Update UI on account created from Registerer onPostExecute;
                    // For later,, will redirect to login activity once account created
                    Toast.makeText(RegisterActivity.this, "registration successful",
                            Toast.LENGTH_LONG).show();
                   // getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new home__fragment()).commit();
                }
            }
        });
    }
}