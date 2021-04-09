package com.medical.citylap.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.medical.citylap.R;
import com.medical.citylap.RetrofitClint;
import com.medical.citylap.modles.LoginRequst;
import com.medical.citylap.modles.Loginmodle;
import com.medical.citylap.viewModel.Userviewmodle;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {
EditText password;
Button loButton;
ProgressBar progressBar;
private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        //intilazation();
//        String pass=password.getText().toString();
//        loButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //loginfunction(pass);
//            }
//        });


    }
    public void intilazation()
    {
        password =findViewById(R.id.edittext_phon_number_for_login);
        loButton = findViewById(R.id.button_login_id);
        progressBar =findViewById(R.id.progresslogin);
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

       LoginRequst login=new LoginRequst();
       login.setPhoneNumber(password);
       RetrofitClint.getInstance().userlogin(login).enqueue(new Callback<Loginmodle>() {
           @Override
           public void onResponse(Call<Loginmodle> call, Response<Loginmodle> response) {

           if(response.isSuccessful())
           {
               Toast.makeText(LoginScreen.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();

           }
           else
               Toast.makeText(LoginScreen.this, "حدث خطاء", Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onFailure(Call<Loginmodle> call, Throwable t) {
               Toast.makeText(LoginScreen.this, t.getLocalizedMessage()+"", Toast.LENGTH_LONG).show();

           }
       });

   }
}