package serviceimpltest;

import com.alibaba.fastjson.JSON;
import dataservice.entity.Place;
import dataservice.serviceimpl.PlaceService;

/**
 * Created by D on 2016/12/14.
 */
public class PlaceTest {

    public static void main(String[] args) {
        PlaceService ps = new PlaceService();
        System.out.println("Start to Insert..");
        Place pl = new Place();
        pl.setPlaceName("DDD");
        pl.setRandomValue(200);
        pl.setRandomPeople(600);
        pl = ps.addPlace(pl);
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