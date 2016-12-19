package serviceimpltest;

import com.alibaba.fastjson.JSON;
import dataservice.entity.Item;
import dataservice.serviceimpl.ItemService;

/**
 * Created by HK on 2016/12/14.
 */
public class ItemTest {

    public static void main(String[] args) {
        ItemService ps = new ItemService();
        System.out.println("Start to Insert..");
        Item pl = new Item();
        pl.setItemName("DDD");
        pl.setRare(1);
        pl = ps.addItem(pl);
        int id = pl.getId();
        System.out.println("InsertDone! ID:" + id);
        System.out.println("Start to find..");
        System.out.println(JSON.toJSONString(ps.findById(id)));
        System.out.println("FindDone!");

        System.out.println("Start to delete..");
        System.out.println(ps.delete(id));
        System.out.println("DeleteDone!");
    }
}