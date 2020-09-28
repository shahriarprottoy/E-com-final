package com.example.zayans_eshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 200;
    private Boolean firstTimeFlag;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);

        SharedPreferences firstTimePrefs = getSharedPreferences("Init",MODE_PRIVATE);
        firstTimeFlag = firstTimePrefs.getBoolean("FirstTime",true);
        final SharedPreferences.Editor editor = firstTimePrefs.edit();

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(firstTimeFlag){
                    Intent mainIntent = new Intent(Splash.this,IntroActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                    editor.putBoolean("FirstTime",false);
                    editor.apply();
                } else {
                    Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}