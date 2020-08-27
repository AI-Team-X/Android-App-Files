package com.teamx.bottomnav.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamx.bottomnav.CustomAdapter;
import com.teamx.bottomnav.DataPoint;
import com.teamx.bottomnav.FindBuyer;
import com.teamx.bottomnav.MessageModel;
import com.teamx.bottomnav.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    LineChart linechart;
    //FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    EditText chat1;
    RecyclerView recyclerView;
    LineData lineData;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    Button send;

  //  private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        chat1 = root.findViewById(R.id.edittext_chatbox);
        recyclerView = root.findViewById(R.id.reyclerview_message_list);
        send = root.findViewById(R.id.button_chatbox_send);

        // Populate dummy messages in List, you can implement your code here

        final ArrayList<MessageModel> messagesList = new ArrayList<>();
        // messagesList.add(new MessageModel(chat1.getText().toString() ? CustomAdapter.MESSAGE_TYPE_IN : CustomAdapter.MESSAGE_TYPE_OUT));

        chat1 = root.findViewById(R.id.edittext_chatbox);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<4;i++) {
                    messagesList.add(new MessageModel(chat1.getText().toString(), i % 2 == 0 ? CustomAdapter.MESSAGE_TYPE_IN : CustomAdapter.MESSAGE_TYPE_OUT));
                    CustomAdapter adapter = new CustomAdapter(getContext(), messagesList);


                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }
            }
        });
        return root;
    }

}