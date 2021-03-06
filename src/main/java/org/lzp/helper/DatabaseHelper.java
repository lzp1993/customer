package org.lzp.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.lzp.util.PropsUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public final class DatabaseHelper {
    private static final Logger LOGGER=LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final QueryRunner QUERY_RUNNER=new QueryRunner();
    private static final ThreadLocal<Connection>CONNECTION_HOLDER=new ThreadLocal<Connection>();
    public static <T> List <T> queryEntityList(Class<T>entityClass,String sql,Object... params){
        List<T> entityList;
        try {
            Connection conn=getConnection();
            entityList=QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        }catch (SQLException e)
        {
            LOGGER.error("query entuty list failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entityList;
    }
    static {
        Properties conf=PropsUtil.loadProps("config.properties");
        DRIVER=conf.getProperty("jdbc.driver");
        URL=conf.getProperty("jdbc.url");
        USERNAME=conf.getProperty("jdbc.USERNAME");
        PASSWORD=conf.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);

        }catch(ClassNotFoundException e)
        {
            LOGGER.error("can not load jdbc driver");
        }
    }
    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn ==null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }

        }
        return conn;
    }
    public static void  closeConnection(Connection conn)
    {
        if(conn!=null) {
         try {
                conn.close();
            }catch(SQLException e)
            {
                LOGGER.error("close connection failure");
            }
        }
    }
}
