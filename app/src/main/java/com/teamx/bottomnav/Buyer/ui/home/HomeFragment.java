package com.teamx.bottomnav.Buyer.ui.home;

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

import com.teamx.bottomnav.Buyer.ui.Buy;
import com.teamx.bottomnav.Buyer.ui.BuyerProfile;
import com.teamx.bottomnav.CropDisease;
import com.teamx.bottomnav.FarmSalesSummary;
import com.teamx.bottomnav.Find;
import com.teamx.bottomnav.FindBuyer;
import com.teamx.bottomnav.Profile;
import com.teamx.bottomnav.R;
import com.teamx.bottomnav.ReportScam;
import com.teamx.bottomnav.Sell;

public class HomeFragment extends Fragment implements View.OnClickListener {
    CardView buycrop, findfarmer, reportScam,profile, salessum;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        buycrop = (CardView) root.findViewById(R.id.b_buy);
        findfarmer = (CardView) root.findViewById(R.id.b_find);
        reportScam = (CardView) root.findViewById(R.id.b_report_scam);
        profile = (CardView) root.findViewById(R.id.bprofile);
        salessum = (CardView) root.findViewById(R.id.b_sales_summary);
       // sales = (CardView) root.findViewById(R.id.farmer_sales);

        //  button_submit.setOnClickListener(this);
        objectOnClick();

        return root;
    }
    public void objectOnClick(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BuyerProfile.class));
            }
        });
        findfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Find.class));
            }
        });
        buycrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Buy.class));
            }
        });
        reportScam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportScam.class));
            }
        });
        salessum.setOnClickListener(new View.OnClickListener() {
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