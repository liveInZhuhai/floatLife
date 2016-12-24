package dataservice.entity;

import java.util.HashMap;

/**
 * Created by Y on 2016/12/22.
 * Edit by D on 2016/12/23.
 */
public class Backpack {
    private int id, current_count,items_max;

    private HashMap<Integer,ItemInBackpack> hashPack = null;

    public String[] getItemsInfoString(){
        String[] result = new String[3];
        StringBuilder idsb = new StringBuilder("");
        StringBuilder countsb = new StringBuilder("");
        StringBuilder buysb = new StringBuilder("");
        for(ItemInBackpack iib:hashPack.values()){
            idsb.append(iib.getItemId()).append(",");
            countsb.append(iib.getItemCount()).append(",");
            buysb.append(iib.getItemPrize()).append(",");
        }
        result[0] = idsb.toString();
        result[1] = countsb.toString();
        result[2] = buysb.toString();
        return result;
    }

    public Integer[][] getItemArray(){
        Integer data[][] = new Integer[hashPack.size()][3];
        int i = 0;
        for (ItemInBackpack temp :
                hashPack.values()) {
            data[i][0] = temp.getItemId();
            data[i][1] = temp.getItemCount();
            data[i][2] = temp.getItemPrize();
            i++;
        }
        return data;
    }

    public void parseItemMap(String items_id,String items_count,String items_buy){
        hashPack = new HashMap<>();
        String[] idl = items_id.split(",");
        String[] counts = items_count.split(",");
        String[] buys = items_buy.split(",");
        for (int i=0;i<idl.length;i++){
            ItemInBackpack temp = new ItemInBackpack(Integer.parseInt(idl[i]),Integer.parseInt(counts[i]),Integer.parseInt(buys[i]));
            hashPack.put(temp.getItemId(),temp);
        }
    }

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

}
