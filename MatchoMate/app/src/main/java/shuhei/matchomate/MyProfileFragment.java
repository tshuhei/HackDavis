package shuhei.matchomate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView myPhoto;
    private ImageView avatarView;
    private TextView myGender;
    private TextView myName;
    private TextView myGymLocation;
    private TextView myExerciseField;
    private TextView myExperience;
    private TextView myAge;
    private TextView myFt;
    private TextView myIn;
    private TextView myWeight;
    private TextView myBio;

    private OnFragmentInteractionListener mListener;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false );
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        myGender = (TextView)view.findViewById(R.id.myGender);
        myName = (TextView)view.findViewById(R.id.myName);
        myGymLocation = (TextView)view.findViewById(R.id.myGymLocation);
        myExerciseField = (TextView)view.findViewById(R.id.myExercideField);
        myExperience = (TextView)view.findViewById(R.id.myExperience);
        myAge = (TextView)view.findViewById(R.id.myAge);
        myFt = (TextView)view.findViewById(R.id.myFt);
        myIn = (TextView)view.findViewById(R.id.myIn);
        myWeight = (TextView)view.findViewById(R.id.myWeight);
        myBio = (TextView)view.findViewById(R.id.myBio);
        avatarView = (ImageView)view.findViewById(R.id.myPhoto);

        mDatabase.child("users").child(mUserId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String gender = (String) dataSnapshot.child("gender").getValue();
                String gymLocation = (String) dataSnapshot.child("gymLocation").getValue();
                String exerciseField = (String) dataSnapshot.child("exerciseField").getValue();
                String nickname = (String) dataSnapshot.child("nickname").getValue();
                String experience = (String) dataSnapshot.child("experience").getValue();
                String weight = (String)dataSnapshot.child("weight").getValue();
                String ft = (String)dataSnapshot.child("ft").getValue();
                String in = (String)dataSnapshot.child("in").getValue();
                String bio = (String)dataSnapshot.child("bio").getValue();
                String age = (String)dataSnapshot.child("age").getValue();
                Long avatar = (Long)dataSnapshot.child("avatar").getValue();
                if(avatar == null){
                    avatar = (long)R.drawable.profile;
                }
                myGender.setText(gender);
                myGymLocation.setText(gymLocation);
                myName.setText(nickname);
                myExerciseField.setText(exerciseField);
                myExperience.setText(experience);
                myWeight.setText(weight);
                myFt.setText(ft);
                myIn.setText(in);
                myBio.setText(bio);
                myAge.setText(age);
                avatarView.setImageResource(new Integer(avatar.toString()));

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
