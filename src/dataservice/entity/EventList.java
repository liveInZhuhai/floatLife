package dataservice.entity;

/**
 * Created by Y on 2016/12/22.
 */
public class EventList {
    private int id;
    private String eventName;
    private String eventNote;
    private double eventRate;
    private int eventPlace;
    private int effectHealth;
    private int moneyEffectType;
    private double moneyEffect;
    private int prizeEffectId;
    private double prizeEffect;
    private int effectItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public double getEventRate() {
        return eventRate;
    }

    public void setEventRate(double eventRate) {
        this.eventRate = eventRate;
    }

    public int getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(int eventPlace) {
        this.eventPlace = eventPlace;
    }

    public int getEffectHealth() {
        return effectHealth;
    }

    public void setEffectHealth(int effectHealth) {
        this.effectHealth = effectHealth;
    }

    public int getMoneyEffectType() {
        return moneyEffectType;
    }

    public void setMoneyEffectType(int moneyEffectType) {
        this.moneyEffectType = moneyEffectType;
    }

    public double getMoneyEffect() {
        return moneyEffect;
    }

    public void setMoneyEffect(double moneyEffect) {
        this.moneyEffect = moneyEffect;
    }

    public int getPrizeEffectId() {
        return prizeEffectId;
    }

    public void setPrizeEffectId(int prizeEffectId) {
        this.prizeEffectId = prizeEffectId;
    }

    public double getPrizeEffect() {
        return prizeEffect;
    }

    public void setPrizeEffect(double prizeEffect) {
        this.prizeEffect = prizeEffect;
    }

    public int getEffectItem() {
        return effectItem;
    }

    public void setEffectItem(int effectItem) {
        this.effectItem = effectItem;
    }

    public int getEffectItemCount() {
        return effectItemCount;
    }

    public void setEffectItemCount(int effectItemCount) {
        this.effectItemCount = effectItemCount;
    }

    private int effectItemCount;
}
