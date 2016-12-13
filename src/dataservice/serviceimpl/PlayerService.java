package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.Connection;

/**
 * Created by D on 2016/12/12.
 */
public class PlayerService extends ServiceBase {
    private static PlayerService ps = null;
    public static PlayerService getPlayerService(){
        if(ps == null){
            ps = new PlayerService();
        }
        return ps;
    }
    public Player addPlayer(Player newpl){
        Connection conn = getConnection();


        return newpl;
    }
    public boolean isExists(Player pl){

        return false;
    }
    public boolean delete(Player delpl){

        return false;
    }


}
