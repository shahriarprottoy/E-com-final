package com.example.zayans_eshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zayans_eshop.R;

public  class Unsigned_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unsigned_screen);
        Button button=(Button) findViewById(R.id.loginbutton);
        Button button2=(Button) findViewById(R.id.register);

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
}
