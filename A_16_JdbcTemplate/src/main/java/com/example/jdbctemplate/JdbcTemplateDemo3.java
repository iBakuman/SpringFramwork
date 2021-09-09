package com.example.jdbctemplate;

import com.example.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * jdbcTemplate的CRUD操作
 *
 * @author Avarice
 * @date 2021/9/9 18:14
 */
public class JdbcTemplateDemo3 {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    /**
     * 必须在上一条语句的下面
     */
    JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        jt.update("insert into account(name, money) values(?, ?)", "刘石东", 10000f);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        jt.update("delete from account where name = ?", "刘石东");
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        jt.update("update account set name=?,money=? where name=?", "要全瑞", 100000f, "刘石东");
    }

    /**
     * 测试查询（结果集有多条记录）
     */
    @Test
    public void testQuery1() {
        // 方法一：自己提供RowMapper的实现类
        List<Account> accounts = jt.query(
                "select * from account where money > ?",
                new AccountRowMapper() ,
                200f
        );

        // 方法二：使用Spring提供的RowMapper实现类
        //List<Account> accounts = jt.query(
        //        "select * from account where money > ?",
        //        new BeanPropertyRowMapper<>(Account.class),
        //        100f
        //);

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 测试查询（结果集只有一行一列，使用聚合函数但不加group by子句）
     */
    @Test
    public void testQuery2() {
        Long count = jt.queryForObject(
                "select count(*) from account where money > ?",
                Long.class,
                100f
        );
        System.out.println(count);
    }
}

/**
 * 自定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {
    private static int count;

    /**
     * 把结果集中的数据封装到Account中，然后由Spring把每个Account加到集合中
     * @param resultSet 结果集中的一个结果
     * @param i         当前遍历到第几个结果集，从0开始计数
     * @return          封装后的对象
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        ++count;
        System.out.println("count = " + count + ", i = " + i);
        Account account = new Account();
        account.setId(resultSet.getInt("Id"));
        // 效果同上，不区分大小写
        //account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
