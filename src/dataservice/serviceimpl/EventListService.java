package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.EventList;


import java.sql.*;

/**
 * Created by Admini on 2016/12/22.
 */
public class EventListService extends ServiceBase<EventList> {


    @Override
    public EventList findById(int id) {
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
            findPS = conn.prepareStatement("SELECT * FROM eventList WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            EventList jg = new EventList();

            //使用结果集填充结果实体
            eventDataSet(jg,rs);

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

    @Override
    public EventList add(EventList newpl) {
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
            addPS = conn.prepareStatement("INSERT INTO eventlist(event_name,event_note) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            //填入参数字符串
            addPS.setString(1,newpl.getEventName());
            addPS.setString(2,newpl.getEventNote());

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
       return delete(id,"eventlist");
    }


    protected void eventDataSet(EventList jg, ResultSet rs) throws SQLException {

    }


}
