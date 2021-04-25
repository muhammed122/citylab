package com.medical.citylap.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.medical.citylap.R;
import com.medical.citylap.fragemnt.Fragment_map;
import com.medical.citylap.fragemnt.HomeFragment;
import com.medical.citylap.fragemnt.LoginFragment;
import com.medical.citylap.fragemnt.MapsFragment;
import com.medical.citylap.fragemnt.Profilefragment;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    MeowBottomNavigation bottomNavigation;
    public static boolean hom=true;
    Context context;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_location:

                    startActivity(new Intent(Home.this,Mooglmap.class));
                    //fragment=new Fragment_map();
                   // loadFragment(fragment);
                    return true;
                case R.id.navigation_chat:
                    hom=false;
                    ////start whats app

                    String contact = "+02 01121308294"; // use country code with your phone number
                    String url = "https://api.whatsapp.com/send?phone="+contact;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    return true;
                case  R.id.navigation_profil:
                    hom=false;
                    if( checkuserlogin()==null)
                    {
                        //open login screen
                        fragment=new LoginFragment();
                        loadFragment(fragment);
                    }

                    else
                    {
                        fragment=new Profilefragment();
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
            fragment=new HomeFragment();
            loadFragment(fragment);

        BottomNavigationView navigation =  findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        bottomNavigation=findViewById(R.id.bottomNavigationView);
//        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.homeblue));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_location_on_24));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_chat_24));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_person_24));
//        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
//            @Override
//            public void onClickItem(MeowBottomNavigation.Model item) {
//                // your codes
//
//                Fragment fragment;
//                switch (item.getId()) {
//                    case 1:
//                        fragment=new HomeFragment();
//                        loadFragment(fragment);
//
//                    case 2:
//
//                        startActivity(new Intent(Home.this,Mooglmap.class));
//                        //fragment=new Fragment_map();
//                        // loadFragment(fragment);
//
//                    case 3:
//                        hom=false;
//                        ////start whats app
//
//                        String contact = "+02 01121308294"; // use country code with your phone number
//                        String url = "https://api.whatsapp.com/send?phone="+contact;
//                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        i.setData(Uri.parse(url));
//                        startActivity(i);
//
//                    case  4:
//
//                        if( checkuserlogin()==null)
//                        {
//                            //open login screen
//                            fragment=new LoginFragment();
//                            loadFragment(fragment);
//                        }
//
//                        else
//                        {
//                            fragment=new Profilefragment();
//                            loadFragment(fragment);
//
//                        }
//
//                    default:
//
//
//
//                }
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
//            @Override
//            public void onShowItem(MeowBottomNavigation.Model item) {
//                // your codes
//            }
//        });
//
//        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
//            @Override
//            public void onReselectItem(MeowBottomNavigation.Model item) {
//                // your codes
//            }
//        });
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack( "tag" )
                    .commit();
            return true;
        }
        return false;
    }
    public String checkuserlogin()
    {
        String id=null;
        SharedPreferences preferences = this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("phonenumberuser",null);//second parameter default value.
        return retrivedToken;
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}

