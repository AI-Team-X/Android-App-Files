package com.teamx.bottomnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;

public class AllUsersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    ArrayList<MessageModel> list;
    public static final int MESSAGE_TYPE_IN = 1;
    public static final int MESSAGE_TYPE_OUT = 2;

    public AllUsersListAdapter(Context context, ArrayList<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    private class MessageInViewHolder extends RecyclerView.ViewHolder {

        TextView username,email;
        TextView location,usertype;
        MessageInViewHolder(final View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.show_user_name);
            email = itemView.findViewById(R.id.show_user_email);
            location = itemView.findViewById(R.id.show_user_location);
            usertype = itemView.findViewById(R.id.show_user_type);
        }
        void bind(int position) {
            MessageModel messageModel = list.get(position);
            username.setText(messageModel.username);
            email.setText(messageModel.email);
            location.setText(messageModel.userloaction);
            usertype.setText(messageModel.usertype);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new AllUsersListAdapter.MessageInViewHolder(LayoutInflater.from(context).inflate(R.layout.find_people, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((AllUsersListAdapter.MessageInViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).messageType;
    }
}
