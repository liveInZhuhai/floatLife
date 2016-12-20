package dataservice;

import dataservice.connectionpool.ConnFactory;
import dataservice.connectionpool.ConnPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;

/**
 * 数据库服务基类 提供连接获取及归还功能
 * Created by D on 2016/12/12.
 */
public abstract class ServiceBase {
    final static GenericObjectPool<Connection> genericObjectPool = new GenericObjectPool(new ConnFactory(),new ConnPoolConfig());
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
}
