package shuhei.matchomate;

import android.view.LayoutInflater;

public class RewardItem {
    private String itemName;
    private String itemPoint;
    private int itemDescription;
    private int itemPhoto;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPoint() {
        return itemPoint;
    }

    public void setItemPoint(String itemPoint) {
        this.itemPoint = itemPoint;
    }

    public int getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(int itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(int itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public RewardItem(String itemName, String itemPoint, int itemDescription, int itemPhoto) {
        this.itemName = itemName;
        this.itemPoint = itemPoint;
        this.itemDescription = itemDescription;
        this.itemPhoto = itemPhoto;
    }
}
