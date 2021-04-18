package com.medical.citylap.fragemnt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.medical.citylap.Interfacess.API;
import com.medical.citylap.R;
import com.medical.citylap.RetrofitClint;
import com.medical.citylap.modles.Loginmodle;
import com.medical.citylap.modles.Signup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    public String pass=null;
    EditText password;
    Button loButton;
    ProgressBar progressBar;
    public API api;
    TextView textView_sign_up;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intilazation(view);
        textView_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new signupuser(), "NewFragmentTag");
                ft.commit();
            }
        });
        loButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), password.getText().toString(), Toast.LENGTH_SHORT).show();
                 loginfunction(password.getText().toString());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_login, container, false);

    }
    public void intilazation(View view)
    {

        password =view.findViewById(R.id.edittext_phon_number_for_login);
        loButton = view.findViewById(R.id.button_login_id);
        progressBar =view.findViewById(R.id.progresslogin);
        textView_sign_up=view.findViewById(R.id.loginfromsignup);

    }
    public int CheckVaildData(String password){
        if(password!=null)
        {
            if(password.length()<10||password.length()>13)
            {
                return 0;
            }

            return 1;

        }

        return 0;
    }
    public void loginfunction(String password)
    {
        RetrofitClint.getInstance().userlogin(password).enqueue(new Callback<Loginmodle>() {
            @Override
            public void onResponse(Call<Loginmodle> call, Response<Loginmodle> response) {
                if(response.isSuccessful())

               Toast.makeText(getContext(), response.body().getData().getToken().toString(), Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                preferences.edit().putString("phonenumberuser", password).apply();
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new Profilefragment(), "NewFragmentTag");
                ft.commit();
            }

            @Override
            public void onFailure(Call<Loginmodle> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}