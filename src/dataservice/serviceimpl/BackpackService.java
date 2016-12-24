package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Backpack;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by D on 2016/12/23.
 */
public class BackpackService extends ServiceBase<Backpack> {
    private static BackpackService backpackService;

    public static BackpackService getBackpackService() {
        if(backpackService!=null){
            backpackService = new BackpackService();
        }
        return backpackService;
    }
    private BackpackService(){
    }

    @Override
    public Backpack findById(int id) {
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
            findPS = conn.prepareStatement("SELECT * FROM backpack WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            Backpack jg = new Backpack();

            //使用结果集填充结果实体
            backpackDataSet(jg,rs);

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

    private void backpackDataSet(Backpack jg, ResultSet rs) throws SQLException {
        jg.setId(rs.getInt("id"));
        jg.setCurrent_count(rs.getInt("current_count"));
        jg.setItems_max(rs.getInt("items_max"));
        jg.parseItemMap(rs.getString("items_id"),rs.getString("items_count"),rs.getString("items_buy"));
    }

    public boolean update(Backpack bp){
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement updatePS = null;
        try {
            //对sql语句进行预编译
            updatePS = conn.prepareStatement("UPDATE backpack SET items_id = ?, items_count = ?, items_buy = ?, current_count = ?, items_max = ? WHERE id=?");

            String[] itemInfo = bp.getItemsInfoString();
            //对sql变量赋值
            updatePS.setString(1,itemInfo[0]);
            updatePS.setString(2,itemInfo[1]);
            updatePS.setString(3,itemInfo[2]);
            updatePS.setInt(4,bp.getCurrent_count());
            updatePS.setInt(5,bp.getItems_max());
            updatePS.setInt(6,bp.getId());

            updatePS.execute();
            //关闭结果集及连接
            updatePS.close();



            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            returnConnection(conn);
        }
        return false;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }
}
