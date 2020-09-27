package com.example.zayans_eshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class FilterMenu extends AppCompatActivity {

    private TextInputEditText maximumField;
    private TextInputEditText minimumField;

    public static int minimum;
    public static int maximum;

    public static Boolean filterFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        Button filterDone = findViewById(R.id.filter_done);

        filterDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        maximumField= findViewById(R.id.maximum_filter_field);
        minimumField= findViewById(R.id.minimum_filter_field);

    }

    /** Implement filter on MainActivity.products in onBackpressed
     * Because it will be called in Done button onClick*/
    @Override
    public void onBackPressed() {
        if(!minimumField.getText().toString().equals("") && !maximumField.getText().toString().equals("")){
            filterFlag = true;
            minimum = Integer.parseInt(minimumField.getText().toString());
            maximum = Integer.parseInt(maximumField.getText().toString());
        } else
            filterFlag = false;
        finish();
        super.onBackPressed();
    }
}