package cn.smart.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by shu on 2017/6/28.
 *数据库助手类
 */
public final class DatabaseHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private final static ThreadLocal<Connection> CONNECTION_LOCAL = new ThreadLocal<>();
    //开启事务
    public static void beginTransaction() {
        Connection connection = getConnection();
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            LOGGER.error("begin transaction failure", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_LOCAL.set(connection);
        }
    }

    //提交事务
    public static void commitTransaction() {
        Connection connection = getConnection();
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("transtion commit failure", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_LOCAL.remove();
        }
    }

    //回滚事务
    public static void rollbackTransaction() {
        Connection connection = getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("rollbackTransaction failure", e);
            throw new RuntimeException(e);
        } finally {
            CONNECTION_LOCAL.remove();
        }
    }

    //获取数据库连接
    private static Connection  getConnection() {
       return CONNECTION_LOCAL.get();
    }
}
