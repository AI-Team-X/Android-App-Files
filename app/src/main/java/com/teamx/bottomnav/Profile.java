package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    FloatingActionButton btn;
    TextView fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn =(FloatingActionButton) findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, EditProfile.class));
            }
        });
    }

    private void getAccountDetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        // query 1
        Query query = reference.child(getString(R.string.dbnode))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    User user = singleSnapshot.getValue(User.class);
                    Log.d("TAG", "on Data Changed (Query method 1) found user: " + user.toString());
                    //fullname.setText(user.getUser_full_name());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // query 2
        Query query2 = reference.child(getString(R.string.dbnode))
                .orderByChild(getString(R.string.field_name_phone_number))
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    User user = singleSnapshot.getValue(User.class);
                    Log.d("TAG", "on Data Changed (Query method 1) found user: " + user.toString());
                    //fullname.setText(user.getUser_full_name());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      //  email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }
}