package com.teamx.bottomnav.Expert.ui.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.teamx.bottomnav.Expert.ui.ui.ExpertProfile;
import com.teamx.bottomnav.R;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    FloatingActionButton fab;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_expert_dashboard, container, false);

        fab = root.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExpertProfile.class));
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {

    }
}