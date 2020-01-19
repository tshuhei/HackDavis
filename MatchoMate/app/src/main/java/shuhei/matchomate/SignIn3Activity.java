package shuhei.matchomate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn3Activity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private EditText inputNickname;
    private EditText inputExperience;
    private String strNickname;
    private String strExperience;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in3);
        setTitle(R.string.signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        next = (Button)findViewById(R.id.next);
        inputNickname = (EditText)findViewById(R.id.inputNickName);
        inputExperience = (EditText)findViewById(R.id.inputYears);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strNickname = inputNickname.getText().toString();
                strExperience = inputExperience.getText().toString();
                mDatabase.child("users").child(mUserId).child("items").child("nickname").setValue(strNickname);
                mDatabase.child("users").child(mUserId).child("items").child("experience").setValue(strExperience);
                loadSignIn4Activity();
            }
        });
    }

    private void loadSignIn4Activity(){
        Intent intent = new Intent(this, SignIn4Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
