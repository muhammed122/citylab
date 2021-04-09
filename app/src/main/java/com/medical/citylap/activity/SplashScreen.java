package com.medical.citylap.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.medical.citylap.R;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    private ProgressBar progressBar;
    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    Animation anim;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.side_slide);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(2000);
        final ImageView splash = findViewById(R.id.imagelogogsplash);
        splash.startAnimation(animation);
        progressBar = findViewById(R.id.prograsssplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Home.class));
                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);




    }
}