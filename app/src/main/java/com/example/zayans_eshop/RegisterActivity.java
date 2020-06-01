package com.example.zayans_eshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

 public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.registerscreen);

       EditText name = (EditText) findViewById(R.id.edittext1);
       EditText pass = (EditText) findViewById(R.id.edittext2);
       EditText phone = (EditText) findViewById(R.id.edittext3);
       EditText email = (EditText) findViewById(R.id.edittext4);
       EditText location = (EditText) findViewById(R.id.edittext5);
    }
 }