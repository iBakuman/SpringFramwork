package com.example.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了：开启事务、提交事务、回滚事务和释放连接
 *
 * @author Avarice
 * @date 2021/9/5 19:06
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.example.service.impl.*.*(..))")
    private void pt1() {
    }

    public ConnectionUtils getConnectionUtils() {
        return connectionUtils;
    }

    /**
     * 开启事务
     */
    //@Before("pt1()")
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
    //@AfterReturning("pt1()")
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
    //@AfterThrowing("pt1()")
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
    //@After("pt1()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();// 先关闭连接
            connectionUtils.removeConnection();// 后解除线程和连接的绑定
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            // 1.获取方法参数
            Object args[] = pjp.getArgs();

            // 2.开启事务
            this.beginTransaction();

            // 3.执行方法
            rtValue = pjp.proceed(args);

            // 4.提交事务
            this.commit();

            return rtValue;
        } catch (Throwable e) {
            // 5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        } finally {
            // 6.释放资源
            this.release();
        }
    }
}
