package com.example.zayans_eshop.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.LoginActivity;
import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.R;

import java.util.function.ToDoubleBiFunction;


public class ChangePassword extends AppCompatActivity {

      private String oldPass;
      private String newPass;
        private TextView tv;
        private Toast warningToast;
        private View toast;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.password_change);

             final EditText previouspass = findViewById(R.id.previous);
             final EditText newpass = findViewById(R.id.newpass);
             final Button change = findViewById(R.id.changepassbutton);

            LayoutInflater inflater = getLayoutInflater();
            toast = inflater.inflate(R.layout.toast_warning, (ViewGroup) findViewById(R.id.toast));
            tv = toast.findViewById(R.id.toast_text);

            warningToast = new Toast(this);
            warningToast.setDuration(Toast.LENGTH_SHORT);
            warningToast.setView(toast);

            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oldPass = previouspass.getText().toString();
                    newPass = newpass.getText().toString();


                      if (newpass.length() < 6) {
                        if (newpass.length() == 0) {
                            tv.setText("Enter new password");
                            warningToast.show();
                        } else {
                            tv.setText("Invalid password");
                            warningToast.show();
                        }
                    } else {
                          //TODO save the new pass

                    }
                }
            });

        }

        }

