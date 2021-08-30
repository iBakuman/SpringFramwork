package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 该类是一个配置类，和bean.xml的作用是一样的
 *      spring的新注解
 *          1.Configuration
 *              作用：指定当前类是一个配置类
 *          2.ComponentScan
 *              作用：用于通过注解指定Spring在创建容器时需要扫描的包
 *              属性：
 *                  value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *              此注解的作用相当于XML中的如下配置：
 *                  <context:component-scan base-package="com.example"/>
 *      3.Bean
 *          作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *          属性：
 *              name：用于指定bean的id。当不写时，默认值时当前方法的名称
 *          细节：
 *              当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *              查找的方式和Autowired注解的作用是一样的
 *      4.Import
 *          作用：英语导入其他的配置类
 *          属性：
 *              value：用于指定其他类的字节码。
 *                  当我们使用Import注解后，有Import注解的类就是父配置类，而导入的都是子配置类
 * @author Avarice
 */

@Configuration
@ComponentScan({"com.example"})
@Import(JdbcConfig.class)
//@ComponentScan(basePackages = {"com.example"}) // 作用同上一行的注解一样
public class SpringConfig {

}
