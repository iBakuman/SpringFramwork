package com.example.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 *
 * @author Avarice
 * @date 2021/9/5 19:01
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *
     * @return 当前线程连接
     */
    public Connection getThreadConnection() {
        Connection conn = tl.get();
        if (null == conn) {
            try {
                conn = dataSource.getConnection();
                tl.set(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public void  removeConnection(){
        tl.remove();
    }
}
