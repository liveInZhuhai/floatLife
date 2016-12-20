package dataservice.connectionpool;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.DriverManager;

import static dataservice.connectionpool.MysqlConfig.password;
import static dataservice.connectionpool.MysqlConfig.url;
import static dataservice.connectionpool.MysqlConfig.username;


/**
 * Created by Y on 2016/12/13.
 *
 * use the makeObject() to use the wrap(create())[return the object of PooledObject]
 */
public class ConnFactory extends BasePooledObjectFactory<Connection> {
    /**
     *
     * @return the new connection of Mysql
     * @throws Exception
     */
    @Override
    public Connection create() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //load the Driver
        } catch (ClassNotFoundException e) {        //deal the error
            System.out.println("驱动加载出错!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url,username,password);//return the new connecttion
    }

    /**
     *
     * @param mysqlConnect is the object of Connection
     * @return put the object of Connection into pooledObject
     */
    @Override
    public PooledObject<Connection> wrap(Connection mysqlConnect) {
        return new DefaultPooledObject<Connection>(mysqlConnect);
    }


}
