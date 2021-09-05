package com.example.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了：开启事务、提交事务、回滚事务和释放连接
 * @author Avarice
 * @date 2021/9/5 19:06
 */
public class TransactionManager {
    private ConnectionUtils connectionUtils;

    public ConnectionUtils getConnectionUtils() {
        return connectionUtils;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();// 先关闭连接
            connectionUtils.removeConnection();// 后解除线程和连接的绑定
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
