package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zayans_eshop.data.BackgroundRegistrationEngine;

public class RegisterActivity extends AppCompatActivity {

    private String userName;
    private String userPass;
    private String userPhone;
    private String userEmail;
    private String userLocation;
    private BackgroundRegistrationEngine regitrationEngine;
    private Button submit;
    private TextView tv;
    private Toast warningToast;
    private View toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        setContentView(R.layout.registerscreen);

        final EditText name = findViewById(R.id.username);
        final EditText pass = findViewById(R.id.userpass);
        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email);
        final EditText location = findViewById(R.id.location);

        submit = findViewById(R.id.submit);
        LayoutInflater inflater = getLayoutInflater();
        toast = inflater.inflate(R.layout.toast_warning, (ViewGroup) findViewById(R.id.toast));
        tv = toast.findViewById(R.id.toast_text);

        warningToast = new Toast(this);
        warningToast.setDuration(Toast.LENGTH_SHORT);
        warningToast.setView(toast);

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
                        tv.setText("Name required!");
                        warningToast.show();
                    } else {
                        tv.setText("Too short! At least 4 letters.");
                        warningToast.show();
                    }
                } else if (userPass.length() < 6) {
                    tv.setText("Too short! At least 6 characters.");
                    warningToast.show();
                } else if (userPhone.length() < 10 || userPhone.length() > 15) {
                    if (userPhone.length() == 0) {
                        tv.setText("Mobile number required!");
                        warningToast.show();
                    } else {
                        tv.setText("Mobile number invalid!");
                        warningToast.show();
                    }
                } else {
                    submit.setEnabled(false);
                    regitrationEngine = new BackgroundRegistrationEngine(RegisterActivity.this, submit);
                    regitrationEngine.execute(userName, userPass, userPhone, userEmail, userLocation);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (regitrationEngine != null)
            regitrationEngine.cancel(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}