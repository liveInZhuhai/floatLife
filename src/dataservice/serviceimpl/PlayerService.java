package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.*;
import java.util.*;

/**
 * Player表Service
 * Created by D on 2016/12/12.
 */
public class PlayerService extends ServiceBase<Player> {
    private static PlayerService ps = null;

    /**
     * 单例的服务获取方法，供api中进行玩家信息的操作
     * @return PlayerService对象
     */
    public static PlayerService getPlayerService(){
        if(ps == null){
            ps = new PlayerService();
        }
        return ps;
    }

    private PlayerService() {
    }

    /**
     * 根据用户名查找
     * @param playername 用户名
     * @return 对应的palyer实体
     */
    public Player findByUsername(String playername){
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement findPS;

        try {
            //对sql语句进行预编译
            findPS = conn.prepareStatement("SELECT * FROM player WHERE playername=?");

            //对sql变量赋值
            findPS.setString(1,playername);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            Player jg = new Player();

            //使用结果集填充结果实体
            playerDataSet(jg,rs);

            //关闭结果集及连接
            findPS.close();
            rs.close();

            //返回结果
            return jg;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            returnConnection(conn);
        }
        return null;
    }

    /**
     * 按id查找Player
     * @param id 欲查找Player的id
     * @return Player对象 数据表中的数据将会被填充进去
     */
    public Player findById(int id) {
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement findPS = null;

        //如传入的是无效id直接返回null
        if(id == 0){
            return null;
        }

        try {
            //对sql语句进行预编译
            findPS = conn.prepareStatement("SELECT * FROM player WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            Player jg = new Player();

            //使用结果集填充结果实体
            playerDataSet(jg,rs);

            //关闭结果集及连接
            findPS.close();
            rs.close();

            //返回结果
            return jg;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            returnConnection(conn);
        }
        return null;
    }

    /**
     * 添加新玩家
     * @param newpl 一个Player类型的对象 其中只需对playername以及password赋值
     * @return 返回创建完成后的Player实体 如插入失败则返回null
     */
    public Player add(Player newpl){
        //通过基类的getConnection方法获取链接对象
        Connection conn = getConnection();
        //新建sql语句对象
        PreparedStatement addPS = null;
        PreparedStatement addbackpack = null;
        PreparedStatement addfin = null;
        //新建结果集对象
        ResultSet rs = null;
        //用于保存插入完成后id值的变量
        int id = 0 ;

        try {
            conn.setAutoCommit(false);
            //对mysql语句进行预编译
            addPS = conn.prepareStatement("INSERT INTO player(playername,password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            //填入参数字符串
            addPS.setString(1,newpl.getPlayerName());
            addPS.setString(2,newpl.getPassword());
            addbackpack = conn.prepareStatement("INSERT INTO backpack");
            addfin = conn.prepareStatement("INSERT INTO fin");
            //执行插入
            addPS.executeUpdate();
            addbackpack.execute();
            addfin.execute();

            conn.commit();
            //取回插入中生成键的结果集
            rs = addPS.getGeneratedKeys();

            //如插入失败返回空对象
            if(!rs.next()){
                return null;
            }

            //取出插入后数据在数据库id
            id = rs.getInt(1);

            //调用service中的按id查找 将实体拉出并返回
            newpl = findById(id);

            //关闭结果集及链接
            rs.close();
            addPS.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.rollback();
            }catch (SQLException a){
                a.printStackTrace();
            }

        }finally {
            //归还连接
            returnConnection(conn);
        }

        return newpl;
    }



    public boolean delete(int id){
        //获取链接
        Connection conn = getConnection();

            //mysql语句对象
            PreparedStatement findPS = null;

            try {
            //对sql语句进行预编译
            findPS = conn.prepareStatement("DELETE FROM player WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //执行sql语句 并返回结果
            return findPS.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            returnConnection(conn);
        }
        return false;
    }

    /**
     * 通过结果集将信息映射到实体
     * @param jg 需要填充的实体对象
     * @param rs 结果集
     * @throws SQLException
     */
    private void playerDataSet(Player jg,ResultSet rs) throws SQLException{
        jg.setId(rs.getInt("id"));
        jg.setPlayerName(rs.getString("playername"));
        jg.setPassword(rs.getString("password"));
        jg.setFinId(rs.getInt("fin_id"));
        jg.setHealth(rs.getInt("health"));
        jg.setReputation(rs.getInt("reputation"));
        jg.setCreate_date(new java.util.Date(rs.getTimestamp("create_date").getTime()));
        jg.setInsurance(rs.getInt("insurance"));
    }

}
