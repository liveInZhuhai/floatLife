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
 * Created by Admini on 2016/12/13.
 */
public class ConnFactory extends BasePooledObjectFactory<Connection> {

    @Override
    public Connection create() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载出错!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url,username,password);
    }

    @Override
    public PooledObject<Connection> wrap(Connection mysqlConnect) {
        return new DefaultPooledObject<Connection>(mysqlConnect);
    }


}
