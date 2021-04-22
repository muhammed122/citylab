package com.medical.citylap.fragemnt;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.medical.citylap.R;

public class Blood_Fragment extends Fragment {

    EditText Age,blood,wight;
    TextView information;
    Button show_information;
    CardView cardView;
    public Blood_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_, container, false);
    }
    public void inti(View view)
    {
        Age=view.findViewById(R.id.age_in_blood_leve);
        blood=view.findViewById(R.id.level_in_blood_leve);
        wight=view.findViewById(R.id.wight_in_blood_leve);
        information=view.findViewById(R.id.information_in_blood_leve);
        show_information=view.findViewById(R.id.confer_blood);
        cardView=view.findViewById(R.id.cardView_blood);
    }
}