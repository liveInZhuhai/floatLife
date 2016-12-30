package dataservice.entity;

/**
 * 用于记录某一商品的价格突变
 * Created by D on 2016/12/29.
 */
public class PrizeEffect {
    private int itemId;
    private double val;

    public PrizeEffect(int itemId, double val) {
        this.itemId = itemId;
        this.val = val;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
