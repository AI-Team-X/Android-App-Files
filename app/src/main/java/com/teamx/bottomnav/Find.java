package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Find extends AppCompatActivity {
    private RecyclerView recylerview;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        reference = FirebaseDatabase.getInstance().getReference().child("all_users");


        recylerview = findViewById(R.id.rc_farm_list);
        recylerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Find.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recylerview.setLayoutManager(linearLayoutManager);

       DisplayallFarmer();
    }

    private void DisplayallFarmer() {
        Query query = FirebaseDatabase.getInstance().getReference().child("all_users").limitToLast(20);

        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();


       FirebaseRecyclerAdapter<User, FindViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, FindViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull FindViewHolder holder, int position, @NonNull User model) {
               holder.setUser_full_name(model.getUser_full_name());
               holder.setUser_email(model.getUser_email());
               holder.setUser_type(model.getUser_type());
               holder.setProfilePic(getApplicationContext(), model.getProfilePic());

               final String user_id = getRef(position).getKey();
               final String user_name = model.getUser_full_name();
               holder.mview.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent chatintent = new Intent(Find.this, ChatActivity.class);
                       chatintent.putExtra("R_user_id",user_id);
                       chatintent.putExtra("R_user_name",user_name);
                       startActivity(chatintent);
                   }
               });
           }

           @NonNull
           @Override
           public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.find_people, parent, false);
               return new FindViewHolder(view);
           }
       };
        recylerview.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    //    firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  firebaseRecyclerAdapter.stopListening();
    }

    public static  class FindViewHolder extends RecyclerView.ViewHolder{

        View mview;
        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void setProfilePic(Context context, String profilePic){

            ImageView imageView = mview.findViewById(R.id.show_user_profile);

            Picasso.get().load(profilePic).placeholder(R.drawable.profile_icon).into(imageView);
        }

        public void setUser_full_name(String user_full_name){
            TextView name = mview.findViewById(R.id.show_user_name);
            name.setText(user_full_name);
        }
        public void setUser_email(String user_email){
            TextView email = mview.findViewById(R.id.show_user_email);
            email.setText(user_email);
        }
        public void setUser_type(String user_type){
            TextView type = mview.findViewById(R.id.show_user_type);
            type.setText(user_type);
        }
    }
}