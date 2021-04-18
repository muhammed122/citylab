package com.medical.citylap.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.medical.citylap.R;
import com.medical.citylap.RetrofitClint;
import com.medical.citylap.modles.Loginmodle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    private ProgressBar progressBar;
    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    Animation anim;
    ImageView imageView;

    public static String token_user;
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
                SharedPreferences preferences = SplashScreen.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                String retrivedToken  = preferences.getString("phonenumberuser",null);
                //second parameter default value.
                if(retrivedToken !=null) {
                    RetrofitClint.getInstance().userlogin(retrivedToken).enqueue(new Callback<Loginmodle>() {
                        @Override
                        public void onResponse(Call<Loginmodle> call, Response<Loginmodle> response) {
                            if (response.isSuccessful())
                            //save token in application
                            {
                                Log.d("TAG", "onResponse: " + response.body().getMessage() + retrivedToken);
                                token_user = response.body().getData().getToken();
                            }

                        }

                        @Override
                        public void onFailure(Call<Loginmodle> call, Throwable t) {

                        }
                    });
                }

                startActivity(new Intent(SplashScreen.this, Home.class));
                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);




    }
}