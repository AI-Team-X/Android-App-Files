package com.teamx.bottomnav.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamx.bottomnav.AskExpert;
import com.teamx.bottomnav.ChatActivity;
import com.teamx.bottomnav.CheckCropDisease;
import com.teamx.bottomnav.CropDisease;
import com.teamx.bottomnav.FarmSalesSummary;
import com.teamx.bottomnav.FarmerProduce;
import com.teamx.bottomnav.Find;
import com.teamx.bottomnav.FindBuyer;
import com.teamx.bottomnav.FindExpert;
import com.teamx.bottomnav.MainActivity;
import com.teamx.bottomnav.Profile;
import com.teamx.bottomnav.R;
import com.teamx.bottomnav.ReportScam;
import com.teamx.bottomnav.Sell;

public class HomeFragment extends Fragment implements View.OnClickListener {
    CardView produce, buyers, chat, reportScam,profile,disease, sales;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        buyers = (CardView) root.findViewById(R.id.farmer_customers);
        chat = (CardView) root.findViewById(R.id.farmer_chat);
        reportScam = (CardView) root.findViewById(R.id.farmer_report_scam);
        profile = (CardView) root.findViewById(R.id.farmer_profile);
        disease = (CardView) root.findViewById(R.id.farmer_crop_disease);
        sales = (CardView) root.findViewById(R.id.farmer_sales);

      //  button_submit.setOnClickListener(this);
        objectOnClick();

        //produce.
        return root;

    }

    public void objectOnClick(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Profile.class));
            }
        });
        buyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FindExpert.class));
            }
        });
        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckCropDisease.class));
            }
        });
        reportScam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportScam.class));
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Sell.class));
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FarmSalesSummary.class));
            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}