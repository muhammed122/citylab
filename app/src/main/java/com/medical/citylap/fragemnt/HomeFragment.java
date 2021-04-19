package com.medical.citylap.fragemnt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.medical.citylap.R;
import com.medical.citylap.MyPreference;
import com.medical.citylap.activity.BookingScreen;
import com.medical.citylap.activity.ResultActivty;
import com.medical.citylap.activity.SplashScreen;
import com.medical.citylap.modles.Result;


public class HomeFragment extends Fragment {
Button result,booking,aboutus,seealloffer,suger,prusioer,perfectwight;
public static Fragment fragment=new Offerfragment();
    MyPreference session;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     View view= inflater.inflate(R.layout.fragment_home, container, false);
        intilazation( view);

        seealloffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      Toast.makeText(getContext(),
                   //     "User Login Status: " + MyPreference.getSharedString("token"),
                   //     Toast.LENGTH_LONG).show();

              final FragmentTransaction ft = getFragmentManager().beginTransaction();
              ft.replace(R.id.fragment_container, new Offerfragment(), "NewFragmentTag");
              ft.commit();
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                String retrivedToken  = preferences.getString("phonenumberuser",null);
                if(retrivedToken !=null)
                {
                    startActivity(new Intent( getActivity(), ResultActivty.class));

                }
                else
                    {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.custom_result);
                        dialog.show();
                }


            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(getActivity(), BookingScreen.class));
            }
        });
     return view;
    }
    public void intilazation(View view)
    {
        result=view.findViewById(R.id.resulthome_id);
        booking=view.findViewById(R.id.booking_test_id);
        aboutus=view.findViewById(R.id.aboutus_id);
        seealloffer=view.findViewById(R.id.see_all_offer);
        suger=view.findViewById(R.id.suger_id_home);
        prusioer=view.findViewById(R.id.presserblode_id_home);
        perfectwight=view.findViewById(R.id.perfect_wight_id_home);

    }

}