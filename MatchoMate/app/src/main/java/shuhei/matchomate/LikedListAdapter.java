package shuhei.matchomate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class LikedListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<UserItem> userList;
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public LikedListAdapter(Context context, List<UserItem> userList){
        this.context = context;
        this.userList = userList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_home, null);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView gymLocation = (TextView)view.findViewById(R.id.gymLocation);
        TextView exerciseField = (TextView)view.findViewById(R.id.exerciseField);
        TextView workoutExperience = (TextView)view.findViewById(R.id.workoutExperience);
        name.setText(userList.get(i).getNickname());
        gymLocation.setText(userList.get(i).getGymLocation());
        exerciseField.setText(userList.get(i).getExerciseField());
        workoutExperience.setText(userList.get(i).getExperience());
        return view;
    }
}
