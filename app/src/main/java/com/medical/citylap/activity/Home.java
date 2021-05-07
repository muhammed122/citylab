package com.medical.citylap.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.medical.citylap.R;
import com.medical.citylap.base.BaseFragment;
import com.medical.citylap.fragemnt.Fragment_map;
import com.medical.citylap.fragemnt.HomeFragment;
import com.medical.citylap.fragemnt.LoginFragment;
import com.medical.citylap.fragemnt.MapsFragment;
import com.medical.citylap.fragemnt.Profilefragment;

public class Home extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_location:

                    startActivity(new Intent(Home.this, Mooglmap.class));
                    //fragment=new Fragment_map();
                    // loadFragment(fragment);
                    return true;
                case R.id.navigation_chat:

                    ////start whats app

                    whatsapp();

                    return true;
                case R.id.navigation_profil:

                    if (checkuserlogin() == null) {
                        //open login screen
                        fragment = new LoginFragment();
                        loadFragment(fragment);
                    } else {
                        fragment = new Profilefragment();
                        loadFragment(fragment);

                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fragment fragment;
        fragment = new HomeFragment();
        loadFragment(fragment);
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack("tag")
                    .commit();
            return true;
        }
        return false;
    }

    public String checkuserlogin() {
        String id = null;
        SharedPreferences preferences = this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("phonenumberuser", null);//second parameter default value.
        return retrivedToken;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            System.exit(1);
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    public void whatsapp() {
        String contact = "+02 "+"01062436363"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + contact;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}


