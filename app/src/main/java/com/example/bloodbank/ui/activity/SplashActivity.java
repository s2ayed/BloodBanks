package com.example.bloodbank.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMathod;
import com.example.bloodbank.ui.fragment.SlideFragment;
import com.example.bloodbank.ui.fragment.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // start Splash Fragment
        Fragment fragment = new SplashFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.splashActivityReplaceFragment, fragment).commit();

        try {
            // start Slider Fragment == 5000 mills
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    try {
                        Fragment fragment = new SlideFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.splashActivityReplaceFragment, fragment).commit();
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            }, 3000);
        } catch (Exception e) {
            e.getMessage();
        }


    }

}
