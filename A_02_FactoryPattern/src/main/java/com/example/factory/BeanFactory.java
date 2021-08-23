package com.example.factory;

import java.io.InputStream;
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

    static {
        try {
            props = new Properties();
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败!");
        }
    }

    /**
     * 根据bean的名称获取bean对象
     *
     * @param beanName
     * @return
     */
    public  static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
