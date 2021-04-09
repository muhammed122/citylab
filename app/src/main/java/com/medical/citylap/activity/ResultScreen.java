package com.medical.citylap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.medical.citylap.R;
import com.medical.citylap.viewModel.ResultuserViewmodle;

public class ResultScreen extends AppCompatActivity {
ResultuserViewmodle resultuserViewmodle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        resultuserViewmodle = ViewModelProviders.of(this).get(ResultuserViewmodle.class);

    }
}