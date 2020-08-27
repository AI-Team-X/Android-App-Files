package com.teamx.bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindExpert extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_expert);

        final ArrayList<MessageModel> messagesList = new ArrayList<>();
               // for (int i=0;i<4;i++) {
        messagesList.add(new MessageModel("Mr Azeez","lawalazeez@gmail.com","Osun state", "Business man"));
        messagesList.add(new MessageModel("Mr Toba", "tobaakinloye@gmail.com", "Lagos state", "Famer"));
        messagesList.add(new MessageModel("Mr Bola","bolaakintubu@gmail.com", "Lagos state", "Business man"));
        AllUsersListAdapter adapter = new AllUsersListAdapter(this, messagesList);

        recyclerView = findViewById(R.id.rc_farm_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindExpert.this, FindBuyer.class));
            }
        });
              //  }
    }
}