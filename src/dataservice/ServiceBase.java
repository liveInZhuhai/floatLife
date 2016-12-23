package dataservice;

import dataservice.connectionpool.ConnFactory;
import dataservice.connectionpool.ConnPoolConfig;
import dataservice.entity.Fin;
import dataservice.entity.Place;
import dataservice.serviceimpl.PlaceService;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库服务基类 提供连接获取及归还功能
 * Created by D on 2016/12/12.
 */
public abstract class ServiceBase<T> {
    static GenericObjectPool<Connection> genericObjectPool;
    public static void initializeConnectionPool(){
        genericObjectPool = new GenericObjectPool(new ConnFactory(),new ConnPoolConfig());
        try {
            for (int i=0;i<5;i++){
                genericObjectPool.addObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        try {
            return genericObjectPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void returnConnection(Connection conn){
        genericObjectPool.returnObject(conn);

    }

    public abstract T findById(int id);
    public  T add(T newpl){
        return null;
    }
    protected boolean delete(int id,String sheetName){
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement findPS = null;

        try {
            //对sql语句进行预编译
            findPS = conn.prepareStatement("DELETE FROM "+sheetName+" WHERE id=?");

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
    public abstract boolean delete(int id);
}
