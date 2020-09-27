package com.example.zayans_eshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.zayans_eshop.data.Product;

import java.util.Collections;

public class SortMenu extends AppCompatActivity {

    private AppCompatCheckBox nameAscending;
    private AppCompatCheckBox nameDescending;
    private AppCompatCheckBox ascending;
    private AppCompatCheckBox descending;

    public static Boolean stringFlag;
    public static Boolean As;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_menu);

        stringFlag = null;

        /*Button back = findViewById(R.id.header_back_button);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        Button sortDone = findViewById(R.id.sort_done);

        sortDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nameAscending = findViewById(R.id.name_as_check);
        nameDescending = findViewById(R.id.name_des_check);
        ascending = findViewById(R.id.as_check);
        descending = findViewById(R.id.des_check);


        setCheckListener(nameAscending);
        setCheckListener(nameDescending);
        setCheckListener(ascending);
        setCheckListener(descending);
    }

    private void uncheckAll(){
        nameAscending.setChecked(false);
        nameDescending.setChecked(false);
        ascending.setChecked(false);
        descending.setChecked(false);
    }

    private void setCheckListener(AppCompatCheckBox checkbox){
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    uncheckAll();
                    buttonView.setChecked(true);
                    stringFlag = buttonView.getId() != R.id.as_check && buttonView.getId() != R.id.des_check;
                    As = buttonView.getId() == R.id.name_as_check || buttonView.getId() == R.id.as_check;
                }
            }
        });
    }


    /** Implement Sorting on MainActivity.products in onBackPressed()
     * This method will be called on sortDone button onClick
     * to shorten things */
    @Override
    public void onBackPressed() {
        if(stringFlag != null){
            if(As){
                Collections.sort(MainActivity.products);
            } else {
                Collections.sort(MainActivity.products,Collections.<Product>reverseOrder());
            }
        }
        finish();
        super.onBackPressed();
    }
}