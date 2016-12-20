package service.controller;

import dataservice.entity.Player;
import dataservice.serviceimpl.PlayerService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y on 2016/12/20.
 */
public class LoginController extends ControllerBase{

    private PlayerService playerService = PlayerService.getPlayerService();


    public void login(Map<String,String> map, OutputStream outputStream){
        String username = map.get("username");                   //get username from map
        Player player = playerService.findByUsername(username);  //get player by username
        Map<String,String> returnMap = new HashMap<>();          //the map in order to return
        if (player == null){                                     //the condition of player is null
            returnMap.put("status","flase");
        }
        else {
            boolean status =  player.getPassword().equals(map.get("password"));
            if(status){
                returnMap.put("status","true");
                returnMap.put("health",String.valueOf(player.getHealth()));
                returnMap.put("username",player.getPlayerName());

            }
            else{
                returnMap.put("status","true");
            }
        }
        try {
            outputStream.write(map2Json(returnMap));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
