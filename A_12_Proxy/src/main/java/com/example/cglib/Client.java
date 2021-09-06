package com.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟一个消费者
 *
 * @author Avarice
 * @date 2021/9/5 21:41
 */
public class Client {
    public static void main(String[] args) {
        Producer producer = new Producer();
        /**
         * 动态代理：
         *      特点：字节码在使用时动态创建，使用时加载
         *      作用：不修改源码的基础上对方法增强
         *      分类：
         *          1.基于接口的动态代理
         *          2.基于子类的动态代理
         *      基于子类的动态代理：
         *          涉及的类：Enhancer
         *          提供者：第三方cglib库
         *      如何创建对象
         *          使用Enhancer类的create方法
         *      创建代理对象的要求
         *          被代理的类不能被final修饰
         *      newProxyInstance方法的参数
         *          Class：字节码
         *              他是用于指定被代理对象的字节码
         *          Callback：用于提供增强的代码
         *              它是让我们如何写代理。我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         *              此接口的实现类都是谁用谁写
         *              我们一般写的都是该接口的子接口实现类：MethodInterceptor
         */

        Object proxyArr[] = new Object[2];
        Producer cglibProducer = (Producer) Enhancer.create(
                producer.getClass(),
                new MethodInterceptor() {
                    /**
                     * @param o             代理对象的引用
                     * @param method        当前执行的方法
                     * @param objects       当前执行的方法所需的参数
                     * @param methodProxy   当前执行方法的代理对象
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        // 提供增强的代码
                        Object retValue = null;

                        proxyArr[0] = o;
                        if ("saleProduct".equals(method.getName())) {
                            Float money = (Float) objects[0];
                            retValue = method.invoke(producer, money * 0.8f);
                        } else {
                            retValue = method.invoke(producer, objects);
                        }
                        return retValue;
                    }
                }
        );

        cglibProducer.saleProduct(12000f);
        System.out.println("proxyArr[0].hashCode = " + proxyArr[0].hashCode());
        System.out.println("cglibProducer.hashCode = " + cglibProducer.hashCode());
    }
}
