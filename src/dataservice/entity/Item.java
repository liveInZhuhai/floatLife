package dataservice.entity;



/**
 * Created by HK on 2016/12/12.
 */
public class Item {
    private int id;
    private String itemName;
    private double basePrize;
    private int reputationEffect;
    private int rare;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getBasePrize() {
        return basePrize;
    }

    public void setBasePrize(double basePrize) {
        this.basePrize = basePrize;
    }

    public int getReputationEffect() {
        return reputationEffect;
    }

    public void setReputationEffect(int reputationEffect) { this.reputationEffect = reputationEffect; }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }
}
