package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public  class Unsigned_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unsigned_screen);
        Button button = findViewById(R.id.loginbutton);
        Button button2 = findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Unsigned_screen.this, LoginActivity.class);
                startActivity(intent);
            }

        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Unsigned_screen.this, RegisterActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
