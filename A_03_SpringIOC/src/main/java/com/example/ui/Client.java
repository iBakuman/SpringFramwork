package com.example.ui;

import com.example.dao.IAccountDao;
import com.example.service.IAccountService;
import com.example.service.impl.AccountServiceImpl;
import com.sun.xml.internal.bind.v2.util.XmlFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    /**
     *  获取spring的IOC核心容器，并根据id获取对象
     *  ApplicationContext的三个常用实现类
     *      1.ClassPathXmlApplicationContext
     *      2.FileSystemXmlApplicationContext
     *      3.AnnotationConfigApplicationContext
     *
     *  核心容器的两个接口引发出的问题
     *      1.ApplicationContext:单例对象适用。采用此接口
     *          它在创建核心容器时，创建对象采用的策略是采用立即加载的方式。只要一读取完配置文件马上创建配置文件中配置的对象
     *      2.BeanFactory:多例对象适用
     *          它在构建核心容器时，创建对象采用的策略是延迟加载的方式。什么时候根据id获取对象了，什么时候才真正的创建对象
     */

    @Test
    public  void testApplicationContext() {
        //  1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ac = new FileSystemXmlApplicationContext("F:\\Project\\Java\\Spring\\A_03_SpringIOC\\src\\main\\resources\\bean.xml");
        // 2.根据id获取bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(as);
        System.out.println(adao);
    }
    
    @Test
    public void testBeanFactory() {
        // 1.获取和兴容器对象
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService service = (IAccountService) factory.getBean("accountService");
        System.out.println(service);
    }
}
