package com.example.factory;

import com.example.service.AccountService;
import com.example.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 *
 * @author Avarice
 * @date 2021/9/6 21:26
 */
public class BeanFactory {
    private  AccountService service;
    private TransactionManager txManager;

    public final void setService(AccountService service) {
        this.service = service;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 获取代理对象
     * @return  代理对象
     */
    public AccountService getService() {
        return (AccountService) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object retValue = null;
                        try {
                            txManager.beginTransaction();
                            retValue = method.invoke(service, args);
                            txManager.commit();
                        } catch (Exception e) {
                            txManager.rollback();
                        } finally {
                            txManager.release();
                        }
                        return retValue;
                    }
                }
        );
    }
}
