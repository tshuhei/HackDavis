package shuhei.matchomate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MatchAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<UserItem> userList;
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public MatchAdapter(Context context, List<UserItem> userList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.userList = userList;
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
        view = inflater.inflate(R.layout.list_matching, null);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView gymLocation = (TextView)view.findViewById(R.id.gymLocation);
        TextView exerciseField = (TextView)view.findViewById(R.id.exerciseField);
        TextView workoutExperience = (TextView)view.findViewById(R.id.workoutExperience);
        ImageView avatarView = (ImageView)view.findViewById(R.id.avatarView);
        UserItem userItem = (UserItem)userList.get(i);
        name.setText(userItem.getNickname());
        gymLocation.setText(userItem.getGymLocation());
        exerciseField.setText(userItem.getExerciseField());
        workoutExperience.setText(userItem.getExperience());
        avatarView.setImageResource(new Integer(userList.get(i).getAvatar().toString()));
        return view;
    }
}
