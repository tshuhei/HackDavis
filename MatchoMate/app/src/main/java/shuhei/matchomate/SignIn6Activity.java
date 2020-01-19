package shuhei.matchomate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn6Activity extends AppCompatActivity {
    private Button done;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    private ImageView avatar5;
    private ImageView avatar6;
    private int avatarSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in6);
        setTitle(R.string.signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        done = (Button)findViewById(R.id.done);
        avatar1 = (ImageView)findViewById(R.id.avatar1);
        avatar2 = (ImageView)findViewById(R.id.avatar2);
        avatar3 = (ImageView)findViewById(R.id.avatar3);
        avatar4 = (ImageView)findViewById(R.id.avatar4);
        avatar5 = (ImageView)findViewById(R.id.avatar5);
        avatar6 = (ImageView)findViewById(R.id.avatar6);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("users").child(mUserId).child("items").child("avatar").setValue(avatarSelection);
                loadMainActivity();
            }
        });

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar1;
            }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar2;
            }
        });

        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar3;
            }
        });

        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar4;
            }
        });

        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar5;
            }
        });

        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarSelection = R.drawable.avatar6;
            }
        });
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
