package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    FloatingActionButton btn;
    TextView fullname,phonenumber, otherpn, sor, cor,soor, email, yof, ha, nin, farmlocation ;
    ImageView imageView;


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
        getAccountDetails();
    }

    private void getAccountDetails(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        User user = dataSnapshot.getValue(User.class);
                        fullname.setText(user.getUser_full_name());
                        email.setText(user.getUser_email());
                        phonenumber.setText(user.getUser_phone_number());
                        otherpn.setText(user.getOtherPhoneNumber());
                        farmlocation.setText(user.getFarmLocation());
                        sor.setText(user.getStateOfOrigin());
                        soor.setText(user.stateOfResidence);
                        yof.setText(user.getYearsOfFarming());
                        cor.setText(user.getCityOfResidenc());
                        ha.setText(user.getHomeAddress());
                        nin.setText(user.getUser_nin());

                        String imguri = user.getProfilePic();
                        Picasso.get().load(imguri).placeholder(R.drawable.profile_icon).into(imageView);

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // query 1
       /* Query query = reference.child(getString(R.string.dbnode))
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
      //  email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());*/
    }
}