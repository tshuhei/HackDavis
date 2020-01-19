package shuhei.matchomate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RewardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RewardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private List<RewardItem> rewardItemList;
    private ListView rewardListView;
    private RewardAdapter rewardAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RewardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RewardFragment.\
     */
    // TODO: Rename and change types and number of parameters
    public static RewardFragment newInstance(String param1, String param2) {
        RewardFragment fragment = new RewardFragment();
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
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        rewardItemList = new ArrayList<RewardItem>();
        rewardListView = (ListView)view.findViewById(R.id.likedListView);
        RewardItem rewardItem1 = new RewardItem("Protein", "23",R.string.protein_des, R.drawable.supplement);
        RewardItem rewardItem2 = new RewardItem("Organic Protein", "34",R.string.organic_protein_des, R.drawable.supplement);
        RewardItem rewardItem3 = new RewardItem("EAA", "12",R.string.eaa_des, R.drawable.supplement);
        RewardItem rewardItem4 = new RewardItem("BCAA", "15",R.string.creatine_des, R.drawable.supplement);
        RewardItem rewardItem5 = new RewardItem("Creatine", "15",R.string.creatine_des, R.drawable.supplement);
        RewardItem rewardItem6 = new RewardItem("Glutamine", "15",R.string.glutamine_des, R.drawable.supplement);
        RewardItem rewardItem7 = new RewardItem("HMB", "12",R.string.hmb_des, R.drawable.supplement);
        RewardItem rewardItem8 = new RewardItem("Energy Drink", "6",R.string.energy_drink_des, R.drawable.supplement);
        RewardItem rewardItem9 = new RewardItem("Wrist Wrap", "8",R.string.wrist_wrap_des, R.drawable.dumbbell);
        RewardItem rewardItem10 = new RewardItem("Training Tube", "8",R.string.training_tube_des, R.drawable.dumbbell);
        RewardItem rewardItem11 = new RewardItem("Lifting Belt", "8",R.string.lifting_belt_des, R.drawable.dumbbell);
        RewardItem rewardItem12 = new RewardItem("Ab-Roller", "25",R.string.ab_roller_des, R.drawable.dumbbell);
        RewardItem rewardItem13 = new RewardItem("Push-Up Bars", "25",R.string.push_up_bars_des, R.drawable.dumbbell);

        rewardItemList.add(rewardItem1);
        rewardItemList.add(rewardItem2);
        rewardItemList.add(rewardItem3);
        rewardItemList.add(rewardItem4);
        rewardItemList.add(rewardItem5);
        rewardItemList.add(rewardItem6);
        rewardItemList.add(rewardItem7);
        rewardItemList.add(rewardItem8);
        rewardItemList.add(rewardItem9);
        rewardItemList.add(rewardItem10);
        rewardItemList.add(rewardItem11);
        rewardItemList.add(rewardItem12);
        rewardItemList.add(rewardItem13);

        rewardAdapter = new RewardAdapter(view.getContext(), rewardItemList);
        rewardListView.setAdapter(rewardAdapter);

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
