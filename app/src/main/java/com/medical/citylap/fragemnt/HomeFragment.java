package com.medical.citylap.fragemnt;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.medical.citylap.Adapter.SliderAdapterExample;
import com.medical.citylap.R;
import com.medical.citylap.MyPreference;
import com.medical.citylap.RetrofitClint;
import com.medical.citylap.activity.BookingScreen;
import com.medical.citylap.activity.ResultActivty;
import com.medical.citylap.activity.SplashScreen;
import com.medical.citylap.modles.AllOffer;
import com.medical.citylap.modles.Datum;
import com.medical.citylap.modles.Result;
import com.medical.citylap.modles.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
Button result,booking,aboutus,seealloffer,suger,prusioer,perfectwight;
ImageView offer_one,offer_two;
TextView title_one,tile_two,start_one,start_two,end_one,end_two,pric_old_one,price_old_two,price_new_one,price_new_two,no_offer_one,no_ffer_two;
    Datum datum1;
    Datum datum2;
    public  int numbercheck;
    int number;
    SliderView sliderView;
    private SliderAdapterExample adapter;
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

        adapter = new SliderAdapterExample(getActivity());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Toast.makeText(getContext(), ""+sliderView.getCurrentPagePosition(), Toast.LENGTH_SHORT).show();
            }
        });
        addNewItem( view);
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
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                String retrivedToken  = preferences.getString("phonenumberuser",null);
                if(retrivedToken !=null)
                {
                    startActivity(new Intent(getActivity(), BookingScreen.class));

                }
                else
                {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.custom_result);
                    dialog.show();
                }


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
        offer_one=view.findViewById(R.id.imag_home_offer_one);
        offer_two=view.findViewById(R.id.imag_home_offer_two);
        title_one=view.findViewById(R.id.title_one_offer);
        tile_two = view.findViewById(R.id.title_two_offer);
        start_one = view.findViewById(R.id.start_id_one);
        start_two = view.findViewById(R.id.start_id_two);
        end_one = view.findViewById(R.id.end_id_one);
        end_two = view.findViewById(R.id.end_id_two);
        pric_old_one = view.findViewById(R.id.old_pric_one_id);
        price_old_two = view.findViewById(R.id.old_pric_two_id);
        price_new_one = view.findViewById(R.id.new_pric_one_id);
        price_new_two = view.findViewById(R.id.new_pric_two_id);
        numbercheck=0;
        number =setnewoffers();
        no_ffer_two=view.findViewById(R.id.text_two);
        no_offer_one=view.findViewById(R.id.text_one);
        sliderView = view.findViewById(R.id.imageSlider);
    }
    public int setnewoffers()
    {
        datum1=new Datum();
         datum2=new Datum();
        RetrofitClint.getInstance().getoffer().enqueue(new Callback<AllOffer>() {
            @Override
            public void onResponse(Call<AllOffer> call, Response<AllOffer> response) {

                if(response.body().getData().size()>0)
                {
                 if(response.body().getData().size()==1)
                 {
                     datum1=response.body().getData().get(response.body().getData().size()-1);
                     Glide.with(getActivity()).load("http://" + datum1.getFiles().get(0))
                             .into(offer_two);
                     tile_two.setText(datum1.getTitle());
                     start_two.setText(datum1.getStartTime().split(" ")[0]);
                     end_two.setText(datum1.getEndTime().split(" ")[0]);
                     price_old_two.setText(datum1.getPreviousPrice().toString());
                     price_new_two.setText(datum1.getCurrentPrice().toString());
                     no_ffer_two.setVisibility(View.GONE);

                     SharedPreferences mPrefs  = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);

                   //set variables of 'myObject', etc.

                     SharedPreferences.Editor prefsEditor = mPrefs.edit();
                     Gson gson = new Gson();
                     String json = gson.toJson(datum1);
                     prefsEditor.putString("offer1_1", json);
                     prefsEditor.commit();

                 }
                 if(response.body().getData().size()>1)
                 {
                     datum1=response.body().getData().get(response.body().getData().size()-1);
                     datum2=response.body().getData().get(response.body().getData().size()-2);
                     Glide.with(getActivity()).load("http://" + datum1.getFiles().get(0))
                             .into(offer_two);
                     tile_two.setText(datum1.getTitle());
                     start_two.setText(datum1.getStartTime().split(" ")[0]);
                     end_two.setText(datum1.getEndTime().split(" ")[0]);
                     price_old_two.setText(datum1.getPreviousPrice().toString());
                     price_new_two.setText(datum1.getCurrentPrice().toString());


                     Glide.with(getActivity()).load("http://" + datum2.getFiles().get(0))
                             .into(offer_one);
                     title_one.setText(datum2.getTitle());
                     start_one.setText(datum2.getStartTime().split(" ")[0]);
                     end_one.setText(datum2.getEndTime().split(" ")[0]);
                     pric_old_one.setText(datum2.getPreviousPrice().toString());
                     price_new_one.setText(datum2.getCurrentPrice().toString());

                     no_ffer_two.setVisibility(View.GONE);
                     no_offer_one.setVisibility(View.GONE);


                     SharedPreferences mPrefs  = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);

                     //set variables of 'myObject', etc.

                     SharedPreferences.Editor prefsEditor = mPrefs.edit();
                     Gson gson = new Gson();
                     String json = gson.toJson(datum1);
                     prefsEditor.putString("offer1", json);

                     Gson gson1 = new Gson();
                     String json1 = gson1.toJson(datum2);
                     prefsEditor.putString("offer2", json1);
                     prefsEditor.commit();


                 }
                }

            }

            @Override
            public void onFailure(Call<AllOffer> call, Throwable t) {

            }
        });


return  numbercheck;
    }
//    public void renewItems(View view) {
//        List<SliderItem> sliderItemList = new ArrayList<>();
//        //dummy data
//        for (int i = 0; i < 5; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }
//        adapter.renewItems(sliderItemList);
//    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        List<SliderItem> sliderItemList = new ArrayList<>();
        sliderItem.setDescription("City lab");
        sliderItem.setImageUrl(R.drawable.citylabimag);
        sliderItemList.add(sliderItem);
        SliderItem sliderItem1 = new SliderItem();
        sliderItem1.setDescription("City lab");
        sliderItem1.setImageUrl(R.drawable.sliderone);
        sliderItemList.add(sliderItem1);
        SliderItem sliderItem2 = new SliderItem();
        sliderItem2.setDescription("City lab");
        sliderItem2.setImageUrl(R.drawable.citylabtwo);
        sliderItemList.add(sliderItem2);
        adapter.renewItems(sliderItemList);
    }
}