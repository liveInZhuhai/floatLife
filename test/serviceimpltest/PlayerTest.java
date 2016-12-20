package serviceimpltest;

import com.alibaba.fastjson.JSON;
import dataservice.entity.Player;
import dataservice.serviceimpl.PlayerService;

/**
 *
 * Created by D on 2016/12/14.
 */
public class PlayerTest {

    public static void main(String[] args) {
        PlayerService ps = PlayerService.getPlayerService();
        System.out.println("Start to Insert..");
        Player pl = new Player();
        pl.setPlayerName("DDD");
        pl.setPassword("um");
        pl = ps.addPlayer(pl);
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
