package dataservice.entity;

/**
 * Created by Y on 2016/12/22.
 * Edit by D on 2016/12/23.
 */
public class Backpack {
    private int id, current_count,items_max;
    private String items_id,items_count,items_buy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrent_count() {
        return current_count;
    }

    public void setCurrent_count(int current_count) {
        this.current_count = current_count;
    }

    public int getItems_max() {
        return items_max;
    }

    public void setItems_max(int items_max) {
        this.items_max = items_max;
    }

    public String getItems_id() {
        return items_id;
    }

    public void setItems_id(String items_id) {
        this.items_id = items_id;
    }

    public String getItems_count() {
        return items_count;
    }

    public void setItems_count(String items_count) {
        this.items_count = items_count;
    }

    public String getItems_buy() {
        return items_buy;
    }

    public void setItems_buy(String items_buy) {
        this.items_buy = items_buy;
    }
}
