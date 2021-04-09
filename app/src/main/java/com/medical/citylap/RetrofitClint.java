package com.medical.citylap;

import com.medical.citylap.Interfacess.API;
import com.medical.citylap.modles.LoginRequst;
import com.medical.citylap.modles.Loginmodle;
import com.medical.citylap.modles.Result;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {

    private static final String BASE_URL  = "http://citylab123-001-site1.htempurl.com/";
    private static RetrofitClint Instance;
    private API apiApi;

    public RetrofitClint() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
        .build();
        apiApi=retrofit.create(API.class);
    }

    public static RetrofitClint getInstance() {
        if(null==Instance)
        {
            Instance=new RetrofitClint();

        }
        return Instance;
    }
public Call <List<Result>>getResults()
{
    return apiApi.getResults();

}
public Call<Loginmodle>userlogin(LoginRequst login)
{
    return apiApi.userlogin(login);

}
 }
