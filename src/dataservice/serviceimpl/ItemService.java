
package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Item;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.*;
import java.util.*;

/**
 * Created by HK on 2016/12/12.
 */
public class ItemService extends ServiceBase {
    private static ItemService is = null;

    /**
     * 单例的服务获取方法，供api中进行玩家信息的操作
     * @return ItemService对象
     */
    public static ItemService getItemService(){
        if(is == null){
            is = new ItemService();
        }
        return is;
    }

    /**
     * 按id查找Item
     * @param id 欲查找Item的id
     * @return Item对象 数据表中的数据将会被填充进去
     */
    public Item findById(int id){
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
            findPS = conn.prepareStatement("SELECT * FROM item WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            Item jg = new Item();

            //使用结果集填充结果实体
            itemDataSet(jg,rs);

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
    public Item addItem(Item newpl){
        //通过基类的getConnection方法获取链接对象
        Connection conn = getConnection();
        //新建sql语句对象
        PreparedStatement addPS = null;
        //新建结果集对象
        ResultSet rs = null;
        //用于保存插入完成后id值的变量
        int id = 0 ;

        try {
            //对mysql语句进行预编译
            addPS = conn.prepareStatement("INSERT INTO (itemname) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            //填入参数字符串
            addPS.setString(1,newpl.getItemName());

            //执行插入
            addPS.executeUpdate();

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
            findPS = conn.prepareStatement("DELETE FROM item WHERE id=?");

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
    private void itemDataSet(Item jg,ResultSet rs) throws SQLException{
        jg.setId(rs.getInt("id"));
        jg.setItemName(rs.getString("itemName"));
        jg.setBasePrize(rs.getDouble("baseprize"));
        jg.setReputationEffect(rs.getInt("reputationeffect"));
        jg.setRare(rs.getInt("rare"));
    }

}
