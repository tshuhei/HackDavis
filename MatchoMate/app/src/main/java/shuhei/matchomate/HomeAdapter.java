package shuhei.matchomate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<UserItem> userList;
    private LayoutInflater inflater;

    public HomeAdapter(Context context,  List<UserItem> userList){
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
        ImageView avatarView = (ImageView)view.findViewById(R.id.avatarView);
        name.setText(userList.get(i).getNickname());
        gymLocation.setText(userList.get(i).getGymLocation());
        exerciseField.setText(userList.get(i).getExerciseField());
        workoutExperience.setText(userList.get(i).getExperience());
        Long myint = userList.get(i).getAvatar();
        avatarView.setImageResource(new Integer(userList.get(i).getAvatar().toString()));
        return view;
    }
}
