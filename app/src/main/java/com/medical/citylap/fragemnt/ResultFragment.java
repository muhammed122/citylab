package com.medical.citylap.fragemnt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.medical.citylap.Adapter.AdapterResult;
import com.medical.citylap.Adapter.OfferAdapter;
import com.medical.citylap.R;
import com.medical.citylap.activity.SplashScreen;
import com.medical.citylap.helperfunction.LoadingDialog;
import com.medical.citylap.modles.Datum;
import com.medical.citylap.modles.Result;
import com.medical.citylap.modles.ResultApi;
import com.medical.citylap.modles.Resultss;
import com.medical.citylap.viewModel.ResultuserViewmodle;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ResultFragment extends Fragment {

ArrayList<Result> listofresult=new ArrayList<>();
    ArrayList<Resultss> listofresultapi=new ArrayList<>();
AdapterResult adapterResult;
RecyclerView mRecyclerView;
ArrayList<String >images1=new ArrayList<>();
ResultuserViewmodle resultuserViewmodle;
    TextView tvView;
    ImageView imgview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_result, container, false);
        intilazation( view);
        //set fack data result
//        images1.add("1");
//        images1.add("65");
//        images1.add("one");
//        images1.add("one");
//        images1.add("one");
//        images1.add("one");
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));
//        listofresult.add(new Result("21554","35145",images1));

        if (isConnected()) {
            LoadingDialog.showDialog(getActivity());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterResult=new AdapterResult(getActivity());
            resultuserViewmodle= ViewModelProviders.of(getActivity()).get(ResultuserViewmodle.class);
            resultuserViewmodle.getResultuser(SplashScreen.token_user).observe(getViewLifecycleOwner(), new Observer<ResultApi>() {
                @Override
                public void onChanged(ResultApi resultApi) {
                    listofresultapi= (ArrayList<Resultss>) resultApi.getData();
                  //  Log.d(TAG, "onChanged: "+resultApi.getData().get(0).getFiles().get(0));
                  //  adapterResult.setlist2(resultApi);
                  //  mRecyclerView.setAdapter(adapterResult);
                }
            });
            LoadingDialog.hideDialog();
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            tvView.setVisibility(View.VISIBLE);
            imgview.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }



        return view;
    }
    public void intilazation(View view)
    {

        mRecyclerView = view.findViewById(R.id.recyclerview_result);

        tvView=view.findViewById(R.id.nointerntid);
        imgview=view.findViewById(R.id.imageView2_no);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}