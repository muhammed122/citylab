package com.medical.citylap.viewModel;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medical.citylap.RetrofitClint;
import com.medical.citylap.modles.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultuserViewmodle extends ViewModel {

    MutableLiveData<List< Result>> resultmutbel=new MediatorLiveData<>();

    public void getResultuser()
    {
        RetrofitClint.getInstance().getResults().enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                resultmutbel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });

    }
}
