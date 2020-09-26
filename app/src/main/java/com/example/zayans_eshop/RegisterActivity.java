package com.example.zayans_eshop;

import android.animation.Animator;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.data.BackgroundRegistrationEngine;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private View background;
    private String userName;
    private String userPass;
    private String retypeuserPass;
    private String userPhone;
    private String userEmail;
    private String userLocation;
    private BackgroundRegistrationEngine regitrationEngine;
    private Button submit;
    private TextView tv;
    private Toast warningToast;
    private View toast;
    private boolean cartFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.registerscreen);

        background = findViewById(R.id.background);
        if (savedInstanceState == null) {
            background.setVisibility(View.VISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }
        final TextInputEditText name = findViewById(R.id.username);
        final TextInputEditText pass = findViewById(R.id.userpass);
        final TextInputEditText retypepass = findViewById(R.id.retypeuserpass);
        final TextInputEditText phone = findViewById(R.id.phone);
        final TextInputEditText email = findViewById(R.id.email);
        final TextInputEditText location = findViewById(R.id.location);

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
                retypeuserPass= retypepass.getText().toString();
                if (userName.length() < 4) {
                    if (userName.length() == 0) {
                        tv.setText("Name required!");
                        warningToast.show();
                    } else {
                        tv.setText("Too short name! At least 4 letters.");
                        warningToast.show();
                    }
                } else if (userPass.length() < 6 ) {
                    tv.setText("Too short password! At least 6 characters.");
                    warningToast.show();
                } else if(!userPass.equals(retypeuserPass)){
                    tv.setText("Password should be same");
                    warningToast.show();
                }  else if (userLocation.length() == 0 || userLocation.length() < 4) {
                    tv.setText("Invalid location! Please give correct location.");
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
                    submit.setAlpha(.5f);
                    regitrationEngine = new BackgroundRegistrationEngine(RegisterActivity.this, submit);
                    regitrationEngine.execute(userName, userPass, userPhone, userEmail, userLocation);
                }
            }
        });

        cartFlag = getIntent().getBooleanExtra("cartFlag", false);


    }

    @Override
    public void onBackPressed() {
        if (regitrationEngine != null)
            regitrationEngine.cancel(true);
        super.onBackPressed();
        finish();
    }
    private void circularRevealActivity() {
        int cx = background.getRight() - getDips(44);
        int cy = background.getBottom() - getDips(44);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(500);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }
}