package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
                intent.putExtra("cartFlag", true);
                startActivity(intent);
                finish();
            }

        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(Unsigned_screen.this, RegisterActivity.class);
                intent.putExtra("cartFlag", true);
                startActivity(intent);
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
