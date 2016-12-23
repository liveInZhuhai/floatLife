package service.controller;

import dataservice.entity.Player;
import dataservice.serviceimpl.PlayerService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y on 2016/12/20.
 *  Edit by D on 2016/12/23.
 *  添加了注册方法
 */
public class LoginController extends ControllerBase{

    private PlayerService playerService = PlayerService.getPlayerService();

    public void login(Map<String,String> params, OutputStream outputStream){
        String username = params.get("username");                   //get username from params
        Player player = playerService.findByUsername(username);  //get player by username
        Map<String,String> returnMap = new HashMap<>();          //the params in order to return
        if (player == null){                                     //the condition of player is null
            returnMap.put("status","false");
        }
        else {
            boolean status =  player.getPassword().equals(params.get("password"));
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

    public void register(Map<String,String> params, OutputStream outputStream){
        String username = params.get("username");
        Map<String,String> returnMap = new HashMap<>();
        if(username.equals("")){
            returnMap.put("status","请填写用户名");

        }else{
            Player player = playerService.findByUsername(username);

            if (player != null){
                returnMap.put("status","用户名已存在");
            }
            else {
                playerService.add(new Player(username,params.get("password")));
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
