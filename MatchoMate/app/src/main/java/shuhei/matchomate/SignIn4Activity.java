package shuhei.matchomate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn4Activity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private Button next;
    private EditText inputAge;
    private EditText inputWeight;
    private String strAge;
    private String strWeight;
    private Spinner spinner_ft;
    private Spinner spinner_in;
    private String strFt;
    private String strIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in4);
        setTitle(R.string.signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        next = (Button)findViewById(R.id.next);
        inputAge = (EditText)findViewById(R.id.inputAge);
        inputWeight = (EditText)findViewById(R.id.inputWeight);
        spinner_ft = (Spinner)findViewById(R.id.spinner_ft);
        spinner_in = (Spinner)findViewById(R.id.spinner_in);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAge = inputAge.getText().toString();
                strWeight = inputWeight.getText().toString();
                mDatabase.child("users").child(mUserId).child("items").child("age").setValue(strAge);
                mDatabase.child("users").child(mUserId).child("items").child("weight").setValue(strWeight);
                mDatabase.child("users").child(mUserId).child("items").child("ft").setValue(strFt);
                mDatabase.child("users").child(mUserId).child("items").child("in").setValue(strIn);
                loadSignIn5Activity();
            }
        });

        spinner_ft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                strFt = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_in.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                strIn = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadSignIn5Activity(){
        Intent intent = new Intent(this, SignIn5Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
