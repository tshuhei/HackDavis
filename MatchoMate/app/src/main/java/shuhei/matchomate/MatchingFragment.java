package shuhei.matchomate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private ListView matchListView;
    private List<UserItem> userList;
    private Context context;
    private MatchAdapter matchAdapter;
    private List<String> matchList;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MatchingFragment(List<String> matchList) {
        // Required empty public constructor
        this.matchList = matchList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchingFragment newInstance(String param1, String param2) {
        MatchingFragment fragment = new MatchingFragment(null);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matching, container, false);
        context = view.getContext();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userList = new ArrayList<UserItem>();
        matchListView = (ListView)view.findViewById(R.id.match_list);

        mDatabase.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String userName = (String)dataSnapshot.getKey();
                if(matchList == null){
                    matchList = new ArrayList<String>();
                }
                if(matchList.contains(userName)){
                    String age = (String)dataSnapshot.child("items").child("age").getValue();
                    String bio = (String)dataSnapshot.child("items").child("bio").getValue();
                    String exerciseField = (String)dataSnapshot.child("items").child("exerciseField").getValue();
                    String experience = (String)dataSnapshot.child("items").child("experience").getValue();
                    String ft = (String)dataSnapshot.child("items").child("ft").getValue();
                    String gymLocation = (String)dataSnapshot.child("items").child("gymLocation").getValue();
                    String in = (String)dataSnapshot.child("items").child("in").getValue();
                    String nickname = (String)dataSnapshot.child("items").child("nickname").getValue();
                    String userType = (String)dataSnapshot.child("items").child("userType").getValue();
                    String weight = (String)dataSnapshot.child("items").child("weight").getValue();
                    String gender = (String)dataSnapshot.child("items").child("gender").getValue();
                    String userId = (String)dataSnapshot.child("items").child("userId").getValue();
                    UserItem userItem = new UserItem(age,bio,exerciseField,experience,ft,gymLocation,in,nickname,userType,weight,userId,gender);
                    userList.add(userItem);
                    matchAdapter = new MatchAdapter(context, userList);
                    matchListView.setAdapter(matchAdapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
