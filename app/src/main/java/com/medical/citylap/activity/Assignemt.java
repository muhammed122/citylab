package com.medical.citylap.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medical.citylap.R;

public class Assignemt extends AppCompatActivity {
    Button convert, clear;
    EditText dollar, pund;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignemt);
        inti();
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Assignemt.this, dollar.getText(), Toast.LENGTH_SHORT).show();
                if (dollar.getText().toString().equals("") && pund.getText().toString().equals("")) {
                    Toast.makeText(Assignemt.this, "please enter data ", Toast.LENGTH_SHORT).show();
                }
                if (dollar.getText().toString().equals("") && !pund.getText().toString().equals("")) {
                    String dolla = pund.getText().toString();
                    int dollaer = Integer.parseInt(dolla) / 15;
                    dollar.setText(dollaer + "");
                }
                if (!dollar.getText().toString().equals("") && pund.getText().toString().equals("")) {
                    String dolla = dollar.getText().toString();
                    int dollaer = Integer.parseInt(dolla) * 15;
                    pund.setText(dollaer + "");
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollar.getText().clear();
                pund.getText().clear();
            }
        });
    }
    public void inti() {
        convert = findViewById(R.id.convert);
        clear = findViewById(R.id.clear);
        dollar = findViewById(R.id.dollar);
        pund = findViewById(R.id.pound);
    }
}