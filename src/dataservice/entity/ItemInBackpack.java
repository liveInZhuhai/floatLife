package dataservice.entity;

/**
 * 这真的是背包里的物品
 * Created by D on 2016/12/23.
 */
public class ItemInBackpack {
    private int itemId,itemCount,itemPrize;

    public ItemInBackpack(int itemId, int itemCount, int itemPrize) {
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemPrize = itemPrize;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemPrize() {
        return itemPrize;
    }

    public void setItemPrize(int itemPrize) {
        this.itemPrize = itemPrize;
    }
}
