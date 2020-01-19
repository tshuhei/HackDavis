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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
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
    private String userId;;
    private ImageView userPhoto;
    private ImageView thumbUp;
    private ImageView avatarView;
    private TextView userGender;
    private TextView userName;
    private TextView userGymLocation;
    private TextView userExerciseField;
    private TextView userExperience;
    private TextView userAge;
    private TextView userFt;
    private TextView userIn;
    private TextView userWeight;
    private TextView userBio;
    private List<String> userLikedUserId;
    private List<String> myLikeUserId;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment(String userId) {
        // Required empty public constructor
        this.userId = userId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment(null);
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

        View view = inflater.inflate(R.layout.fragment_profile, container, false );
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();
        userGender = (TextView)view.findViewById(R.id.userGender);
        userName = (TextView)view.findViewById(R.id.userName);
        userGymLocation = (TextView)view.findViewById(R.id.userGymLocation);
        userExerciseField = (TextView)view.findViewById(R.id.userExerciseField);
        userExperience = (TextView)view.findViewById(R.id.userExperience);
        userAge = (TextView)view.findViewById(R.id.userAge);
        userFt = (TextView)view.findViewById(R.id.userFt);
        userIn = (TextView)view.findViewById(R.id.userIn);
        userWeight = (TextView)view.findViewById(R.id.userWeight);
        userBio = (TextView)view.findViewById(R.id.userBio);
        thumbUp = (ImageView)view.findViewById(R.id.thumbUp);
        avatarView = (ImageView)view.findViewById(R.id.userPhoto);

        mDatabase.child("users").child(mUserId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                myLikeUserId = (List<String>)dataSnapshot.child("likeUserId").getValue();
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
        mDatabase.child("users").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String gender= (String) dataSnapshot.child("gender").getValue();
                String gymLocation = (String) dataSnapshot.child("gymLocation").getValue();
                String exerciseField = (String) dataSnapshot.child("exerciseField").getValue();
                String nickname = (String) dataSnapshot.child("nickname").getValue();
                String experience = (String) dataSnapshot.child("experience").getValue();
                String weight = (String)dataSnapshot.child("weight").getValue();
                String ft = (String)dataSnapshot.child("ft").getValue();
                String in = (String)dataSnapshot.child("in").getValue();
                String bio = (String)dataSnapshot.child("introduction").getValue();
                String age = (String)dataSnapshot.child("age").getValue();
                Long avatar = (Long)dataSnapshot.child("avatar").getValue();
                if(avatar == null){
                    avatar = (long)R.drawable.profile;
                }
                userLikedUserId = (List<String>)dataSnapshot.child("likedUserId").getValue();
                userGender.setText(gender);
                userGymLocation.setText(gymLocation);
                userName.setText(nickname);
                userExerciseField.setText(exerciseField);
                userExperience.setText(experience);
                userWeight.setText(weight);
                userFt.setText(ft);
                userIn.setText(in);
                userBio.setText(bio);
                userAge.setText(age);
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

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userLikedUserId == null){
                    userLikedUserId = new ArrayList<String>();
                }
                if(myLikeUserId == null){
                    myLikeUserId = new ArrayList<String>();
                }
                if(!userLikedUserId.contains(mUserId)){
                    userLikedUserId.add(mUserId);
                }
                if(!myLikeUserId.contains(userId)){
                    myLikeUserId.add(userId);
                }
                mDatabase.child("users").child(mUserId).child("items").child("likeUserId").setValue(myLikeUserId);
                mDatabase.child("users").child(userId).child("items").child("likedUserId").setValue(userLikedUserId);
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
