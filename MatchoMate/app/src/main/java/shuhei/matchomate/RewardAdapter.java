package shuhei.matchomate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RewardAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<RewardItem> rewardItemList;

    public RewardAdapter(Context context, List<RewardItem> rewardItemList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.rewardItemList = rewardItemList;
    }

    ListView rewardList;


    @Override
    public int getCount() {
        return rewardItemList.size();
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
        view = inflater.inflate(R.layout.list_reward, null);
        TextView itemName = (TextView)view.findViewById(R.id.itemName);
        TextView itemPoint = (TextView)view.findViewById(R.id.itemPoint);
        TextView itemDescription = (TextView)view.findViewById(R.id.itemDescription);
        ImageView itemPhoto = (ImageView)view.findViewById(R.id.itemPhoto);
        itemName.setText(rewardItemList.get(i).getItemName());
        itemDescription.setText(rewardItemList.get(i).getItemDescription());
        itemPhoto.setImageResource(rewardItemList.get(i).getItemPhoto());
        itemPoint.setText(rewardItemList.get(i).getItemPoint());


        return view;
    }
}
