package com.medical.citylap.Interfacess;

import com.medical.citylap.modles.LoginRequst;
import com.medical.citylap.modles.Loginmodle;
import com.medical.citylap.modles.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("api/Results/All")
    public Call<List<Result>>getResults();

    @POST("api/Users/Register")
    public Call<Loginmodle>userlogin(@Body LoginRequst loginRequst);

}
