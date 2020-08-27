package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    EditText chat1, chat2;
    Button send;
    ImageButton sendImg;
    RecyclerView recyclerView;
    final List<Chat> messagesList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ChatAdapter chatAdapter;
    TextView receiverName, receiverProfileImage, receiverMessage;
    DatabaseReference reference;
    String messageReceiverID, meassageSenderID;
    String messageReceivername, savecurrentdate, savecurrenttime;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendImg = findViewById(R.id.img);
      //  receiverName = findViewById(R.id.text_message_name);
      //  receiverMessage = findViewById(R.id.text_message_body);
        chat1 = findViewById(R.id.edittext_chatbox);
        send = findViewById(R.id.button_chatbox_send);
        reference = FirebaseDatabase.getInstance().getReference();
        messageReceiverID = getIntent().getExtras().get("R_user_id").toString();
        messageReceivername = getIntent().getExtras().get("R_user_name").toString();

        firebaseAuth = FirebaseAuth.getInstance();
        meassageSenderID = firebaseAuth.getCurrentUser().getUid();

        chatAdapter = new ChatAdapter(messagesList);
        recyclerView = findViewById(R.id.reyclerview_message_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
        
        fetchMessages();



    }

    private void fetchMessages() {
        reference.child("Messages").child(meassageSenderID).child(messageReceiverID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    messagesList.add(chat);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendMessage(){
        String text = chat1.getText().toString();
        if (TextUtils.isEmpty(text)){
            Toast.makeText(ChatActivity.this, "Cannot Send Empty Message", Toast.LENGTH_SHORT).show();
        }
        else{
            String message_sender_ref = "Messages/" + meassageSenderID + "/" + messageReceiverID;
            String message_receiver_ref = "Messages/" + messageReceiverID + "/" + meassageSenderID;

            DatabaseReference databaseReference_key = reference.child("Messages").child(meassageSenderID).child(messageReceiverID).push();
            String message_push_id = databaseReference_key.getKey();

            Calendar calendar_date = Calendar.getInstance();
            SimpleDateFormat current_date = new SimpleDateFormat("dd-mm-yyyy");
            savecurrentdate = current_date.format(calendar_date.getTime());

            Calendar calendar_date1 = Calendar.getInstance();
            SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a");
            savecurrenttime = sfd.format(calendar_date1.getTime());

            Map message_text_body = new HashMap();
            message_text_body.put("message", text);
            message_text_body.put("date", savecurrentdate);
            message_text_body.put("time", savecurrenttime);
            message_text_body.put("type", "text");
            message_text_body.put("from" , meassageSenderID);

            Map message_body_details = new HashMap();
            message_body_details.put(message_sender_ref + "/" + message_push_id , message_text_body);
            message_body_details.put(message_receiver_ref + "/" + message_push_id , message_text_body);

            reference.updateChildren(message_body_details).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ChatActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                        chat1.setText("");
                    }
                    else{
                        Toast.makeText(ChatActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        chat1.setText("");
                    }

                }
            });

        };

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
               /* FirebaseDatabase.getInstance().getReference().push().setValue(new Chat(chat1.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));*/
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
        //DisplayChat();
        /*adapter = new ChatAdapter();
        String senderid = adapter.onBindViewHolder();
        String messageReceiverID = adapter.userDetailIDs();

        reference.child("Chat").child(senderid).child(messageReceiverID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Chat chat = snapshot.getValue(Chat.class);
                messageList.add(chat);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void DisplayChat(){
        adapter = new ChatAdapter();
        String messageReceiverID = adapter.userDetailIDs();
        reference.child("Chat").child(messageReceiverID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Chat chat = snapshot.getValue(Chat.class);
                    messageList.add(chat);
                    adapter.notifyDataSetChanged();
                    final String userName = snapshot.child("sender").getValue().toString();
                   // final String profileImage = snapshot.child("imageUri").getValue().toString();
                   // final String message = snapshot.child("message").getValue().toString();
                  //  receiverMessage.setText(message);
                    receiverName.setText(userName);
                    adapter = new ChatAdapter( messageList);

                    recyclerView = findViewById(R.id.reyclerview_message_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                    recyclerView.setAdapter(adapter);
                    //Glide.with(ChatActivity.this).load(profileImage).into(showUserProfile);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter = new ChatAdapter( messageList);

                recyclerView = findViewById(R.id.reyclerview_message_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }*/
}