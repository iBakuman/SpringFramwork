package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
         *      基于接口的动态代理：
         *          涉及的类：Proxy
         *          涉及的接口：InvocationHandler
         *          提供者：JDK官方
         *      如何创建对象
         *          使用Proxy类的newProxyInstance方法
         *      创建代理对象的要求
         *          被代理类至少实现一个接口，如果没有则不能使用基于接口的动态代理
         *      newProxyInstance方法的参数
         *          ClassLoader：类加载器
         *              它是用于加载代理对象字节码的。和被代理对象使用相同的类加载器，固定写法
         *          Class[]：被代理对象实现的所有接口的字节码构成的字节码数组
         *              他是用于让代理对象和被代理对象由相同的方法
         *          InvocationHandler：用于增强代码
         *              它是让我们写如何代理，我们一般都是写一个该接口的事项类，通常情况下都是匿名内部类，也可以是lambda表达式
         */

        Object proxyOut = null;
        Object proxyArr[] = new Object[3];
        // error：com.sun.proxy.$Proxy0 cannot be cast to com.example.proxy.Producer
        //Producer proxyProducer = (Producer) Proxy.newProxyInstance(
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(
                producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy     代理对象的引用
                     * @param method    当前执行的方法
                     * @param args      当前指向方法所需的参数
                     * @return          和被代理对象方法有相同的返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("In invoke function: ");
                        // 以下语句辉造成死循环***important
                        //System.out.println("proxy.hasCode = " + proxy.hashCode());

                        // error:Variable 'proxyOut' is accessed from within inner class, needs to be final or effectively final
                        //proxyOut = proxy;

                        // ok
                        proxyArr[0] = proxy;

                        // 提供增强的代码
                        Object retValue = null;

                        // 2.判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())) {
                            // 1.获取方法执行的参数
                            Float money = (Float) args[0];
                            retValue = method.invoke(producer, money*0.8f);
                        }
                        else {
                            retValue = method.invoke(producer, args);
                        }
                        return retValue;
                    }
                }
        );
        proxyProducer.saleProduct(2000);
        System.out.println("producer.hasCode = " + producer.hashCode());
        System.out.println("proxyProducer.hasCode = "+proxyProducer.hashCode());
        System.out.println("proxyArr[0].hasCode = "+proxyArr[0].hashCode());
    }
}
