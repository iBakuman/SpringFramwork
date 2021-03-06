package com.example.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 * 用来创建service和dao对象
 * 需要的信息
 * 1.需要一个配置文件来配置service和dao（配置的内容：唯一标识=全限定类名）
 * 2.读取配置文件中的内容，反射创建对象
 */
public class BeanFactory {
    private static Properties props;

    // 定义一个Map，用于存放我们要创建的对象
    private static Map<String, Object> beans;

    static {
        try {
            props = new Properties();
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            // 实例化容器
            beans = new HashMap<String, Object>();
            // 去除配置文件的所有key
            Enumeration<Object> keys = props.keys();
            while (keys.hasMoreElements()) {
                // 去除每个key
                String key = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = props.getProperty(key);
                // 反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                // 放入容器
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败!");
        }
    }

    /**
     * 根据bean的名称获取对象，单例模式
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return  beans.get(beanName);
    }

    ///**
    // * 根据bean的名称获取bean对象
    // *
    // * @param beanName
    // * @return
    // */
    //public  static Object getBean(String beanName) {
    //    Object bean = null;
    //    try {
    //        String beanPath = props.getProperty(beanName);
    //        bean = Class.forName(beanPath).newInstance();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return bean;
    //}
}
