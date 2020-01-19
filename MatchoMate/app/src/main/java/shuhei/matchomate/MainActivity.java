package shuhei.matchomate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, MyProfileFragment.OnFragmentInteractionListener
, LikedListFragment.OnFragmentInteractionListener, MatchingFragment.OnFragmentInteractionListener, RewardFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private List<String> matchList;
    private List<String> likeUserList;
    private List<String> likedUserList;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        matchList = new ArrayList<String>();
        likeUserList = new ArrayList<String>();
        likedUserList = new ArrayList<String>();

        if(mFirebaseUser != null){
            mUserId = mFirebaseUser.getUid();
        }else{
            loadInitialViewActivity();
        }

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch(menuItem.getItemId()){
                    case R.id.navigation_home:
                        toolbar.setTitle("Home");
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_profile:
                        toolbar.setTitle("Profile");
                        fragment = new MyProfileFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_match:
                        toolbar.setTitle("Match");
                        fragment = new MatchingFragment(matchList);
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_liked:
                        toolbar.setTitle("Liked");
                        fragment = new LikedListFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_reward:
                        toolbar.setTitle("Reward");
                        fragment = new RewardFragment();
                        loadFragment(fragment);
                        return true;

                }
                return true;
            }
        };

        mDatabase.child("users").child(mUserId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                likeUserList = (List<String>)dataSnapshot.child("likeUserId").getValue();
                likedUserList = (List<String>)dataSnapshot.child("likedUserId").getValue();
                int size = likeUserList.size();
                for(int i=0; i<size; i++){
                    if(likedUserList.contains(likeUserList.get(i))){
                        matchList.add(likeUserList.get(i));
                    }
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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    @Override
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
        if (id == R.id.logout) {
            mFirebaseAuth.signOut();
            loadInitialViewActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadInitialViewActivity(){
        Intent intent = new Intent(this, InitialViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
