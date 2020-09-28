package com.example.zayans_eshop;

import android.os.Bundle;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.github.paolorotolo.appintro.model.SliderPagerBuilder;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showIntroSlides();
    }

    private void showIntroSlides(){
        SliderPage pageOne = new SliderPagerBuilder()
                .title(getString(R.string.p1title))
                .description(getString(R.string.p1description))
                .imageDrawable(R.drawable.ic_eshop)
                .bgColor(getResources().getColor(R.color.p1))
                .build();
        SliderPage pageTwo = new SliderPagerBuilder()
                .title(getString(R.string.p2title))
                .description(getString(R.string.p2description))
                .imageDrawable(R.drawable.ic_location)
                .bgColor(getResources().getColor(R.color.p2))
                .build();
        SliderPage pageThree = new SliderPagerBuilder()
                .title(getString(R.string.p3title))
                .description(getString(R.string.p3description))
                .imageDrawable(R.drawable.ic_wrench)
                .bgColor(getResources().getColor(R.color.p3))
                .build();

        addSlide(AppIntroFragment.newInstance(pageOne));
        addSlide(AppIntroFragment.newInstance(pageTwo));
        addSlide(AppIntroFragment.newInstance(pageThree));
    }

    private void gotoMain(){
        startActivity(new Intent(IntroActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        gotoMain();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        gotoMain();
    }
}