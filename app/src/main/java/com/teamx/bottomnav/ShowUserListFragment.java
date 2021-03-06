package com.teamx.bottomnav;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowUserListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowUserListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerView showUserList;

    //  private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM_USER_LIST = "userList";
    private List<User> userList;
    public ShowUserListFragment() {
        // Required empty public constructor
    }
    private List<User> setUpUserList(){
        List<User> userList=new ArrayList<>();
        userList.add(new User("ram@gmail.com","1",""));
        userList.add(new User("shyam@gmail.com","1",""));
        userList.add(new User("hari@gmail.com","1",""));
        return userList;
    }


    public static ShowUserListFragment newInstance(List<User> userList) {
        ShowUserListFragment fragment = new ShowUserListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER_LIST, (Serializable) userList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userList = (List<User>) getArguments().getSerializable(ARG_PARAM_USER_LIST);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_show_user_list, container, false);
        defineView(view);
        return view;
    }

    private void defineView(View view){
        showUserList=view.findViewById(R.id.show_userL_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showUserList.setLayoutManager(layoutManager);

        UserListAdapter listAdapter=new UserListAdapter(userList);
        showUserList.setAdapter(listAdapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //  mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}