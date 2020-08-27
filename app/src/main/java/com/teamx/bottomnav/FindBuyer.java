package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FindBuyer extends AppCompatActivity {
    EditText chat1, chat2;
    Button send;
    RecyclerView recyclerView;
    User user;
    ArrayList<User> allusrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat1 = findViewById(R.id.edittext_chatbox);
        // chat2 = findViewById(R.id.
        //
        // text21);
        send = findViewById(R.id.button_chatbox_send);

        allusrs = new ArrayList<>();
        //allusrs.add()
        // Populate dummy messages in List, you can implement your code here


        final ArrayList<MessageModel> messagesList = new ArrayList<>();
       // messagesList.add(new MessageModel(chat1.getText().toString() ? CustomAdapter.MESSAGE_TYPE_IN : CustomAdapter.MESSAGE_TYPE_OUT));


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<4;i++) {
                    messagesList.add(new MessageModel(chat1.getText().toString(), i % 2 == 0 ? CustomAdapter.MESSAGE_TYPE_IN : CustomAdapter.MESSAGE_TYPE_OUT));
                    CustomAdapter adapter = new CustomAdapter(FindBuyer.this, messagesList);

                    recyclerView = findViewById(R.id.reyclerview_message_list);
                    recyclerView.setLayoutManager(new GridLayoutManager(FindBuyer.this, 2));
                    recyclerView.setAdapter(adapter);
                }
            }
        });
       // messagesList.add(chat1.getText().toString());




    }
}





/*public class FindBuyer extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<FProfile> list;
    FirebaseStorage firestore;
    List<FProfile> contents;
    RVAdapter rvAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView showUserList;

    private static final String ARG_PARAM_USER_LIST = "userList";
    private List<User> userList;

    public FindBuyer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_buyer);
        if (getArguments() != null) {
            userList = (List<User>) getArguments().getSerializable(ARG_PARAM_USER_LIST);

        }

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

    }
    public static FindBuyer newInstance(List<User> userList) {
        ShowUserListFragment fragment = new ShowUserListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER_LIST, (Serializable) userList);
        fragment.setArguments(args);
        return fragment;
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/


   /* class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
    private List<User> setUpUserList(){
        final List<User> userList=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Chat").child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    userList.add(snapshot.getValue(User.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return userList;
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //  adapter.addFragment(new CuriosityModeFeatured(),"Featured");
        adapter.addFragment(new ShowUserListFragment().newInstance(setUpUserList()), "Users");
        adapter.addFragment(new ShowUserListFragment().newInstance(setUpChatListUsers()),"Chats");
        adapter.addFragment(new ShowUserListFragment().newInstance(setUpOnlineUsers()),"Online");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    private List<User> setUpChatListUsers(){
        List<User> userList=new ArrayList<>();
        userList.add(new User("ram@gmail.com","1",""));
        return userList;
    }
    private List<User> setUpOnlineUsers(){
        List<User> userList=new ArrayList<>();
        userList.add(new User("ram@gmail.com","1",""));
        userList.add(new User("hari@gmail.com","3",""));
        return userList;
    }
}*/