package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by D on 2016/12/12.
 */
public class PlayerService extends ServiceBase {
    private static PlayerService ps = null;
    private  PreparedStatement addPS = null;
    public static PlayerService getPlayerService(){
        if(ps == null){
            ps = new PlayerService();
        }
        return ps;
    }
    public Player addPlayer(Player newpl){
        if(addPS == null){
            Connection conn = getConnection();
            try {
                addPS = conn.prepareStatement("INSERT INTO player(playername,password,fin_id) VALUES ('黄卓东无浪交','黄卓东无浪交',1)");
                addPS.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        return newpl;
    }
    public boolean isExists(Player pl){

        return false;
    }
    public boolean delete(Player delpl){

        return false;
    }


}
