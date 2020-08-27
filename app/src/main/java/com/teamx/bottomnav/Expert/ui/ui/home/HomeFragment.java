package com.teamx.bottomnav.Expert.ui.ui.home;

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

import com.teamx.bottomnav.Expert.ui.ui.ViewExpertProfile;
import com.teamx.bottomnav.Find;
import com.teamx.bottomnav.R;
import com.teamx.bottomnav.ReportScam;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    CardView chat, reportScam,profile;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_expert_home, container, false);
        profile = (CardView) root.findViewById(R.id.chat_farmer);
        chat = (CardView) root.findViewById(R.id.farmer_customers);
        reportScam = (CardView) root.findViewById(R.id.farmer_profile);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Find.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewExpertProfile.class));
            }
        });

        reportScam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportScam.class));
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {

    }
}