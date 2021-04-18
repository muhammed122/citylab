package com.medical.citylap.Interfacess;

import com.medical.citylap.modles.AllOffer;
import com.medical.citylap.modles.Loginmodle;
import com.medical.citylap.modles.ResultApi;
import com.medical.citylap.modles.SimpleResponse;
import com.medical.citylap.modles.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {



    @FormUrlEncoded
    @POST("api/Users/Login")
    public Call<Loginmodle>userlogin(@Field("PhoneNumber") String phone);

    @GET("api/offers/all")
    public Call<AllOffer>offers();

    @GET("api/Results/All")
    @Headers("Accept:application/json")
    public Call<ResultApi>getResults(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/Users/Register")
   Call<SimpleResponse> addUser(@Field("Name") String name,
                                @Field("PhoneNumber") String phone);

    @GET("api/users/all")
    @Headers("Accept:application/json")
    Call<UsersResponse> getAllUsers(@Header("Authorization") String token);




}
