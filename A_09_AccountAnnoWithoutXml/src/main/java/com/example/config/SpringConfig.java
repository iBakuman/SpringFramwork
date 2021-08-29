package com.example.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
 * @author Avarice
 */

@Configuration
@ComponentScan("com.example")
//@ComponentScan(basePackages = {"com.example"}) // 作用同上一行的注解一样
public class SpringConfig {

    /**
     * 用于创建一个QueryRunner对象
     *
     * @param ds
     * @return
     */
    @Bean(name = "runner")
    //@Scope("prototype")// 设置为多例对象
    public QueryRunner createQueryRunner(DataSource ds) {
        return new QueryRunner(ds);
    }

    /**
     * 创建数据源对象
     *
     * @return
     */
    @Bean(name = "ds")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            ds.setUser("root");
            ds.setPassword("193196");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
