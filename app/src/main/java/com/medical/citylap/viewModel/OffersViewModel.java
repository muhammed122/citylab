package com.medical.citylap.viewModel;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.medical.citylap.RetrofitClint;

import com.medical.citylap.helperfunction.LoadingDialog;
import com.medical.citylap.modles.AllOffer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersViewModel extends ViewModel {

    MutableLiveData<AllOffer> allOfferMediatorLiveData=new MediatorLiveData<>();

    public MutableLiveData<AllOffer> getAllOffer()
    {

        RetrofitClint.getInstance().getoffer().enqueue(new Callback<AllOffer>() {
            @Override
            public void onResponse(Call<AllOffer> call, Response<AllOffer> response) {
                if(response.isSuccessful()) {
                    allOfferMediatorLiveData.setValue(response.body());
                    //  Log.e("offerfragment", "onResponse: "+response.body().getData() );
                    //  Log.d("offerfragment", "onResponse: "+response.body(). getData().get(0).getTitle());
                    // Log.v("offerfragment", "onResponse: "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<AllOffer> call, Throwable t) {
                Log.e("offerfragment", "onResponse: "+t.getMessage() );
            }
        });
return allOfferMediatorLiveData;
    }
}
