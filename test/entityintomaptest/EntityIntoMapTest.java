package entityintomaptest;

import dataservice.entity.Player;
import dataservice.serviceimpl.PlayerService;
import service.controller.ControllerBase;
import service.controller.LoginController;

import java.util.Map;

/**
 * Created by Admini on 2016/12/20.
 */
public class EntityIntoMapTest {


    public static void main(String[] args) {
        Player player = new Player();
        player.setPassword("5555555555555");
        player.setId(2);
        player.setFinId(6);
        player.setInsurance(55);
        player.setPlayerName("555555555555555555555sss");

        LoginController loginController = new LoginController();
        Map map = loginController.getReturnMap(player);
        System.out.println(map.toString());
    }
}
