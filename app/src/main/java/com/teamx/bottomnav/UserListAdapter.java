package com.teamx.bottomnav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    List<User> userList;

    public UserListAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.find_people,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindView(userList.get(position));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView email, fullname;
        ImageView showUserProfile;
        public ViewHolder(View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.show_user_email);
            //fullname=itemView.findViewById(R.id.show_user_name);
            //showUserProfile=itemView.findViewById(R.id.show_user_profile);
        }
        public void bindView(User user){
            email.setText(user.getUser_email());
           // fullname.setText(user.getUser_full_name());
           // Glide.with(showUserProfile.getContext()).load(user.getProfilePic()).into(showUserProfile);


        }
    }
}

