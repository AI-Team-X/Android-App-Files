package com.teamx.bottomnav;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyChatViewHolder> {

    public static final int VIEW_TYPE_ME = 1;
    public static final int VIEW_TYPE_OTHER = 2;
    List<Chat> mChats;
    Chat chat;
    DatabaseReference reference;
    String fromUserID;
    FirebaseAuth firebaseAuth;
    public ChatAdapter() {
    }


    @NonNull
    @Override
    public ChatAdapter.MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.findlayout1, parent, false);

        firebaseAuth = FirebaseAuth.getInstance();
        return new MyChatViewHolder(view);
    }

    public ChatAdapter(List<Chat> mChats) {
        this.mChats = mChats;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyChatViewHolder holder, int position) {

        String message_sender_id = firebaseAuth.getCurrentUser().getUid();
        Chat chat = mChats.get(position);

        String receiverID = chat.getFrom();
        String message_type = chat.getType();

        reference = FirebaseDatabase.getInstance().getReference().child(receiverID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String img = snapshot.child("profilePic").getValue().toString();

                    Picasso.get().load(img).placeholder(R.drawable.circle).into(holder.receiverImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (message_type.equals("text")){

            holder.receiverText.setVisibility(View.INVISIBLE);
            holder.receiverMsgTime.setVisibility(View.INVISIBLE);
            holder.receiverName.setVisibility(View.INVISIBLE);
            holder.receiverImage.setVisibility(View.INVISIBLE);

            if (receiverID.equals(message_sender_id)){
                holder.senderText.setBackgroundResource(R.drawable.greenrectangle);
                holder.senderText.setTextColor(Color.WHITE);
                holder.senderText.setGravity(Gravity.LEFT);
                holder.senderText.setText(chat.getMessage());
            }
            else {
                holder.senderText.setVisibility(View.INVISIBLE);
                holder.receiverText.setVisibility(View.VISIBLE);
                holder.receiverMsgTime.setVisibility(View.INVISIBLE);
                holder.receiverName.setVisibility(View.INVISIBLE);
                holder.receiverImage.setVisibility(View.INVISIBLE);

                holder.receiverText.setBackgroundResource(R.drawable.chatbubble);
                holder.receiverText.setTextColor(Color.WHITE);
                holder.receiverText.setGravity(Gravity.LEFT);
                holder.receiverText.setText(chat.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView senderText, receiverText, receiverName;
        private TextView senderMsgTime, receiverMsgTime;
        ImageView receiverImage;

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            senderText = (TextView) itemView.findViewById(R.id.text_messagesender_body);
            senderMsgTime=(TextView) itemView.findViewById(R.id.text_messagesender_time);
            receiverMsgTime=(TextView) itemView.findViewById(R.id.text_message_time);
            receiverName = itemView.findViewById(R.id.text_message_name);
            receiverText = itemView.findViewById(R.id.text_message_body);
            receiverImage = itemView.findViewById(R.id.image_message_profile);

        }
    }

    /*

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ME:
                View viewChatMine = layoutInflater.inflate(R.layout.findlayout1, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case VIEW_TYPE_OTHER:
                View viewChatOther = layoutInflater.inflate(R.layout.findlayout, parent, false);
                viewHolder = new OtherChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (TextUtils.equals(mChats.get(position).senderUid,
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {


            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        } else {

            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
         //   senderDetailIDs(position);


        }


    }
    public String userDetailIDs(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public String senderDetailIDs(int position){
        Chat chat = mChats.get(position);
        return chat.getReceiverUid();
    }


    private void configureMyChatViewHolder(final MyChatViewHolder myChatViewHolder, int position) {
        Chat chat = mChats.get(position);
        SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a");
        String date=sfd.format(new Date(chat.timestamp).getTime());
        myChatViewHolder.senderMsgTime.setText(date);


    }

    private void configureOtherChatViewHolder(final OtherChatViewHolder otherChatViewHolder, int position) {
        chat = mChats.get(position);
        SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a");
        String date=sfd.format(new Date(chat.timestamp).getTime());
        otherChatViewHolder.receiverMsgTime.setText(date);
       fromUserID = chat.getReceiverUid();

        reference.child("Chat").child(fromUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                     final String userName = snapshot.child("sender").getValue().toString();
                  //  final String profileImage = snapshot.child("imageUri").getValue().toString();
                    // final String message = snapshot.child("senderUid").getValue().toString();

                    otherChatViewHolder.receiverName.setText(userName);
                   // Picasso.with(holder.receiverProfilePicture.getContext()).load(profileImage)
                   //         .placeholder(R.drawable.circle).into(holder.receiverProfilePicture);
                   // Glide.with(ChatActivity.this).load(profileImage).into(showUserProfile);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(mChats.get(position).senderUid,
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }

    private static class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView txtChatMessage, txtUserAlphabet;
        private TextView senderMsgTime;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            txtChatMessage = (TextView) itemView.findViewById(R.id.text_message_body);
          //  txtUserAlphabet = (TextView) itemView.findViewById(R.id.text_view_user_alphabet);
            senderMsgTime=(TextView) itemView.findViewById(R.id.text_message_time);
        }
    }

    private static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        private TextView txtChatMessage;
        private TextView receiverName;
        private TextView receiverMsgTime;




        public OtherChatViewHolder(View itemView) {
            super(itemView);
            txtChatMessage = (TextView) itemView.findViewById(R.id.text_message_body);
            receiverName = (TextView) itemView.findViewById(R.id.text_message_name);
            receiverMsgTime=(TextView) itemView.findViewById(R.id.text_message_time);
            //receiverProfilePic =


        }
    }*/
}