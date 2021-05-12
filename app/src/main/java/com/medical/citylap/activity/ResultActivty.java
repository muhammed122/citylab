package com.medical.citylap.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.medical.citylap.Adapter.AdapterResult;
import com.medical.citylap.R;
import com.medical.citylap.helperfunction.LoadingDialog;
import com.medical.citylap.modles.Result;
import com.medical.citylap.modles.ResultApi;
import com.medical.citylap.modles.Resultcopy;
import com.medical.citylap.modles.Resultss;
import com.medical.citylap.viewModel.ResultuserViewmodle;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class ResultActivty extends AppCompatActivity {
    ArrayList<Result> listofresult=new ArrayList<>();
    ArrayList<Resultcopy> listofresultapi=new ArrayList<>();
    AdapterResult adapterResult;
    RecyclerView mRecyclerView;
    ArrayList<String >images1=new ArrayList<>();
    ResultuserViewmodle resultuserViewmodle;
    TextView tvView;
    ImageView imgview;
    ImageView imageView_back;
    ProgressBar progressBar;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activty);
        intilazation();

        if (isConnected()) {
            //LoadingDialog.showDialog(this);
            progressBar.setVisibility(View.VISIBLE);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapterResult=new AdapterResult(this);
            resultuserViewmodle= ViewModelProviders.of(this).get(ResultuserViewmodle.class);
            resultuserViewmodle.getResultuser(SplashScreen.token_user).observe(this, new Observer<ResultApi>() {
                @Override
                public void onChanged(ResultApi resultApi) {
                    if(resultApi.getData().size()==0)
                    {
                        mRecyclerView.setVisibility(View.GONE);
                        tvView.setVisibility(View.VISIBLE);
                        tvView.setText("لا يوجد نتائج حاليا");
                    }
                    Log.d("TAG", "onResponse:result: "+SplashScreen.token_user);
                    for(int i=0;i<resultApi.getData().size();i++)
                    {Resultcopy re=new Resultcopy();
                        listofresultapi.add(re);
                    }

                    adapterResult.setlist2(resultApi,listofresultapi);
                    mRecyclerView.setAdapter(adapterResult);

                }
            });

            progressBar.setVisibility(View.GONE);
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            tvView.setVisibility(View.VISIBLE);
            imgview.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        LoadingDialog.hideDialog();

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivty.this,Home.class));

            }
        });
    }
    public void intilazation()
    {

        mRecyclerView = findViewById(R.id.recyclerview_result);
        tvView=findViewById(R.id.nointerntid);
        imgview=findViewById(R.id.imageView2_no);
        imageView_back=findViewById(R.id.imagebutton_back_from_result_to_home);
        progressBar=findViewById(R.id.prograbarresult);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultActivty.this,Home.class));

    }
}